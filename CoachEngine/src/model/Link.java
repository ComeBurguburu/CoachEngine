package model;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Link {
	private String url;
	private String name;
	private String time;
	
	public Link(String name,Object id,String time){
		this.name=name;
		this.url=generateLink(id);
		this.time = time;
		
	}
	private String generateLink(Object id){
		return "/addtraining?id="+id;
		
	}
	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		try {
			o.put("name", name);
			o.put("url", url);
			o.put("time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return o;
	
	}
}
