package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		GooglemapsStepDefinitions g=new GooglemapsStepDefinitions();
		if(g.place_id==null) {

		g.add_Place_payload_with("Sea_Residency", "French", "pondy");
		g.user_calls_with_POST_http_request("AddPlaceAPI", "POST");
		g.verify_place_id_created_maps_to_using("Sea_Residency", "getPlaceAPI");
		}
	}
}
