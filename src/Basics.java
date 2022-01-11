import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReUsableMethods;
import files.payloads;
public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Validate  if AddPlace is API is working as expected
		
		//AddPlace-> Update place with new address -> GetPlace to validate is new address is presence in response 
		
		//given - all inputs details
		//when - Submit the API  - resource, HTTP methods
		//then - validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		/*1)AddPlace() method is in Another class is written to avoid complexity of this code in files package
		 * 2) .extract().response().asString() converts response to string ,so that we can store it in String variable*/
		
		/* Passing data to .body() through functions
		 * 
		 * 
		 String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payloads.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		*/	
		
		 /* Passing data to .body() through .json file */
		 
		  	String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		  			.body(new String(Files.readAllBytes(Paths.get("D:\\\\Rest API Testing (Automation) from Scratch-Rest Assured Java\\\\Section 7\\\\staticJsonAddBook.json"))) )
		  			.when().post("maps/api/place/add/json")
		  			.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		  			.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		  
		 
		JsonPath js=new JsonPath(response);		/* (for parsing json file)JsonPath class accept parameter as string */
	
		String placeId=js.getString("place_id");
		
		System.out.println("Place ID="+placeId);
		
		// Update Place
		
		//String newAddress = "70 Summer walk, USA";
		String newAddress = "summer 20 new york, USA";
				
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		// Get place
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		// Used to avoid repeated java code
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		
		String actualAddress = js1.getString("address");
		
		System.out.println("actual address="+actualAddress);
		
		
	}

}
