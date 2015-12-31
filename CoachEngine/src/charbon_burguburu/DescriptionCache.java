package charbon_burguburu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

@SuppressWarnings("serial")
public class DescriptionCache extends HttpServlet {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Entity DescriptionHome=null;
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		
		javax.cache.Cache cache=null;
		
		try {
			// R�cup�ration du Cache
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			// cr�ation/r�cup�ration du cache suivant des propri�t�s sp�cifiques
			
			 cache = cacheFactory.createCache(props);

			// Si aucune propri�t� n'est sp�cifi�e,
			//cr�er/r�cup�rer un cache comme ci-dessous
			//cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			System.out.println("Traitement en cas d'erreur sur la r�cup�ration/configuration du cache");
		}
		//String key = "message";
		
		// Mise en cache de l'objet � l'aide d'une cl�
		// cache.put(key, value);
		// R�cup�ration de l'objet stock�
		Key cleDescription = KeyFactory.createKey("Description", "idDescription");
		
		//value = (String)cache.get(key);
		
		
		toDataStore(" Ceci est une description");
		String key ="message";
		String value=null;
		value=(String) cache.get(key);
		if(value!=null){
			resp.getWriter().write("in cache "+ value);
		}
		else{
			try {
				DescriptionHome = datastore.get(cleDescription);
				String message = (String) DescriptionHome.getProperty("description");
				cache.put(key, message);
				resp.getWriter().write("not in cache"+message);
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				resp.getWriter().write("Pas Normal!");
				e1.printStackTrace();
			}
		}
		
		
				
				
		
				
			
			//Query q = new Query("message");

			
			//PreparedQuery pq=datastore.prepare(q);
			
			

		
			
			
		
		
	}
	public void toDataStore(String description){
		
		DescriptionHome = new Entity("Description","idDescription");
		DescriptionHome.setProperty("description", description);
		datastore.put(DescriptionHome);
	}
}
