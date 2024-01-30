package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static JsonPath rawToJson(String response) {
		JsonPath js1=new JsonPath(response);
		return js1;
	}
	
	public static XmlPath rawToXML(Response r)
	{
	
		
		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
		
	}
	public static String getJiaraSessionKey() {
		//creating session
				RestAssured.baseURI="http://localhost:8080";
				String response=given().header("Content-Type", "application/json").body("{\"username\": \"savitas\", \"password\": \"Ilink@2023*\"}").
				when().post("/rest/auth/1/session").then().statusCode(200).extract().response().asString();
				JsonPath js=ReusableMethods.rawToJson(response);
				String sessionID=js.get("session.value");
				//System.out.println(sessionID);
				return sessionID;
				
	}
	

}
