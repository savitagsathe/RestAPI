package files;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Basic8_updatecomment {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\DemoProject\\src\\main\\java\\files\\env.properties");
		prop.load(fis);
		
	}
	
	@Test
	public void jiraAPIUPdateComment() {
		//Creating issue
		RestAssured.baseURI="http://localhost:8080";
		String Response=given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+ReusableMethods.getJiaraSessionKey()).pathParams("commentid","10103").
		body("{\r\n" + 
				"  \"body\":  \"Updating comment second from automation code(10201).\",\r\n" + 
				"    \r\n" + 
				"       \"visibility\": {\r\n" + 
				"    \"type\": \"role\",\r\n" + 
				"    \"value\": \"Administrators\"\r\n" + 
				"  }    \r\n" + 
				"       \r\n" + 
				"}").when().put("/rest/api/2/issue/10201/comment/{commentid}").then().statusCode(200).extract().response().asString();
		
	}
	

}
