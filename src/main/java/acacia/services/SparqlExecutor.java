package acacia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;

import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.GlobalVar;

public class SparqlExecutor {
	
	private OntModel ontModel;
	private File ontFile;
	
	public SparqlExecutor(String file) throws FileNotFoundException {
		ontFile = new File(file);
		FileInputStream reader = new FileInputStream(ontFile);
		Model model = ModelFactory.createDefaultModel();
		model.read(reader, null);
		ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
		
		//Set Highest Observation ID:
		String query = ConstantURIs.prefixes
				+ "SELECT ?obsid "
                + "WHERE {"
                + "?x acacia:Observation_ID ?obsid . "
                + "}"
                + "ORDER BY DESC(?obsid) LIMIT 1";

		ResultSet rs = executeQuery(query);
		
		QuerySolution qs = rs.next();
		List<String> sl = rs.getResultVars();
		for (String str: sl) {
			Object obj = qs.get(str).asLiteral().getValue();
			GlobalVar.GlobalObservationID = Integer.parseInt(String.valueOf(obj));
			System.out.println("Global Observation ID: " + String.valueOf(GlobalVar.GlobalObservationID));	
		}

		//Set Highest BasicUser ID:
		String query2 = ConstantURIs.prefixes
				+ "SELECT ?id "
                + "WHERE {"
                + "?x acacia:User_ID ?id . "
                + "}"
                + "ORDER BY DESC(?id) LIMIT 1";

		ResultSet rs2 = executeQuery(query2);
		
		QuerySolution qs2 = rs2.next();
		List<String> sl2 = rs2.getResultVars();
		for (String str2: sl2) {
			Object obj2 = qs2.get(str2).asLiteral().getValue();
			GlobalVar.GlobalUserID = Integer.parseInt(String.valueOf(obj2));
			System.out.println("Global BasicUser ID: " + String.valueOf(GlobalVar.GlobalUserID));	
		}

		//Set Highest Session ID:
		String query3 = ConstantURIs.prefixes
				+ "SELECT ?session_id "
                + "WHERE {"
                + "?session acacia:Session_ID ?session_id . "
                + "}"
                + "ORDER BY DESC(?session_id) LIMIT 1";

		ResultSet rs3 = executeQuery(query3);
		
		QuerySolution qs3 = rs3.next();
		List<String> sl3 = rs3.getResultVars();
		for (String str3: sl3) {
			Object obj3 = qs3.get(str3).asLiteral().getValue();
			GlobalVar.GlobalSessionID = Integer.parseInt(String.valueOf(obj3));
			System.out.println("Global Session ID: " + String.valueOf(GlobalVar.GlobalSessionID));	
		}
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
