package com.serialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;


public class SerializationTest {

	public static void main(String[] args) {
		//we are setting values for Request Json using Java objects
		GetGoogleValues g=new GetGoogleValues();
		g.setAccuracy(50);
		g.setName("Frontline house");
		g.setPhone_number("(+91) 983 893 3937");
		g.setAddress("29, side layout, cohen 09");
		GetLocationValues l=new GetLocationValues();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		List<String> t=new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		g.setTypes(t);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123").body(g).log().all()
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		
	}

}
;