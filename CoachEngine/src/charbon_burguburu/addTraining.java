package charbon_burguburu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

public class addTraining extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
/*	public void toDataStore(String description){
		
		DescriptionHome = new Entity("Description","idDescription");
		DescriptionHome.setProperty("description", description);
		datastore.put(DescriptionHome);
	}*/

}
