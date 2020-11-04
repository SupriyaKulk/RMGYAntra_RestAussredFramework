package com.rmgyantra.api.genericUtils;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;

public class BaseAPIClass {
	
	public DataBaseUtilities dblib = new DataBaseUtilities();
	
	@BeforeSuite
	public void configBeforeSuite() {
		
		  //connect to DataBase
		dblib.connectToDB();
		  //BaseURI & PORT NUM
		baseURI = "http://localhost:8084";
		
	}
	
	
	@AfterSuite
	public void configAfterSuite() throws SQLException {
		 //close database connection
		dblib.closeDb();
	}

}
