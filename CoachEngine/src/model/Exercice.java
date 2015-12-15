package model;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Exercice {
	private String title;
	private String description;
	private String time;

	public Exercice(JSONObject json){
		try {
			this.title=json.getString("Title");
			this.description = json.getString("Description");
			this.time = json.getString("Date");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Exercice(String title, String description, String time) {
		this.title=title;
		this.description=description;
		this.time = time;
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

	public String getTime() {
		return time;
	}

	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		try {
			o.put("Title", title);
			o.put("Description", description);
			o.put("Date", time);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
