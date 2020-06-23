package com.RestAssuredTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTest 
    
{
	@Test(priority=1,description="Get the List of user")
	public void GetListUser()
	{
		RestAssured.baseURI="https://reqres.in";

		String Resp=given().
				when().
				get("https://reqres.in/api/users?page=2").
				then().
				assertThat().statusCode(200).and().
				body("page",equalTo(2)).and().
				body("data[0].first_name",equalTo("Michael")).and().
				body("data[1].last_name",equalTo("Ferguson")).and().
				extract().
				response().asString();

		System.out.println("Response is\t"+Resp);
	}
	
	@Test(priority=2,description="Delete the List of user")
	public void DeleteUserRequest()
	{
		RestAssured.baseURI="https://reqres.in";
		String Resp=given().
				when().
				delete("/api/users/2").
				then().assertThat(). 
				statusCode(204).and().
				header("server", "cloudflare").and(). 
				extract().
				response().asString();

		System.out.println("Response is\t"+Resp);
	}
	
	@Test(priority=2,description="Creating the List of user")
	public void RegisterUserPOSTAPI()
	{

		String bo=" '   {  '  + \r\n" + 
				" '       \"name\": \"shakir\",  '  + \r\n" + 
				" '       \"job\": \"qa\"  '  + \r\n" + 
				" '  }  ' ; ";
		//{"name": "shakir","job" "QA"}

		RestAssured.baseURI="https://reqres.in";

		String Resp=given().
				body("bo").
				when().
				post("/api/users").
				then().assertThat(). 
				statusCode(201).and().
				contentType(ContentType.JSON).and().
				extract().
				response().asString();

		System.out.println("Response is\t"+Resp);
	}
	 
	@Test(priority=3,description="Update the List of user")
	public void UpdateUserPutRequest()
	{

		String PutBody="{\r\n" + 
				"    \"name\": \"mayank\",\r\n" + 
				"    \"job\": \"qa\"\r\n" + 
				"}";
		RestAssured.baseURI="https://reqres.in";

				String Resp=given().
				header("Content-Type","application/json; charset=utf-8").
				body("PutBody").
				when().
				put("/api/users").
				then().assertThat(). 
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("name[]",equalTo("mayank")).
				extract().
				response().asString();

		System.out.println("Response is\t"+Resp);
	}
}
