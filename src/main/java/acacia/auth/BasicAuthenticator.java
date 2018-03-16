package acacia.auth;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import acacia.core.BasicUser;
import acacia.core.Person;
import acacia.dao.PersonDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

public class BasicAuthenticator implements Authenticator<BasicCredentials, BasicUser>{

	private PersonDAO personDAO;
	
	public BasicAuthenticator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@Override
    @UnitOfWork
	public Optional<BasicUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
		
		List<Person> personOptional = this.personDAO.findByEmail(credentials.getUsername());
		if(personOptional.isEmpty()) return Optional.empty();

		Person person = (Person) personOptional.get(0);//nullPointer

		if( person!=null && 
			person.getEmail().equalsIgnoreCase(credentials.getUsername()) && 
			//person.getPassword().equals(credentials.getPassword())
			BCrypt.checkpw(credentials.getPassword(),person.getPassword())
		) {
			String role = null;
			if(person.getRole().equals("student")) role = "Student";
			if(person.getRole().equals("collaborator")) role = "Teacher";
			//if(person.getRole().equals("annalist")) role = "Annalist";
			if(person.getRole().equals("admin")) role = "Admin";
			return Optional.of(new BasicUser(credentials.getUsername(), role));
		}
		return Optional.empty();
	}

}
