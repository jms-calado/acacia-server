package acacia.auth;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableSet;

import acacia.core.Person;
import acacia.core.BasicUser;
import acacia.dao.PersonDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import org.mindrot.jbcrypt.BCrypt;

public class BasicAuthenticator implements Authenticator<BasicCredentials, BasicUser>{

	private PersonDAO personDAO;
	
	public BasicAuthenticator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@Override
    @UnitOfWork
	public Optional<BasicUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
		
		List<Person> personOptional = this.personDAO.findByUsername(credentials.getUsername());
		Person person = (Person) personOptional.get(0);
		
		if( person!=null && 
			person.getUsername().equalsIgnoreCase(credentials.getUsername()) && 
			//person.getPassword().equals(credentials.getPassword())
			BCrypt.checkpw(credentials.getPassword(),person.getPassword())
		) {
			String role = null;
			if(person.getRole().equals("Student")) role = "Student";
			if(person.getRole().equals("Teacher")) role = "Teacher";
			if(person.getRole().equals("Annalist")) role = "Annalist";
			if(person.getRole().equals("Admin")) role = "Admin";
			return Optional.of(new BasicUser(credentials.getUsername(), role));
		}
		return Optional.empty();
	}

}
