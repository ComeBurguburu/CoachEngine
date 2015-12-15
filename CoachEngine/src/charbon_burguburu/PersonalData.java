package charbon_burguburu;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class PersonalData extends HttpServlet{
	

	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Entity userExerciceDataEntity = null;
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		String userExerciceData = req.getParameter("userExerciceData");
		JSONObject userExerciceDataObject;
		try {
			userExerciceDataObject = new JSONObject(userExerciceData);
		
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
}
