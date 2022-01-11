import org.testng.Assert;
import org.testng.annotations.Test;

import files.payloads;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses()
	{
		JsonPath js=new JsonPath(payloads.CoursePrice());
		
		int courseCount=js.getInt("courses.size()");
		
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		
		int sumOfCourseAmount=0;
		for(int i=0;i<courseCount;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			
			int sumPrice=price*copies;
			sumOfCourseAmount+=sumPrice;
		}
		
		Assert.assertEquals(sumOfCourseAmount, totalAmount);
		/*if(totalAmount==sumOfCourseAmount)
		{
			System.out.println("Price are same");
		}
		else
		{
			System.out.println("Price are not same");
		}*/
	}
}
