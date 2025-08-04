package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pojo.GetGoogleValues;
import com.pojo.GetLocationValues;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class GooglemapsStepDefinitions extends Utils{
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	@Given("Add Place payload")
	public void add_Place_payload() throws IOException {

	    req=given().spec(requestSpecification()).body(data.addplacePayload());
	}
	//addplacepayload stepdefinition with dynamic values

	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with (String name,String language,String address) throws IOException {

	    req=given().spec(requestSpecification()).body(data.addplacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_POST_http_request(String resource, String method) {
		//Write code here that turns the phrase above into concrete actions
		//Constructor will be called with value of resource which you pass
		APIResources resourceapi=APIResources.valueOf(resource);

		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")){
		response=req.when().post(resourceapi.getresource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			response=req.when().get(resourceapi.getresource());
		}
		else if(method.equalsIgnoreCase("delete")) {
			response=req.when().delete(resourceapi.getresource());
		}
	}

	@Then("user got success with status code {int}")
	public void user_got_success_with_status_code(Integer code) {
		assertEquals(response.getStatusCode(),200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body(String keyValue,String expectedValue) {

		assertEquals(getJsonPath(response,keyValue),expectedValue);

	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id=getJsonPath(response,"place_id");

       req=given().spec(requestSpecification()).queryParam("place_id", place_id);
       user_calls_with_POST_http_request(resource,"GET");
       String actualName=getJsonPath(response,"name");
       assertEquals(actualName,expectedName);

	}

	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
		req=given().spec(requestSpecification()).body(data.deleteplacePayload(place_id));
	}



}
