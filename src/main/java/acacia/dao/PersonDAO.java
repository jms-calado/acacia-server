package acacia.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import acacia.core.Person;
import io.dropwizard.hibernate.AbstractDAO;

public class PersonDAO extends AbstractDAO<Person>{

	public PersonDAO(SessionFactory sessionFactory) {
		super (sessionFactory);
	}

    public List<Person> findAll() {
        return list(namedQuery("acacia.core.Person.findAll"));
    }
    //*
    public List<Person> findByUsername(String username) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(username).append("%");
        return list(
                namedQuery("acacia.core.Person.findByUsername")
                .setParameter("username", builder.toString())
        );
    }
    /*/
    /*
    public Optional<Person> findByUsername(String username) {
    	return Optional.ofNullable(get(username));
    }
    */
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(get(id));
    }
/*
	public Optional<Person> findByUserPass(String username, String password) {
        StringBuilder builder1 = new StringBuilder("%");
        builder1.append(username).append("%");
        List userpassword = list(namedQuery("acacia.core.Person.getPass")
        		.setParameter("username", builder1.toString())
        );
		
		return Optional.ofNullable(get(username));
	}
	*/
	public Person create(Person person) {
		return persist(person);
	}

}