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

public class AddTrainingQueue extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String training = req.getParameter("training");
		System.out.println("doPost add Training queue");		
		if(training == null){
			System.err.println("error queue received nothing");
			return;
		}
		
		
		try {
			JSONObject json_training = new JSONObject(training);
			Training t=new Training(json_training);
			Long TrainingId = addTraining(t);
			 Iterator<Exercice> it = t.getExercices().iterator();
			
			while(it.hasNext()){
				addExercice(it.next(),TrainingId);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
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
