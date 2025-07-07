package com.ecom;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.files.ReusableMethod;

public class EcommerceTest {

	public static void main(String[] args) {
		//Login 
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		GetUserLoginDetails ud=new GetUserLoginDetails();
		ud.setUserEmail("shishira@gmail.com");
		ud.setUserPassword("Shishira@123");
		LoginResponseValues loginres=given().spec(req).body(ud)
		.when().post("api/ecom/auth/login")
		.then().spec(res).extract().response().as(LoginResponseValues.class);
		String token=loginres.getToken();
		String userId=loginres.getUserId();
		System.out.println(token);
		System.out.println(userId);
		
		//Create Product
		RequestSpecification createprodreq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").
				addHeader("Authorization",token).build();
	String createRes=	given().spec(createprodreq).param("productName", "RedDress").param("productAddedBy",userId).param("productCategory", "fashion")
		.param("productSubCategory", "Kurti").param("productPrice", "1000").param("productDescription", "BIBA Fashion").param("productFor", "women")
		.multiPart("productImage",new File("C:\\Users\\Shishira Reddy\\Downloads\\RedDress.png"))
		.when().post("api/ecom/product/add-product")
		.then().assertThat().statusCode(201).extract().response().asString();
		 // JsonPath js=new JsonPath(createRes);
	//Resuable method
			JsonPath js=ReusableMethod.rawToJson(createRes);
		String productId=js.get("productId");
				System.out.println(productId);
				
	//Createorder
				RequestSpecification createOrderReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").
						addHeader("Authorization",token).setContentType(ContentType.JSON).build();
//				OrdersDetails od=new OrdersDetails();
//				od.setCountry("India");
//				od.setProductOrderedId(productId);
//				List<OrdersDetails>orderDetailsList=new ArrayList<OrdersDetails>();
//				orderDetailsList.add(od);
//				OrderRequestValues orders=new OrderRequestValues();
//				orders.setOrder(orderDetailsList);
				String response=given().spec(createOrderReq).body("{\r\n"
						+ "    \"orders\": [\r\n"
						+ "        {\r\n"
						+ "            \"country\": \"India\",\r\n"
						+ "            \"productOrderedId\": \""+productId+"\"\r\n"
						+ "        }\r\n"
						+ "    ]\r\n"
						+ "}")
				.when().log().all().post("api/ecom/order/create-order")
				.then().log().all().extract().response().asString();
				System.out.println(response);
				
		//DeleteOrder
				RequestSpecification deletereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
						.addHeader("Authorization",token).build();
				String deleteresponse=given().spec(deletereq).pathParam("productId", productId)
				.when().delete("api/ecom/product/delete-product/{productId}")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				JsonPath delejs=new JsonPath(deleteresponse);
				String message=delejs.get("message");
				Assert.assertEquals(message, "Product Deleted Successfully");
				
		
				
		
	}

}
