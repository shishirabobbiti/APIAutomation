package com.api;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReusableMethod;

import static io.restassured.RestAssured.*;

public class PostRequest {

	public static void main(String[] args) {
		
		//validate if add place API is working  as expected
		//given -all input details
		//when -submit the API
		//Then -Validate the response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		//update addrees using put
		String newAddress="87 Avneue colony";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get
		String getResponse=given().log().all().queryParam("place_id", placeId).queryParam("key", "qaclick123").when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath jsGet=ReusableMethod.rawToJson(getResponse);
	  String actualAddress=	jsGet.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(newAddress, actualAddress);
	}

}
