package TestFramework;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class BasicsTest {
//Validate if Add Place API is working as expected or not
	//Add place->update place with new address->get place to validate if new address is present in response
	
	//given-All input details
	//when-submit the API
	//then-validate the response
	//content fo the file to String->content of the file can convert into Byte->Byte data to String
	
	public static void main(String args[]) throws IOException {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body(new String(Files.readAllBytes
	(Paths.get("C:\\Users\\Admin\\Downloads\\Library+API.postman_collection.json")))
	).when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	JsonPath js=new JsonPath(response);//for parsing json
	String placeId=js.getString("place_id");
	
	System.out.println(placeId);
	
	//update
	String newAddress="summer walk,africa";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
			"\"place_id\":\""+placeId+"\",\r\n" + 
			"\"address\":\""+newAddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	//Get place
	String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
	when().get("maps/api/place/get/json").
	then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);//for parsing json
	String actualAddress=js1.getString("address");
	
	System.out.println(actualAddress);
	
	Assert.assertEquals(actualAddress, newAddress);
	}
	
	
}
