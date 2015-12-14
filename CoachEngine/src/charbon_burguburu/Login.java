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

public class Login extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String login = req.getParameter("login");
			String email =req.getParameter("email");
			
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
			
			resp.getWriter().print("ok");
			

	}

}
