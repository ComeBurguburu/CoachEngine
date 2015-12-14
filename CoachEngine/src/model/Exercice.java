package model;

import java.util.Collection;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Exercice {
	private String title;
	private String description;
	private Long time;

	public Exercice(JSONObject json){
		try {
			this.title=json.getString("title");
			this.description = json.getString("description");
			this.time = json.getLong("time");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Exercice(String title, String description) {
		this.title=title;
		this.description=description;
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

	public Long getTime() {
		return time;
	}

	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		try {
			o.put("title", title);

			o.put("description", description);
			o.put("time", time);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
