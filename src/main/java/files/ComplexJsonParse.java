package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String args[]) {
		JsonPath js=new JsonPath(Payload.coursePrice());
		//No.of courses returned by aPI
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        //print title of the first course
        
       String titleFirstCourse= js.get("courses[0].title");
       System.out.println(titleFirstCourse);
       
       //print all course titles and their prices
       for(int i=0;i<count;i++) {
    	  String coursetitle=js.get("courses["+i+"].title");
    	  System.out.println(js.get("courses["+i+"].price").toString());
    	  
    	  System.out.println(coursetitle);
       }
       
       System.out.println("print number of copies sold by RPA course");
       for(int i=0;i<count;i++) {
    	   String courseTitles=js.get("courses["+i+"].title");
    	   if(courseTitles.equalsIgnoreCase("RPA")) {
    		   //copies sold
    		   int copies=js.get("courses["+i+"].copies");
    		   System.out.println(copies);
    		   break;
    	   }
       }
	}

		

}
