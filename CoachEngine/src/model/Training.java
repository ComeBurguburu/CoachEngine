package model;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Training {
	private String title;
	private String description;
	private String domaine;
	private Long time;
	private ArrayList<Exercice> exercices;

	public Training(JSONObject json){
		
		try {
			this.title=json.getString("Title");
			this.description = json.getString("Description");
			this.domaine=json.getString("Domain");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONArray jsonArray = json.getJSONArray("Exercice");
			if (jsonArray != null) { 
				int len = jsonArray.length();
				this.exercices = new ArrayList<Exercice>();
				for (int i=0;i<len;i++){ 
					this.exercices.add(new Exercice((JSONObject) jsonArray.get(i)));
				} 
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Training(String title, String description, String domaine) {
		this.title=title;
		this.description=description;
		this.domaine=domaine;
		this.exercices = new ArrayList<Exercice>();
	}
	public Training() {
		this.exercices = new ArrayList<Exercice>();
	}

	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		try {
			o.put("Title",title);
			o.put("Description",description);
			o.put("Domaine",domaine);
			o.put("Time", time);
			o.put("Exercice", this.getJSONArray());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Exercice> getExercices() {
		return exercices;
	}
	private JSONArray getJSONArray(){
		JSONArray a = new JSONArray();
		Iterator<Exercice> it = this.exercices.iterator();
		while(it.hasNext()){
			a.put(it.next().toJSON());
		}
		return a;
	}


	public void setExercices(ArrayList<Exercice> exercices) {
		this.exercices = exercices;
	}

	public String getDomaine() {
		return this.domaine;
	}

	public void addExercice(Exercice ex) {
		this.exercices.add(ex);	
	}

}
