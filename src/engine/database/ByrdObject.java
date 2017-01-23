package engine.database;

import java.io.Serializable;

public class ByrdObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Object object;
	
	public ByrdObject(String id, Object object){
		setId(id);
		setObject(object);
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
