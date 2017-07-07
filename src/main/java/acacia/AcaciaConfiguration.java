package acacia;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

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
	
}
