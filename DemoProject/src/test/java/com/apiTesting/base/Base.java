package com.apiTesting.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.apiTesting.Listner.ExtentListner;
import com.apiTesting.Listner.ExtentManager;
import com.apiTesting.apiConfigs.ApiPath;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;







@Listeners(ExtentListner.class)
public class Base extends ExtentListner {
	public static  Logger LOGGER = Logger.getLogger(Base.class);
	public  String access_Token="";
	static String workingDir = System.getProperty("user.dir");
	static Properties prop = null;
	static InputStream input = null;
	FileInputStream file = null;
	static String pathToPropertyFolder = workingDir + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "property"+File.separator ;
	static String configData = pathToPropertyFolder + "config.properties";
	
	public static String readFromPropertyFile(String key) {
		try {
			prop = new Properties();
			input = new FileInputStream(configData);
			prop.load(input);
		} catch (Exception e) {
			//logger.info("Unable to read file : " + appsetUpData);
		}
		return prop.getProperty(key);
	}
	
	@BeforeSuite
	 public  void BeforeSuite() {
	  ExtentManager.setExtent();
	 }
	
	
	
	@BeforeClass
		public void getAccessToken() {
		
			String baseURL=readFromPropertyFile("baseUrl");
			String url=baseURL+ApiPath.Post_Generate_Token;
			//LOGGER.info(url);
			Response re= RestAssured.given().queryParams("grant_type", "password")
					.param("username", "mukul.shishodia")
					.param("password", "google#123")
					.auth().basic("colombia-1","secret")
					.log().all().post(url);
			re.prettyPrint();
			//LOGGER.info("Getting response");
			String access_Token = re.jsonPath().get("access_token");
			//LOGGER.info("Successfully get token="+access_Token);
			System.out.println("token is="+access_Token);
			 this.access_Token=access_Token;	
		}
	
	 
	 @AfterSuite
	 public void AfterSuite() {
	  ExtentManager.endReport();
	 }

	

	
	

}
