package engine.action;

import java.io.*;
import java.util.ArrayList;

public class TextReader {
	
	public static ArrayList<String> getFileTextLines(String location){
		try {
			FileReader fileReader = new FileReader(location);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> lines = new ArrayList<>();
			while(true){
				lines.add(bufferedReader.readLine());
				if(lines.get(lines.size()-1) == null){
					lines.remove(lines.size() - 1);
					break;
				}
			}
			bufferedReader.close();
			fileReader.close();
			return lines;
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public static String getFileText(String location){
		try {
			FileReader fileReader = new FileReader(location);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String lines = "";
			while(true){
				String line = bufferedReader.readLine();
				if(line == null){
					break;
				}
				lines += line + "\n";
			}
			bufferedReader.close();
			fileReader.close();
			return lines;
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}

	public static void write(String location, String e){
		BufferedWriter writer = null;
		try {
			File f = new File(location);
			writer = new BufferedWriter(new FileWriter(f));
			writer.write(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				writer.close();
			}catch (Exception e1){}
		}
	}

}
