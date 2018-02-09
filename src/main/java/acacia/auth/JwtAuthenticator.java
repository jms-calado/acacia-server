package acacia.auth;

import java.util.List;
import java.util.Optional;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtContext;

import acacia.core.JwtUser;
import acacia.core.Person;
import acacia.dao.PersonDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.hibernate.UnitOfWork;

public class JwtAuthenticator implements Authenticator<JwtContext, JwtUser>{

	private PersonDAO personDAO;
	
	public JwtAuthenticator(PersonDAO personDAO) {
		this.personDAO=personDAO;
	}
	
	@Override
    @UnitOfWork
	public Optional<JwtUser> authenticate(JwtContext context) throws AuthenticationException {
		try {
			JwtClaims jwtClaims = context.getJwtClaims();

			//String user = jwtClaims.getSubject();
			String username = (String) jwtClaims.getClaimValue("username");
			String role = (String) jwtClaims.getClaimValue("role");
			
			List<Person> personOptional = this.personDAO.findByUsername(username);
			Person person = (Person) personOptional.get(0);
			
			if(person!=null) { 
				if(	person.getUsername().equalsIgnoreCase(username) && 
					person.getRole().equals(role)
				) {
					return Optional.of(new JwtUser(username, role));
				}
			}
			return Optional.empty();
		}
		catch (Exception e) { 
			return Optional.empty(); 
		}
	}

}
