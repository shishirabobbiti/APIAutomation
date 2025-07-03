package com.api;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.files.ReusableMethod;
import com.pojo.Api;
import com.pojo.GetValues;
import com.pojo.WebAutomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth {

	public static void main(String[] args) {
		String[] courses= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		//AuthenticationServer API
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	     String	response=given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).extract().response().asString();
	     JsonPath js=ReusableMethod.rawToJson(response);
	    String accessToken= js.getString("access_token");
	    System.out.println(accessToken);
		
		//getcourseDetails -Print response 
	   String response2= given()
	    .queryParam("access_token", accessToken)
	    .when().log().all().get("oauthapi/getCourseDetails")
	    .asString();
	    System.out.println(response2);
	    
	    //using Pojo ,deserialization,serialization
	    GetValues gv= given()
	    	    .queryParam("access_token", accessToken)
	    	    .when().log().all().get("oauthapi/getCourseDetails")
	    	    .as(GetValues.class);
	    
	   System.out.println(gv.getInstructor());
	   System.out.println(gv.getLinkedIn());
	  System.out.println (gv.getCourses().getApi().get(1).getCourseTitle());
	  
	  List<Api> apiCourses=gv.getCourses().getApi();
	   //get price of SoapUI Webservices testing
	  for(int i=0;i<apiCourses.size();i++) {
		String title= apiCourses.get(i).getCourseTitle();
		if(title.equalsIgnoreCase("SoapUI Webservices testing")) {
			System.out.println("SoapUI Webservices testing -price: "+apiCourses.get(i).getPrice());
		}
	}
	  //get course titles of webautomation
	  ArrayList<String> expectedList= new ArrayList<String>();
	  //Convert array into arrayList
	   List<String>actualList= Arrays.asList(courses);
	 
	 List<WebAutomation>  webAutomationcourses=gv.getCourses().getWebAutomation();
	    	  for(int i=0;i<webAutomationcourses.size();i++) {
	    		  String title=webAutomationcourses.get(i).getCourseTitle();
	    		  expectedList.add(title);
	    		  //System.out.println("WebAutomationTitle: "+title);    		  
	    	  }
	    	 Assert.assertEquals(actualList, expectedList); 
	    
	}

}
