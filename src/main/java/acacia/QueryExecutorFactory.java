package acacia;

import java.io.FileNotFoundException;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import acacia.services.QueryExecutor;

public class QueryExecutorFactory {
	
	@NotEmpty
    private String file;

	@JsonProperty
	public String getFile() {
		return file;
	}

	@JsonProperty
	public void setFile(String file) {
		this.file = file;
	}
	
	public QueryExecutor build() throws FileNotFoundException {
		return new QueryExecutor(getFile());
	}

}
