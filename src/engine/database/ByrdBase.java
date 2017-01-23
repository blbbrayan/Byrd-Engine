package engine.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ByrdBase implements Serializable{
	private static final long serialVersionUID = 1L;
		
	public static void config(String root, String[] directories){
		for(String e: directories){
			createByrdBase(root, e);
		}
	}
	
	private static void createByrdBase(String saveLocation, String name){
		if(!checkLocation(saveLocation, name)){
			try {
				String path = saveLocation + "/" + name;
				path = path.substring(0, path.lastIndexOf("/"));
				new File(path).mkdirs();
				FileOutputStream saveFile = new FileOutputStream(saveLocation + "/" + name + ".bb");
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				
				ByrdGroup objects = new ByrdGroup();
				save.writeObject(objects);

				save.close();
				saveFile.close();
				System.out.println("ByrdBase created at \"" + saveLocation + "/" + name + ".bb\"");
			} catch (Exception exc) {
				System.out.println("ByrdBase Error: Could not create a ByrdBase - Error printing below");
				exc.printStackTrace();
			}
		}else{
			System.out.println("ByrdBase  \"" + saveLocation + "/" + name + ".bb\" loaded");
		}
	}
	
	public static boolean checkLocation(String saveLocation, String name){
		File bBase = new File(saveLocation + "/" + name + ".bb");
		return bBase.exists();
	}
	
	public static void save(String saveLocation, String name, String objectId, Object object, boolean overwrite) {
		try {
			new File(saveLocation).mkdirs();
			ByrdGroup objects = loadByrdGroup(saveLocation, name);
			
			FileOutputStream saveFile = new FileOutputStream(saveLocation + "/" + name + ".bb");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			boolean add = false;
			
			if (objects.size() > 0){
				for(ByrdObject byrdObject: objects){
					if(byrdObject.getId().equals(objectId)){
						if(overwrite){
							byrdObject.setObject(object);
						}
						break;
					}else{
						add = true;
					}
				}
			}else{
				add = true;
			}
			
			save.writeObject(objects);

			save.close();
			saveFile.close();
			if(add){
				add(saveLocation, name, objectId, object);
			}
		} catch (Exception exc) {
			System.out.println("ByrdBase Error: Could not save ByrdBase");
		}
	}
	
	private static ByrdGroup loadByrdGroup(String saveLocation, String name) {
		try {
			FileInputStream saveFile = new FileInputStream(saveLocation + "/" + name + ".bb");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			ByrdGroup objects = (ByrdGroup) save.readObject();
			
			save.close();
			saveFile.close();
			return objects;
		} catch (Exception exc) {
			if(!checkLocation(saveLocation, name))
				System.out.println("ByrdBase Error: The ByrdBase does not exist");
			exc.printStackTrace();
			return null;
		}
	}
	
	private static void saveByrdGroup(String saveLocation, String name, ByrdGroup objects) {
		try {
			new File(saveLocation).mkdirs();
			FileOutputStream saveFile = new FileOutputStream(saveLocation + "/" + name + ".bb");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(objects);

			save.close();
			saveFile.close();
		} catch (Exception exc) {
			System.out.println("ByrdBase Error: Could not save ByrdBase");
		}
	}
	
	public static Object[] loadAll(String saveLocation, String name) {
		try {
			FileInputStream saveFile = new FileInputStream(saveLocation + "/" + name + ".bb");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			ByrdGroup byrdObjects = (ByrdGroup) save.readObject();
			Object[] objects = new Object[byrdObjects.size()];
			for(int i = 0; i < byrdObjects.size(); i++){
				objects[i] = byrdObjects.get(i).getObject();
			}
			
			save.close();
			saveFile.close();
			return objects;
		} catch (Exception exc) {
			if(checkLocation(saveLocation, name))
				System.out.println("ByrdBase Error: The requested object does not exist");
			else
				System.out.println("ByrdBase Error: The ByrdBase does not exist");
			exc.printStackTrace();
			return null;
		}
	}
	
	public static Object load(String saveLocation, String name, String objectId) {
		try {
			FileInputStream saveFile = new FileInputStream(saveLocation + "/" + name + ".bb");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			ByrdGroup objects = (ByrdGroup) save.readObject();
			
			Object obj = null;
			
			for(ByrdObject bObj: objects){
				if(bObj.getId().equals(objectId)){
					obj = bObj.getObject();
					break;
				}
			}
			
			save.close();
			saveFile.close();
			return obj;
		} catch (Exception exc) {
			if(checkLocation(saveLocation, name))
				System.out.println("ByrdBase Error: The requested object does not exist");
			else
				System.out.println("ByrdBase Error: The ByrdBase does not exist");
			return null;
		}
	}
	
	private static void add(String saveLocation, String name, String objectId, Object object){
		try {
			FileInputStream saveFile = new FileInputStream(saveLocation + "/" + name + ".bb");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			ByrdGroup objects = (ByrdGroup) save.readObject();
			
			boolean doSave = false;
			
			if(isUnique(objects, objectId)){
				objects.add(new ByrdObject(objectId, object));
				doSave = true;
			}
			
			save.close();
			saveFile.close();
			if(doSave){
				saveByrdGroup(saveLocation, name, objects);
			}
		} catch (Exception exc) {
			System.out.println("ByrdBase Error: the ByrdBase does not exist");
			exc.printStackTrace();
		}
	}
	
	public static void remove(String saveLocation, String name, String objectId){
		try { // load servers
			FileInputStream saveFile = new FileInputStream(saveLocation + "/" + name + ".bb");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			ByrdGroup byrdObjects = (ByrdGroup) save.readObject();
			
			boolean doSave = false;
			
			for(ByrdObject bObj: byrdObjects){
				if(bObj.getId().equals(objectId)){
					byrdObjects.remove(bObj);
					doSave = true;
					break;
				}
			}
			
			save.close();
			saveFile.close();
			
			if(doSave){
				saveByrdGroup(saveLocation, name, byrdObjects);
			}
		} catch (Exception exc) {
			if(checkLocation(saveLocation, name))
				System.out.println("ByrdBase Error: The requested object does not exist");
			else
				System.out.println("ByrdBase Error: The ByrdBase does not exist");
			exc.printStackTrace();
		}
	}
	
	public static void deleteByrdBase(String saveLocation, String name){
		try {
			new File(saveLocation + "/" + name + ".bb").delete();
		} catch (Exception exc) {
			System.out.println("ByrdBase Error: Could not delete");
			exc.printStackTrace();
		}
	}
	
	public static boolean isUnique(ByrdGroup objects, String objectId){
		for(ByrdObject bObj: objects){
			if(bObj.getId().equals(objectId)){
				return false;
			}
		}
		return true;
	}
	
}
