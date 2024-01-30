package TestFramework;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;


public class AddPaceMaps {
	
@Test
public void postData() {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
			"  \"location\": {\r\n" + 
			"    \"lat\": -38.383494,\r\n" + 
			"    \"lng\": 33.427362\r\n" + 
			"  },\r\n" + 
			"  \"accuracy\": 50,\r\n" + 
			"  \"name\" :\"Rahul shetty academy\",\r\n" + 
			"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
			"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
			"  \"types\": [\r\n" + 
			"    \"shoe park\",\r\n" + 
			"    \"shop\"\r\n" + 
			"  ],\r\n" + 
			"  \"website\": \"http://Rahulshettyacademy.com\",\r\n" + 
			"  \"language\": \"French-IN\"\r\n" + 
			"}").when().post("maps/api/place/add/json").then().assertThat().log().all().statusCode(200);
	
	//Create place=(response(place id)) 
	//and delete place=(Request-place id)
	
}



}
