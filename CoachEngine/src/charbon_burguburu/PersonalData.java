package charbon_burguburu;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;


public class PersonalData extends HttpServlet{
	

	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Entity userExerciceDataEntity = null;
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("On est dans le POST");
		String userExerciceData = req.getParameter("userExerciceData");
		System.out.println(userExerciceData);
		JSONObject userExerciceDataObject;
		try {
			userExerciceDataObject = new JSONObject(userExerciceData);
			System.out.println(userExerciceData);
			userExerciceDataEntity = new Entity("userExerciceDataEntity");
			
			userExerciceDataEntity.setProperty("date", userExerciceDataObject.getString("date"));
			userExerciceDataEntity.setProperty("idUser",userExerciceDataObject.getString("idUser"));
			userExerciceDataEntity.setProperty("planTitle", userExerciceDataObject.getString("planTitle"));
			userExerciceDataEntity.setProperty("exerciceTitle", userExerciceDataObject.getString("exerciceTitle"));
			userExerciceDataEntity.setProperty("status", userExerciceDataObject.getString("status"));
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		datastore.put(userExerciceDataEntity);

	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Query q = new Query("userExerciceDataEntity");
		System.out.println("On est dans le get");
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		PreparedQuery pq = datastore.prepare(q);
		 
		for (Entity result : pq.asIterable()) {
			System.out.println("On est dans le for");
			 String date = (String) result.getProperty("date");
			 String idUser = (String) result.getProperty("idUser");
			 String planTitle = (String) result.getProperty("planTitle");
			 String exerciceTitle = (String) result.getProperty("exerciceTitle");
			 String status = (String) result.getProperty("status");
			 JSONObject  userExerciceData = new JSONObject();
			 try {
				 System.out.println("On est dans le try");
				 userExerciceData.put("date", date);
				 userExerciceData.put("idUser", idUser);
				 userExerciceData.put("planTitle", planTitle);
				 userExerciceData.put("exerciceTitle", exerciceTitle);
				 userExerciceData.put("status", status);	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			 resp.getWriter().print(userExerciceData.toString());
			 break;
		}
		
		
	}
}
