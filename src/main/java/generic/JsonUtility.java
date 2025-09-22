package generic;
import java.io.FileNotFoundException;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Exception {
		FileReader file=new FileReader("./configAppData/JSONDATA.json");
		JSONParser parse=new JSONParser();
		Object obj=parse.parse(file);
		JSONObject map=(JSONObject)obj;
		String data=map.get(key).toString();
		
		
		return data;
	}
	
	


}
