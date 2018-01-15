package acacia.resources;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

public abstract class Resource {

	protected static ConstantURIs c_uris = new ConstantURIs();
	private SparqlExecutor qe;

	public Resource(SparqlExecutor qe) {
		this.qe = qe;
	}
	
	protected ResultSet executeQuery(String query) throws FileNotFoundException {
		return qe.executeQuery(query);
	}
	
	protected List<Map<String, String>> buildResult(ResultSet rs) throws FileNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    Map<String, String> item;
	    QuerySolution qs;
		List<String> sl;
		while (rs.hasNext()){
			qs = rs.next();
			sl = rs.getResultVars();
			item = new HashMap<String, String>();
			for (String str: sl) {
				Object obj = null;		
				if(qs.get(str) == null) {
					System.out.println("Error: Null results");
					return list;
				}
				if (qs.get(str).isLiteral()) {
					obj = qs.get(str).asLiteral().getValue();
				}
				if (qs.get(str).isResource()) {
					obj = qs.get(str).asResource().getLocalName();
				}
				item.put(str, String.valueOf(obj));
			}
			list.add(item);
		}		
		return list;
	}
	
	protected void executeUpdate(String update) throws FileNotFoundException {
		qe.executeUpdate(update);
	}
	
}