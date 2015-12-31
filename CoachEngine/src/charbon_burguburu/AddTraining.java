package charbon_burguburu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exercice;
import model.Training;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

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
		String Training_id = req.getParameter("Training");
		Query q = new Query("Training");

		if(Training_id!=null){
			Key kt = KeyFactory.createKey("Training",Long.parseLong(Training_id));
			Filter filter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,FilterOperator.EQUAL,kt);
			q.setFilter(filter);
		}
		// Récupération du résultat de la requète à l’aide de PreparedQuery
		PreparedQuery pq = datastore.prepare(q);

		Training train = new Training();
		if(pq.countEntities()==0 && Training_id!=null){
			resp.getWriter().println("{}");
			return;
		}

		for (Entity result : pq.asIterable()) {
			String title = (String) result.getProperty("title");
			String description = (String) result.getProperty("description");
			String domaine = (String) result.getProperty("domaine");
			String time = (String) result.getProperty("time");
			train = new Training(title,description,domaine,time);
		}
		q = new Query("Exercice");
		String Exercice_id = req.getParameter("Exercice");

		if(Exercice_id!=null){
			Key ke = KeyFactory.createKey("Exercice",Long.parseLong(Exercice_id));

			Entity d=null;
			try {
				d = datastore.get(ke);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
				resp.getWriter().println("{}");
				return;
			}
			Filter filter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,FilterOperator.EQUAL,ke);
			q.setFilter(filter);
			train = new Training();
			Exercice exercice = new Exercice((String)d.getProperty("title"),(String) d.getProperty("description"),(String) d.getProperty("time"));
			train.addExercice(exercice);
			resp.getWriter().println(train.toJSON().toString());
			return;
		}

		// Récupération du résultat de la requète à l’aide de PreparedQuery
		if(Training_id!=null){
			q.addFilter("training", FilterOperator.EQUAL, Long.parseLong(Training_id));
		}
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
