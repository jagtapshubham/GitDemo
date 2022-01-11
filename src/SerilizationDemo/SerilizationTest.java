package SerilizationDemo;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerilizationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
	
		AddPlace ap=new AddPlace();
		
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		ap.setTypes(myList);
		
		Location lc=new Location();
		lc.setLat(-38.383494);
		lc.setLng(-38.383494);
		ap.setLocation(lc);
		
		
		String responseString = given().queryParam("key", "qaclick123")
				.body(ap)
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();
	
		System.out.println(responseString);
	}

}
