package com.apiTesting.apiConfig;

public interface ApiPath {
	//api's end point
	
	public String Get_List_Of_User="/api/users?page=2";
	public String Get_Single_user="/api/users/2";
	public String Post_Create_User="/api/users";
	public String Post_Register_User="/api/register";
	
	//expresso api's end point
	public String Post_Generate_Token="/oauth/token";
	public String Get_All_Financial_Year="/api/invoice/getAllFinancialYear";
	public String Post_Create_CustomizeInvoice="/api/invoice/createCustomizeInvoice";
	
	

}
