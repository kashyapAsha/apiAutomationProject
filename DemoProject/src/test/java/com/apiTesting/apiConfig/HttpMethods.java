package com.apiTesting.apiConfig;
import java.util.Map;

import com.apiTesting.base.Base;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpMethods extends Base {
	//all https methods
	// get method without parameters
	public static Response getWithoutParam(String url) {
		Response res=null;
		try {
			res=RestAssured.given().log().all()
					.when().get(url);
			test.log(Status.PASS,"Successfully getting the response");
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		return res;
		
	}
	// get method with query parameters
	public static Response getWithQueryParam(String url, String key, String id) {
		Response res=null;
		 try {
			res=RestAssured.given().queryParams(key, id)
					.log().all()
					.when().get(url);
			test.log(Status.PASS,"Successfully getting the response");
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		return res;
	}
	//post method
	public static Response post(String url, Map<String, String> m) {
		Response res=null;
		 try {
			res=RestAssured.given()
					.log().all()
					.body(m)
					.when().post(url);
			test.log(Status.PASS,"Successfully getting the response");
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		return res;
	}
	//get method with bearer auth
	public static Response getWithAuthentication(String token,String url) {
		Response res=null;
		try {
			res=RestAssured.given().header("Authorization", "Bearer "+token).log().all()
					.when().get(url);
		
			test.log(Status.PASS,"Successfully getting the response");
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		return res;
		
	}
	
	public static Response postWithParam(String paramKey, String paramValue,String token,String url) {
		Response res=null;
		 try {
			res=RestAssured.given().param(paramKey,paramValue)
					.header("Authorization", "Bearer "+token)
					.log().all()
					.when().post(url);
			test.log(Status.PASS,"Successfully getting the response");
		} catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	

}
