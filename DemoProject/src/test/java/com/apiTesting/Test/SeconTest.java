package com.apiTesting.Test;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiTesting.apiConfigs.ApiPath;
import com.apiTesting.apiConfigs.ApiVerification;
import com.apiTesting.apiConfigs.HttpMethods;
import com.apiTesting.base.Base;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SeconTest extends Base {
	String baseURL=readFromPropertyFile("baseUrl");
	
	@Test()
	public void getAllFinancialYear() {
		test.log(Status.INFO, "getAllFinancialYear test is starting......");
		
		String url=baseURL+ApiPath.Get_All_Financial_Year;
		
		Response re= HttpMethods.getWithAuthentication(access_Token, url);
		re.prettyPrint();
		
		ApiVerification.responseCodeValidation(re, 200);
		ApiVerification.responseKeyValidationFromResponseBody(re, "2021");
		test.log(Status.INFO, "getAllFinancialYear test is end......");
	}
	
	@Test
	public void createCustomizeInvoice() {
		test.log(Status.INFO, "createCustomizeInvoice test is starting......");
		String url=baseURL+ApiPath.Post_Create_CustomizeInvoice;
		
		Response re= HttpMethods.postWithParam("invoiceId", "2231765",access_Token, url);
		re.prettyPrint();
		ApiVerification.responseCodeValidation(re, 200);
		ApiVerification.responseKeyValidationFromJsonpath(re, "invoice.clientName","RICHEMONT");
		ApiVerification.responseKeyValidationFromResponseBody(re, "clientName");
		
		test.log(Status.INFO, "createCustomizeInvoice test is end......");
		
	}

}
