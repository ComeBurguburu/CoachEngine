package model;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Link {
	private String url;
	private String name;
	
	public Link(String name,Object id){
		this.name=name;
		this.url=generateLink(id);
		
	}
	private String generateLink(Object id){
		return "/addtraining?id="+id;
		
	}
	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		try {
			o.put("name", name);
			o.put("url", url);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return o;
	
	}
}
