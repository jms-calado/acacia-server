package acacia;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOW_CREDENTIALS_PARAM;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.websocket.server.ServerEndpointConfig;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import acacia.dataobjects.GlobalVar;
import acacia.health.SearchHealthCheck;
import acacia.resources.FindUser;
import acacia.resources.InsertAffect;
import acacia.resources.InsertBehaviour;
import acacia.resources.InsertEmotion;
import acacia.resources.InsertObservation;
import acacia.resources.InsertSession;
import acacia.resources.InsertUser;
import acacia.resources.ListClasses;
import acacia.resources.ListIndividualProperties;
import acacia.resources.ListObservationsOfSession;
import acacia.resources.ListStudentsOfObservation;
import acacia.resources.ListStudentsOfSession;
import acacia.resources.TestFind;
import acacia.services.SparqlExecutor;
import acacia.websocket.DeviceWebSocketServer;
import be.tomcools.dropwizard.websocket.WebsocketBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AcaciaApplication extends Application<AcaciaConfiguration> {
    private WebsocketBundle websocket = new WebsocketBundle();

	public static void main(String[] args) throws Exception {
		new AcaciaApplication().run(args);
	}
	
	@Override
	public String getName() {
		return "acacia";
	}

    @Override
    public void initialize(Bootstrap<AcaciaConfiguration> bootstrap) {
        GlobalVar.GlobalID = 0;
        super.initialize(bootstrap);
        bootstrap.addBundle(websocket);
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

		SparqlExecutor qe = configuration.getQueryExecutorFactory().buildQE();
		
		environment.jersey().register(new ListStudentsOfSession(qe));
		environment.jersey().register(new ListStudentsOfObservation(qe));
		environment.jersey().register(new ListObservationsOfSession(qe));
		environment.jersey().register(new ListIndividualProperties(qe));
		environment.jersey().register(new ListClasses(qe));
		environment.jersey().register(new FindUser(qe));
		environment.jersey().register(new InsertUser(qe));
		environment.jersey().register(new InsertSession(qe));
		environment.jersey().register(new InsertObservation(qe));
		environment.jersey().register(new InsertEmotion(qe));
		environment.jersey().register(new InsertBehaviour(qe));
		environment.jersey().register(new InsertAffect(qe));
		//environment.jersey().register(new TestFind(qe));
		environment.healthChecks().register("search", new SearchHealthCheck());
		environment.healthChecks().register("insert", new SearchHealthCheck());
		
	}

}
