package com.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.Payload;
import com.files.ReusableMethod;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson2 {

	//AddBook
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisle) )
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReusableMethod.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		
	}
	//DeleteBook
	@Test
	public void deleteBook() throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		given().header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Shishira Reddy\\API-workspace\\APIProject\\src\\com\\files\\deleteBook.json"))))
		.when().delete("Library/DeleteBook.php")
		.then().assertThat().statusCode(200);
				
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//array-colllection of elements
		//multidimensional array-colllection of arrays
		Object[][] data=new Object[][] {{"bcd","6262"},{"bcd","6161"},{"bcd","6363"}};
		return data;
	}
}
