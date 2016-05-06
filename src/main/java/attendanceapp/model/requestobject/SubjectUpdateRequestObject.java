package attendanceapp.model.requestobject;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

public class SubjectUpdateRequestObject {

	private static final int INITIAL_NONZERO_ODD_NUM = 17;
	private static final int MULTIPLIER_NONZERO_ODD_NUM = 157;

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank()
	private String shortName;

	@NotBlank()
	private String name;

	private int credit;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	@Override()
	public int hashCode() {
		return new HashCodeBuilder(INITIAL_NONZERO_ODD_NUM, MULTIPLIER_NONZERO_ODD_NUM).append(id).append(shortName)
				.append(name).append(credit).toHashCode();
	}

	@Override()
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		SubjectCreateRequestObject other = (SubjectCreateRequestObject) obj;
		return new EqualsBuilder().append(id, other.getId()).append(shortName, other.getShortName())
				.append(name, other.getName()).append(credit, other.getCredit()).isEquals();
	}

	@Override
	public String toString() {
		return "SubjectUpdateRequestObject [id=" + id + ", shortName=" + shortName + ", name=" + name + ", credit="
				+ credit + "]";
	}
}
