package com.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import com.files.ReusableMethod;

public class JiraBugTest {

	public static void main(String[] args) {
			
		RestAssured.baseURI="https://shishira.atlassian.net/";
		String response=given().header("Content-Type","application/json").header("Authorization","Basic c2hpc2hpcmEzNkBnbWFpbC5jb206QVRBVFQzeEZmR0YweWJHTkRqa3BvU0U0aE9WRTVGTWVOUVY0V2dhMnBEYnZ2UU9hYTRkWUtGRWJ0ODVPSzdfTmx4dzkxNk5nQ3Y4QjlqY2ZTUGNUNVBlRzJESUQyN3Q3U2pKVUNPYjdVWUFOdUpMRU5pWHJ1MjU5cS1yc1RJNFpkR2ZicGFFNHh6YzE0eDVwQUo3Y3YtakdOSVdCczNlNFgzTXg2a0VUYXdqM3JWMDZUUFJYNGZNPTg5NjFENzQ1")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"login button is not working\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("rest/api/2/issue")
		.then().assertThat().statusCode(201).extract().response().asString();
		JsonPath js=ReusableMethod.rawToJson(response);
		String id=js.get("id");
		System.out.println("id : "+id);
		
		given()
		.pathParam("key", id)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic c2hpc2hpcmEzNkBnbWFpbC5jb206QVRBVFQzeEZmR0YweWJHTkRqa3BvU0U0aE9WRTVGTWVOUVY0V2dhMnBEYnZ2UU9hYTRkWUtGRWJ0ODVPSzdfTmx4dzkxNk5nQ3Y4QjlqY2ZTUGNUNVBlRzJESUQyN3Q3U2pKVUNPYjdVWUFOdUpMRU5pWHJ1MjU5cS1yc1RJNFpkR2ZicGFFNHh6YzE0eDVwQUo3Y3YtakdOSVdCczNlNFgzTXg2a0VUYXdqM3JWMDZUUFJYNGZNPTg5NjFENzQ1")
		.multiPart("file",new File("C:\\Users\\Shishira Reddy\\Downloads\\April.pdf"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().assertThat().statusCode(200);
	}

}
