package com.rmgyantra.api.projecttest;

import org.testng.annotations.Test;

import com.rmgyantra.api.genericUtils.BaseAPIClass;
import com.rmgyantra.api.pojolib.IEndPoints;
import com.rmgyantra.api.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ModifyProject extends BaseAPIClass{
	
	@Test
	public void modifyTest() throws Throwable {
		/* create Project */
		Project poj = new Project("deepak", "Orange-2", "On Going", 10);
		
		Response resp = given()
						   .contentType(ContentType.JSON)
						   .body(poj, ObjectMapperType.JACKSON_1)
						.when()
						   .post(IEndPoints.addProj);
						resp.then()
						       .log().all()
							   .assertThat().statusCode(201);
							
		  String actProjectID =  resp.jsonPath().get("projectId");
		
		/* modify Project name */
		       poj.setProjectName("apple");
				  given()
				    .contentType(ContentType.JSON)
				    .body(poj, ObjectMapperType.JACKSON_1)
				  .when()
				    .put("/projects/"+actProjectID)
				  .then()
				    .log().all();
				
				  dblib.executeQueryVerifyAndGetData("select * from project", 4, "apple");
				
		
	}

}
