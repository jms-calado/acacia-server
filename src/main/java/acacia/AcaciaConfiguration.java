package acacia;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AcaciaConfiguration extends Configuration {
	
	@Valid
	@NotNull
	private SparqlExecutorFactory queryExecutorFactory = new SparqlExecutorFactory();

	@JsonProperty("ontology")
	public SparqlExecutorFactory getQueryExecutorFactory() {
		return queryExecutorFactory;
	}

	@JsonProperty("ontology")
	public void setQueryExecutorFactory(SparqlExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = queryExecutorFactory;
	}

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	
    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    @NotEmpty
    //@JsonProperty("jwtTokenSecret")
    private String jwtTokenSecret;

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
}
}
