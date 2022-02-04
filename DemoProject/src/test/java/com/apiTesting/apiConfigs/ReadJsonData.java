package com.apiTesting.apiConfigs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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
	
	public static Map<String,Object> readJsonFile(String filename,String methodName)  {
		
		 Map<String, Object> map=null;
		try {
			String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\Payload\\"+filename+".json";
			 JsonElement jsonele= (new JsonParser()).parse(new FileReader(filePath));
			 JsonObject jsonBody= jsonele.getAsJsonObject();
			 JsonElement methodBody = jsonBody.get(methodName);
			 JsonObject obj = null;
			 
			 if(methodBody.isJsonArray()) {
				 JsonArray arr=methodBody.getAsJsonArray();
				
				 for (int i = 0; i < arr.size(); i++) {
					  obj = arr.get(i).getAsJsonObject();
					 
				 } 
			 }
			 else {
             obj= methodBody.getAsJsonObject();
			 }
			 
			 map = new HashMap<String, Object>();
			 String s=obj.toString();
				GsonBuilder gb= new GsonBuilder();
				gb.setPrettyPrinting();
				Gson gson1=gb.create();
				map=gson1.fromJson(s, Map.class);
					
				 
//			 Iterator iterator=obj.entrySet().iterator();
//			 while(iterator.hasNext()) {
//				Entry entry= (Entry) iterator.next();
//				if(entry.getValue().toString().contains("\"")) {
//				map.put(entry.getKey().toString(), entry.getValue().toString().replace("\"", ""));
//			   }
//	
//				
//				else {
//					map.put(entry.getKey().toString(), entry.getValue().toString());
//				}
//				
//			}
			
				//LOGGER.info("Successfully get the value from json file"); 
		} catch (JsonIOException e) {
			e.printStackTrace();
			//LOGGER.info(e.getMessage());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			//LOGGER.info(e.getMessage());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//LOGGER.info(e.getMessage());
			
		} catch(Exception e) {
			e.printStackTrace();
			//LOGGER.info(e.getMessage());
		}
		return map;
	}
	
	public static Map<String, Object>  readJsonFile1(String filename) throws JsonParseException, JsonMappingException, IOException {
		
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\Payload\\"+filename+".json";
		
		Map<String,Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		map = mapper.readValue(new File(filePath), new TypeReference<Map<String,Object>>(){});
		
		return map ;			
	}
	

	
	

}
