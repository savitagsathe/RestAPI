package TestFramework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Comnet_jira_basic7 {

	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\DemoProject\\src\\main\\java\\files\\env.properties");
		prop.load(fis);
		
	}
	
	@Test
	public void jiraAPI() {
		//Creating issue
		RestAssured.baseURI="http://localhost:8080";
		String Response=given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+ReusableMethods.getJiaraSessionKey()).
		body("{\r\n" + 
				"  \"body\":  \"Executing comment form automation code.\",\r\n" + 
				"    \r\n" + 
				"       \"visibility\": {\r\n" + 
				"    \"type\": \"role\",\r\n" + 
				"    \"value\": \"Administrators\"\r\n" + 
				"  }    \r\n" + 
				"       \r\n" + 
				"}").when().post("/rest/api/2/issue/10201/comment").then().statusCode(201).extract().response().asString();
		JsonPath js=new JsonPath(Response);
		String id=js.get("id");
		System.out.println(id);
	}
		
	}
	
	

