package acacia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class QueryExecutor {
	
	private OntModel ontModel;
	
	public QueryExecutor(String file) throws FileNotFoundException {
		FileInputStream reader = new FileInputStream(new File(file));
		Model model = ModelFactory.createDefaultModel();
		model.read(reader, null);
		ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
	}
	
	public ResultSet executeQuery(String query) {
		QueryExecution exe = QueryExecutionFactory.create(QueryFactory.create(query), ontModel);
		return exe.execSelect();
	}

}
