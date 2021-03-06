package attendanceapp.requestobjecttomodelmapper;

import java.time.LocalDateTime;

import attendanceapp.model.School;

public class SchoolRequestToSchoolMapper {

	private SchoolRequestToSchoolMapper() throws InstantiationException {
		throw new InstantiationException();
	}

	public static class SchoolBuilder {
		private long id;

		private String name;

		private String telephone;

		private String email;

		private LocalDateTime createdOn;

		private LocalDateTime updatedOn;

		public SchoolBuilder id(long id) {
			this.id = id;
			return this;
		}

		public SchoolBuilder name(String name) {
			this.name = name;
			return this;
		}

		public SchoolBuilder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}

		public SchoolBuilder email(String email) {
			this.email = email;
			return this;
		}

		public SchoolBuilder createdOn(LocalDateTime createdOn) {
			this.createdOn = createdOn;
			return this;
		}

		public SchoolBuilder updatedOn(LocalDateTime updatedOn) {
			this.updatedOn = updatedOn;
			return this;
		}

		public School build() {
			School school = new School();
			school.setId(this.id);
			school.setEmail(this.email);
			school.setName(this.name);
			school.setTelephone(this.telephone);
			school.setCreatedOn(createdOn);
			school.setUpdatedOn(updatedOn);
			return school;
		}
	}

}
