package charbon_burguburu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Login extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String login = req.getParameter("login");
			String email =req.getParameter("email");
			String picture =req.getParameter("picture");
			String id = req.getParameter("id");
						
			CacheFactory cacheFactory = null;
			Cache cache = null;
			Map props = new HashMap();
			props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
			
			try {
				cacheFactory = CacheManager.getInstance().getCacheFactory();
				cache = cacheFactory.createCache(props);
			} catch (CacheException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			cache.put("login", login);
			cache.put("email", email);
			cache.put("picture", picture);
			cache.put("id", id);
			
			resp.getWriter().print("ok");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CacheFactory cacheFactory = null;
		Cache cache = null;
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		JSONObject json_object = new JSONObject();
		
		try {
			cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(props);
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.getWriter().write(json_object.toString());
			return;
		}

		String login = (String) cache.get("login");
		String email = (String) cache.get("email");
		String picture = (String) cache.get("picture");
		String id= (String)cache.get("id");
		
		try{
			json_object.put("login", login);
			json_object.put("email", email);
			json_object.put("picture", picture);
			json_object.put("id",id);
		}catch(Exception e){
			e.printStackTrace();
		}
		resp.getWriter().print(json_object.toString());
		
		
	}
	

}
