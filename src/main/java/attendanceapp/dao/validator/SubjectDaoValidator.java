package attendanceapp.dao.validator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import attendanceapp.constants.SubjectRestControllerConstants;
import attendanceapp.exceptions.ConflictException;
import attendanceapp.exceptions.UnknownException;

@Component()
public class SubjectDaoValidator {
	@Autowired()
	private SessionFactory sessionFactory;
	private final Logger logger = LoggerFactory.getLogger(StaffDaoValidator.class);
	private Session session = null;

	private static final String SELECT_SUBJECT_BY_SHORT_NAME = "from attendanceapp.model.Subject where short_name= :shortname and school.id= :schoolId";
	private static final String SELECT_SUBJECT_BY_SHORT_NAME_IF_ALREADY_EXIST = "from attendanceapp.model.Subject where short_name= :shortname and school.id= :schoolId and id!= :subjectId";

	private void closeSession() {
		if (session != null) {
			session.close();
		}
	}

	public void validateShortName(final long schoolId, final String shortName, final long subjectId,
			final boolean isCreateOperation) {
		try {
			session = sessionFactory.openSession();
			Query query;
			if (isCreateOperation) {
				query = session.createQuery(SELECT_SUBJECT_BY_SHORT_NAME).setParameter("shortname", shortName)
						.setParameter("schoolId", schoolId);
			} else {
				query = session.createQuery(SELECT_SUBJECT_BY_SHORT_NAME_IF_ALREADY_EXIST)
						.setParameter("shortname", shortName).setParameter("schoolId", schoolId)
						.setParameter("subjectId", subjectId);
			}

			if (query.uniqueResult() != null) {
				throw new ConflictException(SubjectRestControllerConstants.DUPLICATE_SUBJECT_SHORT_NAME);
			}
		} catch (ConflictException ex) {
			throw ex;
		} catch (Exception ex) {
			logger.error("", ex);
			throw new UnknownException();
		} finally {
			closeSession();
		}
	}

}
