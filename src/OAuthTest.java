import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import files.ReUsableMethods;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	
	 // In 2020 Google has Stopped login from automation for security
	 
	 
		// Invoke Chrome browser below 2 lines
		System.setProperty("webdriver.chrome.driver", "D:\\Rest API Testing (Automation) from Scratch-Rest Assured Java\\Section 10\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();		// initialize the browser 
		String pass="originalpassword";
		// used to hit website rahulshetty by using google credentials 
		//driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyfjdss&flowName=GeneralOAuthFlow");
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("jagtapshubham191@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url=driver.getCurrentUrl();	// gets the current url into "url(variable)"
		
		// Above code uses selenium
		
		//String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWgU9Hs7GR1j263GO3MSg2EX4ncQmS0uCVQvSfRwfevyxmeDc7hHlYFbSlHthNz6Vg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partialcode=url.split("code=")[1];	//splits the url into two parts till "code="
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
                .when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		JsonPath js=new JsonPath(accessTokenResponse);			// pass the above Json file to JsonPath class
		String accessToken = js.getString("access_token");		//js object can be used to query specific parts of the Response Json
		
		
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)	//restAssured treats response as Json
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		//System.out.println(response);
	}

}
