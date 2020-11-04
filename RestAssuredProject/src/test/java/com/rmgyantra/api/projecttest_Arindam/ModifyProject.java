package com.rmgyantra.api.projecttest_Arindam;

import org.testng.annotations.Test;

import com.rmgyantra.api.genericUtils.BaseAPIClass;
import com.rmgyantra.api.pojolib.IEndPoints;
import com.rmgyantra.api.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
/**
 * 
 * @author Roy Arindam
 *
 */
public class ModifyProject extends BaseAPIClass {
 
	@Test
    public void modifyTest() throws Throwable {
		 /*create JSONObject using POJO class*/
		Project pro = new Project("Arindam","Genesis","On-going", 20);
		
		/*Create a Project*/
		Response resp = given()
						.contentType(ContentType.JSON)
						.body(pro, ObjectMapperType.JACKSON_1)
					.when()
						.post(IEndPoints.addProj);
		resp.then()
			.log().all()
			.assertThat().statusCode(201);
		
		String actProjectID = resp.jsonPath().get("projectId");
		
		/*Modify the status to 'Completed'*/
		pro.setStatus("Completed");
		 given()
		  .contentType(ContentType.JSON)
		  .body(pro, ObjectMapperType.JACKSON_1)
		 .when()
		   .put("/projects/"+actProjectID)
		 .then()
		 	.log().all();
		 
		 dblib.executeQueryVerifyAndGetData("select * from project", 5, "Completed");
						
	}
}
