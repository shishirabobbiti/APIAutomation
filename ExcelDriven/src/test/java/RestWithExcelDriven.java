import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RestWithExcelDriven {

	public static void main(String[] args) throws IOException {
		ArrayList data=DataDriven.getData("AddBook","RestAssured");
	
		HashMap<String,Object> jsonAsMap=new HashMap<String,Object>();
		jsonAsMap.put("name", data.get(1));
		jsonAsMap.put("isbn", data.get(2));
		jsonAsMap.put("aisle", data.get(3));
		jsonAsMap.put("author", data.get(4));
		
//		HashMap<String,Object> jsonAsMap2=new HashMap<String,Object>();
//		jsonAsMap2.put("lat", "3345672");
//		jsonAsMap2.put("lan", "-32456789");
//		jsonAsMap.put("location", jsonAsMap2);//it is nested Json 
		
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String response=given().headers("Content-Type","application/json")
	.body(jsonAsMap)
	.when().post("/Library/Addbook.php")
	.then().extract().response().asString();
	JsonPath js=new JsonPath(response);	
	String id=js.get("ID");
	System.out.println(id);
	
	//Get
	
	//given().headers("Content-Type","application/json").body(null)
	
	}

}
