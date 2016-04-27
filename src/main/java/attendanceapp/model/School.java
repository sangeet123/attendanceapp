package attendanceapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "schools", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class School implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3776706368364694697L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", unique = true, nullable = false, length = 250)
	private String name;

	@Column(name = "telephone", nullable = false, length = 250)
	private String telephone;

	@Column(name = "email", nullable = false, length = 250)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="SCHOOL_ID")
	private Set<Student> students = new HashSet<Student>(0);

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="SCHOOL_ID")
	private Set<User> users = new HashSet<User>(0);

	public School() {

	}

	public School(long id, String email, String name, String telephone) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", telephone=" + telephone
				+ ", email=" + email;
	}

}
