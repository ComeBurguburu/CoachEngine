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
		
		try {
			JSONObject json_training = new JSONObject(training);
			Training t=new Training(json_training);
			Long TrainingId = addTraining(t);
			 Iterator<Exercice> it = t.getExercices().iterator();
			
			while(it.hasNext()){
				addExercice(it.next(),TrainingId);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Query q = new Query("Training");
		//q.addFilter("ID/Name", Query.FilterOperator.EQUAL, trainingkey);
		// R�cup�ration du r�sultat de la requ�te � l�aide de PreparedQuery
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
		// R�cup�ration du r�sultat de la requ�te � l�aide de PreparedQuery
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



	public void addExercice(Exercice exercice,Long trainingId){

		Entity DescriptionHome = new Entity("Exercice");
		DescriptionHome.setProperty("title", exercice.getTitle());
		DescriptionHome.setProperty("description",exercice.getDescription());
		DescriptionHome.setProperty("time",exercice.getTime());
		DescriptionHome.setProperty("training",trainingId);

		datastore.put(DescriptionHome);
	}
	public long addTraining(Training training){

		Entity DescriptionHome = new Entity("Training");
		DescriptionHome.setProperty("title", training.getTitle());
		DescriptionHome.setProperty("description",training.getDescription());
		DescriptionHome.setProperty("domaine", training.getDomaine());
		DescriptionHome.setProperty("time", training.getTime());
		datastore.put(DescriptionHome);
		return DescriptionHome.getKey().getId();
	}

}
