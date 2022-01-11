import files.payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js= new JsonPath(payloads.CoursePrice());

		// Print number of courses
		int count= js.getInt("courses.size()");
		System.out.println(count);
		
		// Print total purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print First Title of courses name
		String titleFirstCourse=js.getString("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//Print course title with their respective prices 
		for(int i=0;i<count;i++)
		 {
			String courseTitle=js.getString("courses["+i+"].title"); 
			int coursePrice=js.getInt("courses["+i+"].price");
			System.out.print("title:"+courseTitle+"\t price:"+coursePrice);
			System.out.println();
		 }
		
		//Print no of copies sold by RPA Course

		for(int i=0;i<count;i++)
		{
			String str=js.get("courses["+i+"].title");
			if(str.equalsIgnoreCase("RPA"))
			{
				int soldCopysOfRPA=js.get("courses["+i+"].copies");
				System.out.println("Sold copies of RPA course is "+soldCopysOfRPA);
				break;		
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int sumOfCoursesAmount=0;
		for(int i=0;i<count;i++)
		{
			int individualSum=js.getInt("courses["+i+"].price");
			int soldCopies=js.getInt("courses["+i+"].copies");
			int localSum=individualSum*soldCopies;
			sumOfCoursesAmount+=localSum;
		}
		if(sumOfCoursesAmount==totalAmount)
		{
			System.out.println("Prices are same");
		}
		else
		{
			System.out.println("Prices are not same");
		}
	
	    }
		
	}

