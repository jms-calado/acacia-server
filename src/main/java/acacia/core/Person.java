package acacia.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users_table")
@NamedQueries({ @NamedQuery(name="acacia.core.Person.findAll",
							query="select e from Person e"),
				@NamedQuery(name="acacia.core.Person.findByUsername",
							query="select e from Person e "
								+ "where e.username like :username "),
				@NamedQuery(name="acacia.core.Person.findByName",
							query="select e from Person e "
								+ "where e.full_name like :name "),
				@NamedQuery(name="acacia.core.Person.getPass",
							query="select password from Person e "
								+ "where e.username = :username ")
})
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	private String full_name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "role", nullable = false)
	private String role;
	
	public Person() {}
	public Person(String username, String password) {
		this.username = username;
		this.password = password;		
	}
	public Person(String username, String full_name, String email, String password, String role) {
		this.username=username;
		this.full_name=full_name;
		this.email=email;
		this.password=password;
		this.role=role;		
	}
	public Person(long id, String username, String full_name, String email, String password, String role) {
		this.id=id;
		this.username=username;
		this.full_name=full_name;
		this.email=email;
		this.password=password;
		this.role=role;		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((full_name == null) ? 0 : full_name.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
