package charbon_burguburu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Link;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Search extends HttpServlet{
	private 	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	private void search(String table,String key,String keyword,JSONObject response){
		Query q = new Query(table);
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			String title = (String) result.getProperty("title");
			Object idT = result.getProperty("training");
			Object idE = result.getKey().getId();
			
			String time=(String)result.getProperty("time");

			try {
				if(title.indexOf(keyword)!=-1){
					response.getJSONArray(table).put(new Link(table,title,idT,idE,time).toJSON());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String search = req.getParameter("q");
		JSONObject response = new JSONObject();
		try {
			response.put("Training", new JSONArray());
			response.put("Exercice", new JSONArray());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String []keywords = search.split(" ");
		for(String keyword:keywords){
			search("Training","title",keyword,response);
			search("Exercice","title",keyword,response);
		}
		resp.getWriter().print(response.toString());
	}

}
