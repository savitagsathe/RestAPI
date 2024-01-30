package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class resource {
	
	public static String placePostData() {
		String res="/maps/api/place/add/json";
		return res;
	}

	
	
}
