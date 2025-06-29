package com.api;

import org.testng.annotations.Test;

import com.files.Payload;
import com.files.ReusableMethod;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").body(Payload.addBook("bcd","8525") )
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReusableMethod.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		
	}

}
