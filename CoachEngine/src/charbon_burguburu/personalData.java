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
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class personalData extends HttpServlet{
	

	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		String userExerciceData = req.getParameter("userExerciceData");
		JSONObject userExerciceDataObject = new JSONObject(userExerciceData);	
		
		Entity userExerciceDataEntity = new Entity("userExerciceData");
	
		userExerciceDataEntity.setProperty("date", userExerciceDataObject.date);
		userExerciceDataEntity.setProperty("idUser",userExerciceDataObject.idUser);
		userExerciceDataEntity.setProperty("planTitle", userExerciceDataObject.planTitle);
		userExerciceDataEntity.setProperty("exerciceTitle", userExerciceDataObject.exerciceTitle);
		userExerciceDataEntity.setProperty("status", userExerciceDataObject.status);
		
		datastore.put(userExerciceDataEntity);

	}
}
