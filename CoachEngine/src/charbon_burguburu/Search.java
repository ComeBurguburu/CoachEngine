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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String keyword = req.getParameter("q");
		JSONObject response = new JSONObject();
		try {
			response.put("training", new JSONArray());
			response.put("exercice", new JSONArray());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Query q = new Query("Training");
		q.addFilter("title", Query.FilterOperator.EQUAL, keyword);
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			String title = (String) result.getProperty("title");
			String id = (String) result.getProperty("ID/Name");
			String time=(String)result.getProperty("time");

			try {
				response.getJSONArray("training").put(new Link(title,id,time).toJSON());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		q = new Query("Exercice");
		q.addFilter("title", Query.FilterOperator.EQUAL, keyword);
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String title = (String) result.getProperty("title");
			Object id = result.getProperty("training");
			String time=(String)result.getProperty("time");
			
			try {
				response.getJSONArray("exercice").put(new Link(title,id,time).toJSON());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resp.getWriter().print(response.toString());
	}

}
