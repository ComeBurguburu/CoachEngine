package charbon_burguburu;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exercice;
import model.Training;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AddTraining extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String training = req.getParameter("training");
		System.out.println("doPost add Training");		
		if(training == null){
			return;
		}
		Queue queue = QueueFactory.getDefaultQueue();
	    queue.add(TaskOptions.Builder.withUrl("/addtrainingqueue").param("training",training));
	    System.out.println("launch task in queue");
	}
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Query q = new Query("Training");
		//q.addFilter("ID/Name", Query.FilterOperator.EQUAL, trainingkey);
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		PreparedQuery pq = datastore.prepare(q);
		Training train = new Training();
		
		for (Entity result : pq.asIterable()) {
		 String title = (String) result.getProperty("title");
		 String description = (String) result.getProperty("description");
		 String domaine = (String) result.getProperty("domaine");
		 String time = (String) result.getProperty("time");
		train = new Training(title,description,domaine,time);
		}
		q = new Query("Exercice");
		//q.addFilter("training", Query.FilterOperator.EQUAL, train.getTrainingKey());
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
		 String title = (String) result.getProperty("title");
		 String description = (String) result.getProperty("description");
		 String date=(String)result.getProperty("time");
		 Exercice ex = new Exercice(title,description,date);
		 train.addExercice(ex);
		}
		resp.getWriter().print(train.toJSON().toString());
	}

}
