package engine.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Saver {
	
	public static void save(String saveLocation, String name, Object obj) {
		try {
			new File(saveLocation).mkdirs();
			FileOutputStream saveFile = new FileOutputStream(saveLocation + name);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(obj);

			save.close();
			saveFile.close();
		} catch (Exception exc) {
			System.out.println("Saving Error: could not save");
		}
	}
	
	public static boolean checkLocation(String saveLocation, String name){
		File obj = new File(saveLocation + name);
		return obj.exists();
	}
	
	public static Object load(String saveLocation, String name) {
		try {
			new File(saveLocation).mkdirs();
			FileInputStream saveFile = new FileInputStream(saveLocation + name);
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			Object obj = save.readObject();

			save.close();
			saveFile.close();
			return obj;
		} catch (Exception exc) {
			System.out.println("Loading Error: could not load");
			return null;
		}
	}
	
}
