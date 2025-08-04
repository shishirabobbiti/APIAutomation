package com.graphQL;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String queryresponse=given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query{\\n  character(characterId: 801)\\n  {\\n    name\\n  }\\n  \\n  characters(filters: {name :\\\"Rahul\\\"})\\n  {\\n   info  {\\n    count\\n  }\\n    result{\\n      name\\n      id\\n    }\\n  }\\n  episodes(filters:{episode:\\\"hulu\\\"})\\n  {\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n  location(locationId :22477)\\n  {\\n    name\\n    id\\n  }\\n}\",\"variables\":null}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		JsonPath js=new JsonPath(queryresponse);
		String name=js.get("data.character.name");
		System.out.println(name);

	
	//Mutation
	String mutationresponse=given().log().all().header("Content-Type","application/json")
			.body("{\"query\":\"mutation{\\n  createLocation(location :{name:\\\"Hyd\\\",type:\\\"CentralZone\\\",dimension:\\\"2345\\\"})\\n  {\\n    id\\n  }\\n  createEpisode(episode :{name:\\\"Bigboss\\\",air_date:\\\"11-07-2025\\\",episode:\\\"9\\\"})\\n  {\\n  id\\n  }\\n  deleteLocations(locationIds:[22472,22473])\\n  {\\n  locationsDeleted  \\n  }\\n  \\n}\",\"variables\":null}")
			.when().post("https://rahulshettyacademy.com/gq/graphql")
			.then().extract().response().asString();
			JsonPath js1=new JsonPath(mutationresponse);
			int id=js1.get("data.createLocation.id");
			System.out.println(id);
	}
	

}
