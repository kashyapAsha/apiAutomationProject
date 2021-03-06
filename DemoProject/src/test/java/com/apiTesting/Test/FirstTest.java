package com.apiTesting.Test;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.apiTesting.apiConfigs.ApiPath;
import com.apiTesting.apiConfigs.ApiVerification;
import com.apiTesting.apiConfigs.HttpMethods;
import com.apiTesting.apiConfigs.ReadJsonData;
import com.apiTesting.base.Base;
import com.aventstack.extentreports.Status;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

//import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstTest extends Base {
	String baseURL=readFromPropertyFile("url");
	
	@Test()
	public void verifySingleUser() {
		test.log(Status.INFO, "verifySingleUser test is starting......");
		
		
		String url=baseURL+ApiPath.Get_Single_user;
		
		Response re= HttpMethods.getWithoutParam(url);
		re.prettyPrint();
		ApiVerification.responseCodeValidation(re, 200);
		test.log(Status.INFO, "verifySingleUser test is end......");
	}
	
	@Test
	public void createUser() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		test.log(Status.INFO, "createUser test is starting......");
		
		String url=baseURL+ApiPath.Post_Create_User;
		
		Map<String, String>m=new HashMap<String,String>();
		m=ReadJsonData.readJsonFile("createUser");
		
		Response re = HttpMethods.post(url, m);
		ApiVerification.responseCodeValidation(re, 201);
		re.prettyPrint();
		test.log(Status.INFO, "createUser test is end......");
	}
	
	@Test
	public void registerUser() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		test.log(Status.INFO, "registerUser test is starting......");
		
		String url=baseURL+ApiPath.Post_Register_User;
		
		Map<String, String>m=new HashMap<String,String>();
		m=ReadJsonData.readJsonFile("registerUser");
		
		Response re = HttpMethods.post(url, m);
		ApiVerification.responseCodeValidation(re, 200);
		re.prettyPrint();
		test.log(Status.INFO, "registerUser test is end......");
	}

}
