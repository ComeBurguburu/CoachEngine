package model;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Link {
	private String url;
	private String name;
	private String time;

	public Link(String type,String name,Object idT,Object idE,String time){
		this.name=name;
		this.url=generateLink(type,idT,idE);
		this.time = time;

	}
	private String generateLink(String type,Object idT,Object idE){
		StringBuffer link = new StringBuffer();
		if(idE==null){
			link.append("/result-detail-screen.html?").append(type).append("=").append(idT);
		}else{
			link.append("/result-detail-screen.html?").append(type).append("=").append(idE);
		}
		return link.toString();

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
