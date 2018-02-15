package acacia.dataobjects;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VloObject {
	
	@NotBlank
	private String Name;
	
	private String Description;
	
	@URL
	private String Url;
	
	private String Domain_Lexicon;

	public VloObject(String name, String description, String url, String domain_Lexicon) {
		super();
		Name = name;
		Description = description;
		Url = url;
		Domain_Lexicon = domain_Lexicon;
	}

	public VloObject() {
	}

	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	@JsonProperty("Name")
	public void setName(String name) {
		Name = name;
	}

	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}

	@JsonProperty("Description")
	public void setDescription(String description) {
		Description = description;
	}

	@JsonProperty("URL")
	public String getUrl() {
		return Url;
	}

	@JsonProperty("URL")
	public void setUrl(String url) {
		Url = url;
	}

	@JsonProperty("Domain_Lexicon")
	public String getDomain_Lexicon() {
		return Domain_Lexicon;
	}

	@JsonProperty("Domain_Lexicon")
	public void setDomain_Lexicon(String domain_Lexicon) {
		Domain_Lexicon = domain_Lexicon;
	}
	
}
