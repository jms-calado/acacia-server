package acacia;

import java.io.FileNotFoundException;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import acacia.services.SparqlExecutor;

public class SparqlExecutorFactory {
	
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
	
/*	@NotEmpty
    private String file2;

	@JsonProperty
	public String getFile2() {
		return file2;
	}

	@JsonProperty
	public void setFile2(String file2) {
		this.file2 = file2;
	}*/
	
	public SparqlExecutor buildQE() throws FileNotFoundException {
		return new SparqlExecutor(getFile());
	}

}
