package com.apiTesting.apiConfigs;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.apiTesting.base.Base;
import com.aventstack.extentreports.Status;
//import com.google.gson.JsonArray;

import io.restassured.response.Response;

public class ApiVerification extends Base {
	
	
	public static void responseCodeValidation(Response response, int statusCode) {
		try {
			Assert.assertEquals(response.getStatusCode(), statusCode, "Not getting expected status code");
			test.log(Status.PASS,
					"Successfully validdated status code, status code is :: " + response.getStatusCode());
			
		/*} catch (AssertionError e) {
			test.log(Status.FAIL, e.fillInStackTrace());
			test.log(Status.FAIL,
					"Expected status code is :: " + statusCode + " , instead of getting :: " + response.getStatusCode());
			e.printStackTrace();*/
		}catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
	}
}
	
	public static void responseTimeValidation(Response response) {
		try {
			long time=response.time();
			test.log(Status.INFO, "Api response time is :: " + time);
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
	}
	
	public static void responseKeyValidationfromArray(Response response, String key) {
		try {
			JSONArray array = new JSONArray(response.getBody().asString());
			for(int i=0; i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				Assert.assertEquals(obj.get(key).equals(key), true);
				test.log(Status.PASS, "Validetd values are  " + obj.get(key));
				
			}
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
	}
	
	public static void responseKeyValidationFromJsonObject(Response response, String key) {
		try {
			JSONObject json = new JSONObject(response.getBody().asString());
			if(json.has(key) && json.get(key)!= null) {
				Assert.assertEquals(json.get(key), key, "Not getting expected key");
				test.log(Status.PASS, "Sucessfully validated value of " + key + " It is " + json.get(key));
			}
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
	}
	
	public static void responseKeyValidationFromResponseBody(Response response, String key) {
		try {
			String getBody=response.body().asString();
			Assert.assertEquals(getBody.contains(key), true);
			test.log(Status.PASS, "Sucessfully validated : " + key);
			//LOGGER.info("Sucessfully validated : " + key);
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
			//LOGGER.equals(e);
		}
	}
	
	public static void responseKeyValidationFromJsonpath(Response response, String key, String value) {
		try {
			String getKey=response.jsonPath().get(key);
			Assert.assertEquals(getKey.equals(value), true);
			test.log(Status.PASS, "Sucessfully validated value of: " + key);
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
	}
	
}
