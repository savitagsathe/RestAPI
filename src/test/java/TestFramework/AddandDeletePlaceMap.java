package TestFramework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.resource;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddandDeletePlaceMap {
	//Create place=(response(place id)) 
			//and delete place=(Request-place id)
	
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\DemoProject\\src\\main\\java\\files\\env.properties");
		prop.load(fis);
		//prop.get("HOST");
		
	}
	
	
	@Test
	public void postData() {
		/*String b="{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\" :\"Rahul shetty academy test test1223\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://Rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";
				hardcoded removed from here and read it from Payload.java*/
		
		
		//Task1-Response
		RestAssured.baseURI= prop.getProperty("HOST");
		
		String res=given().log().all().queryParam("key", prop.getProperty("KEY")).header("Content-Type","application/json").body(Payload.addPlace()).when().post(resource.placePostData()).then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		//Task2-Grab the place Id from the Response
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		//Task3- place this place Id in the delete request
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"\"place_id\":\""+placeid+"\"}").when().post("maps/api/place/delete/json")
		.then().assertThat().log().all().statusCode(200);
		
		
		
	}


}
