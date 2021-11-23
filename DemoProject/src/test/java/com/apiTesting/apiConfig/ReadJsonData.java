package com.apiTesting.apiConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

//import io.restassured.internal.support.FileReader;

public class ReadJsonData {
	
	
	
	// get the data from .json file
	public static Map<String,String> readJsonFile(String methodName) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
	// String filePath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resource"+File.separator+"Payload"+File.separator+"user.json";
	 String filePath="D:\\workspace2\\DemoProject\\src\\test\\resources\\Payload\\user.json";
	 JsonElement jsonele= (new JsonParser()).parse(new FileReader(filePath));
     JsonObject object= jsonele.getAsJsonObject();
     JsonElement ele = object.get(methodName);
     JsonObject obj= ele.getAsJsonObject();
     Map<String, String> map = new HashMap<String, String>();
     Iterator iterator=obj.entrySet().iterator();
     while(iterator.hasNext()) {
    	Entry entry= (Entry) iterator.next();
    	map.put(entry.getKey().toString(), entry.getValue().toString());
     }
	return map;
}
	

}
