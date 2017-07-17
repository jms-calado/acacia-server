package acacia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;

public class SparqlExecutor {
	
	private OntModel ontModel;
	private File ontFile;
	
	public SparqlExecutor(String file) throws FileNotFoundException {
		ontFile = new File(file);
		FileInputStream reader = new FileInputStream(ontFile);
		Model model = ModelFactory.createDefaultModel();
		model.read(reader, null);
		ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
	}

	/*//https://svn.apache.org/repos/asf/jena/branches/jena-owl2/doc/ontology/common-problems.html
	public SparqlExecutor(String file1) throws FileNotFoundException {
		String file2 = "resources/ontologyClasses.owl";
		FileInputStream reader1 = new FileInputStream(new File(file1));
		FileInputStream reader2 = new FileInputStream(new File(file2));
				
		Model aBox = ModelFactory.createDefaultModel();
		aBox.read(reader1, null);
		Model tBox = ModelFactory.createDefaultModel();
		tBox.read(reader2, null);

		Reasoner reasoner = ReasonerRegistry.getOWLReasoner().bindSchema(tBox);

		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM_RULE_INF);
		spec.setReasoner(reasoner);
		ontModel = ModelFactory.createOntologyModel(spec, aBox);
	}*/
	
	public ResultSet executeQuery(String query) {
		QueryExecution exe = QueryExecutionFactory.create(QueryFactory.create(query), ontModel);
		return exe.execSelect();
	}
	
	public void executeUpdate(String update) {
		UpdateAction.parseExecute(update, ontModel);
		
		FileWriter out = null;
		try {
			out = new FileWriter(ontFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ontModel.write(out, "RDF/XML");
		}
		finally {
		   try {
		       out.close();
		   }
		   catch (IOException closeException) {
				closeException.printStackTrace();
		   }
		}
	}
	/*
	public void exeUpdate(UpdateRequest request){
	UpdateAction.execute(request, ontModel);
	}*/

}
