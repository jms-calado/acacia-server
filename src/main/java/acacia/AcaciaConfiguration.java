package acacia;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class AcaciaConfiguration extends Configuration {
	
	@Valid
	@NotNull
	private QueryExecutorFactory queryExecutorFactory = new QueryExecutorFactory();

	@JsonProperty("ontology")
	public QueryExecutorFactory getQueryExecutorFactory() {
		return queryExecutorFactory;
	}

	@JsonProperty("ontology")
	public void setQueryExecutorFactory(QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = queryExecutorFactory;
	}
	
}
