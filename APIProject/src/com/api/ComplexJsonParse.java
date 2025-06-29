package com.api;

import org.testng.Assert;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
			
		JsonPath js=new JsonPath(Payload.coursePrice());
		//1. Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		//2.Print Purchase Amount
	    int  purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		//3.Print Title of the first course
		String coursefirsttitle=js.getString("courses[0].title");
		System.out.println(coursefirsttitle);
		//4.Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String courseTitle=js.getString("courses["+i+"].title");
			int price=js.get("courses["+i+"].price");
			System.out.println(courseTitle + ":"+ price);
				}
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String courseTitle=js.getString("courses["+i+"].title");
			if(courseTitle.contentEquals("RPA")) {
				int copiesRPA=js.getInt("courses["+i+"].copies");
				System.out.println(copiesRPA);
				break;
			}
		}

		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int actualprice=0;
		for(int i=0;i<count;i++) {
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int copiesprice=price*copies;
			//System.out.println(copiesprice);
			actualprice=actualprice+copiesprice;
			
		}
		System.out.println(actualprice);
		Assert.assertEquals(purchaseAmount,actualprice );

	}

}
