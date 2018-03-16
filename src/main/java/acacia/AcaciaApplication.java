package acacia;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOW_CREDENTIALS_PARAM;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import acacia.auth.BasicAuthenticator;
import acacia.auth.JwtAuthenticator;
import acacia.auth.ResourceAuthorizer;
import acacia.core.Person;
import acacia.core.BasicUser;
import acacia.core.JwtUser;
import acacia.dao.PersonDAO;
import acacia.dataobjects.GlobalVar;
import acacia.health.SearchHealthCheck;
import acacia.resources.AuthTest;
import acacia.resources.Login;
import acacia.resources.PersonResource;
import acacia.resources.queries.FindUser;
import acacia.resources.queries.ListClasses;
import acacia.resources.queries.ListIndividualProperties;
import acacia.resources.queries.ListObservationsOfSession;
import acacia.resources.queries.ListProfilesOfStudent;
import acacia.resources.queries.ListSessionsClass;
import acacia.resources.queries.ListSessionsOfStudent;
import acacia.resources.queries.ListStudentsOfClass;
import acacia.resources.queries.ListStudentsOfObservation;
import acacia.resources.queries.ListStudentsOfSession;
import acacia.resources.queries.PlotIssuesOfStudent;
import acacia.resources.queries.PlotIssue;
import acacia.resources.queries.PlotSession;
import acacia.resources.queries.PlotStudent;
import acacia.resources.queries.PlotStudentSession;
import acacia.resources.updates.InsertAcademicProfile;
import acacia.resources.updates.InsertAffect;
import acacia.resources.updates.InsertBehaviour;
import acacia.resources.updates.InsertClass;
import acacia.resources.updates.InsertDiversityProfile;
import acacia.resources.updates.InsertEmotion;
import acacia.resources.updates.InsertLearningProfile;
import acacia.resources.updates.InsertMusicalProfile;
import acacia.resources.updates.InsertObservation;
import acacia.resources.updates.InsertSession;
import acacia.resources.updates.InsertStudentIssue;
import acacia.resources.updates.InsertUser;
import acacia.resources.updates.InsertVLO;
import acacia.resources.updates.SuperSession;
import acacia.services.SparqlExecutor;
import acacia.websocket.DeviceWebSocketServer;
import be.tomcools.dropwizard.websocket.WebsocketBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AcaciaApplication extends Application<AcaciaConfiguration> {
    private WebsocketBundle websocket = new WebsocketBundle();

	public static void main(String[] args) throws Exception {
		new AcaciaApplication().run(args);
	}

    private final HibernateBundle<AcaciaConfiguration> hibernateBundle = new HibernateBundle<AcaciaConfiguration>(Person.class){
    	@Override
    	public DataSourceFactory getDataSourceFactory(AcaciaConfiguration configuration){
    		return configuration.getDataSourceFactory();
    	}	
	};
	
	@Override
	public String getName() {
		return "acacia";
	}

    @Override
    public void initialize(Bootstrap<AcaciaConfiguration> bootstrap) {
        GlobalVar.GlobalObservationID = 0;
        GlobalVar.GlobalUserID = 0;
        GlobalVar.GlobalSessionID = 0;
        super.initialize(bootstrap);
        bootstrap.addBundle(websocket);
        bootstrap.addBundle(hibernateBundle);
    }
	
	@Override
	public void run(AcaciaConfiguration configuration, Environment environment) throws Exception {
		/*
		 * Enabling CORS (Cross-Origin Resource Sharing)
		 * Sources:	https://stackoverflow.com/a/25801822
		 * 			https://gist.github.com/yunspace/07d80a9ac32901f1e149#gistcomment-1468074
		 */
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);
		filter.setInitParameter(ALLOWED_METHODS_PARAM, "OPTIONS,POST,GET");
		filter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin,Content-Type,Accept,X-Requested-With");
		filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		
        //Annotated endpoint
        websocket.addEndpoint(DeviceWebSocketServer.class);
        
        //programmatic endpoint
        //ServerEndpointConfig websocketserverEndpointConfig = ServerEndpointConfig.Builder.create(DeviceWebSocketServer.class, "/actions").build();
        //websocket.addEndpoint(websocketserverEndpointConfig);

        //-----------------------------------------------------------------
        
        final PersonDAO personDAO = new PersonDAO(hibernateBundle.getSessionFactory());
        //environment.jersey().register(new PersonResource(personDAO));        

        //-----------------------------------------------------------------
        
        BasicAuthenticator basicAuthenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
        		.create(BasicAuthenticator.class, PersonDAO.class, personDAO);

        final AuthFilter<BasicCredentials, BasicUser> basicFilter 
	        = new BasicCredentialAuthFilter.Builder<BasicUser>()
		        .setAuthenticator(basicAuthenticator)
		        //.setAuthorizer(new ResourceAuthorizer())
		        .setRealm("basic auth realm")
		        .setPrefix("Basic")
		        .buildAuthFilter();
        
        final byte[] key = configuration.getJwtTokenSecret();
        
        JwtAuthenticator jwtAuthenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(JwtAuthenticator.class, PersonDAO.class, personDAO);
        
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
            	.build(); // create the JwtConsumer instance
        
        final AuthFilter<JwtContext,JwtUser> jwtFilter
	        = new JwtAuthFilter.Builder<JwtUser>()
		        .setJwtConsumer(jwtConsumer)
		        .setRealm("jwt auth realm")
		        .setPrefix("Bearer")
		        .setAuthenticator(jwtAuthenticator)
		        .setAuthorizer(new ResourceAuthorizer())
	        	.buildAuthFilter();
        
        final PolymorphicAuthDynamicFeature polyAuthDynamicFeature
        	= new PolymorphicAuthDynamicFeature<>(
        			ImmutableMap.of(
        					BasicUser.class, basicFilter,
        					JwtUser.class, jwtFilter));
        
        final AbstractBinder abstractBinder
        	= new PolymorphicAuthValueFactoryProvider.Binder<>(
        			ImmutableSet.of(BasicUser.class, JwtUser.class));
        /*
        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<BasicUser>()
                .setAuthenticator(basicAuthenticator)
                .setAuthorizer(new ResourceAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        */
        

		environment.jersey().register(polyAuthDynamicFeature);
		environment.jersey().register(abstractBinder);

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(BasicUser.class));
        
		environment.jersey().register(new Login(configuration.getJwtTokenSecret()));
		environment.jersey().register(new AuthTest());
        
        //-----------------------------------------------------------------
        
		SparqlExecutor qe = configuration.getQueryExecutorFactory().buildQE();

		environment.jersey().register(new PlotStudentSession(qe));
		environment.jersey().register(new PlotStudent(qe));
		environment.jersey().register(new PlotIssue(qe));
		environment.jersey().register(new PlotIssuesOfStudent(qe));
		environment.jersey().register(new PlotSession(qe));
		environment.jersey().register(new ListSessionsClass(qe));
		environment.jersey().register(new ListSessionsOfStudent(qe));
		environment.jersey().register(new ListStudentsOfSession(qe));
		environment.jersey().register(new ListStudentsOfObservation(qe));
		environment.jersey().register(new ListStudentsOfClass(qe));
		environment.jersey().register(new ListObservationsOfSession(qe));
		environment.jersey().register(new ListProfilesOfStudent(qe));
		environment.jersey().register(new ListIndividualProperties(qe));
		environment.jersey().register(new ListClasses(qe));
		environment.jersey().register(new FindUser(qe));

		environment.jersey().register(new SuperSession(qe));
		environment.jersey().register(new InsertStudentIssue(qe));
		environment.jersey().register(new InsertUser(qe));
		environment.jersey().register(new InsertSession(qe));
		environment.jersey().register(new InsertObservation(qe));
		environment.jersey().register(new InsertEmotion(qe));
		environment.jersey().register(new InsertBehaviour(qe));
		environment.jersey().register(new InsertAffect(qe));
		environment.jersey().register(new InsertAcademicProfile(qe));
		environment.jersey().register(new InsertLearningProfile(qe));
		environment.jersey().register(new InsertDiversityProfile(qe));
		environment.jersey().register(new InsertMusicalProfile(qe));
		environment.jersey().register(new InsertClass(qe));
		environment.jersey().register(new InsertVLO(qe));
		environment.healthChecks().register("search", new SearchHealthCheck());
		environment.healthChecks().register("insert", new SearchHealthCheck());
		
	}

}
