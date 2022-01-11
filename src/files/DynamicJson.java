package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/* Handling Multiple Data Sets */
public class DynamicJson {

	/*
	@Test
	public void Addbook()			// Add  book to library
	{
		RestAssured.baseURI="http://216.10.245.166";
	
		// Passing Parameterized content to .body() 
		 String bookResponse= given().header("Content-Type","application/json")
		.body(payloads.AddBook("hell","2019"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=ReUsableMethods.rawToJson(bookResponse);
		
		String id=js.getString("ID");
		System.out.println("book id = "+id);
		
		
		// DeleteBook 
		Response deleteResponse= given().header("Content-Type","application/json")
				.body("{\r\n"
						+ " \r\n"
						+ "\"ID\" : \"hell2019\"\r\n"
						+ " \r\n"
						+ "} ")
				.when().delete("Library/DeleteBook.php");//.then().log().all().assertThat().statusCode(200);
		System.out.println("Delete Response Status code = "+deleteResponse.getStatusCode());
		
	}
	*/
	
	// Add Book
/*	@Test(dataProvider="BookData")
	public void Addbook(String isbn,String aisle)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String bookResponse= given().header("Content-Type","application/json")
				.body(payloads.AddBook(isbn,aisle))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		
		JsonPath js=ReUsableMethods.rawToJson(bookResponse);
		
		String id=js.getString("ID");
		System.out.println("book id = "+id);
		
	}
	@DataProvider(name="BookData")
	public Object[][] getData()
	{
		return new Object[][] {{"cca","1972"},{"cppb","1980"},{"jja","1985"}};
		
	}
	*/
	
	// Delete Book
	@Test(dataProvider="BookId")
	public void Deletebook(String id)
	{
		RestAssured.baseURI="http://216.10.245.166";
		// Delete Data
		
				 given().header("Content-Type","application/json")
				.body(payloads.DeleteBook(id))
				.when().delete("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200);
				
				
	}
	@DataProvider(name="BookId")
	public Object[] getData()
	{
		return new Object[] {"cca1972","cppb1980","jja1985"};
	}
	
}
