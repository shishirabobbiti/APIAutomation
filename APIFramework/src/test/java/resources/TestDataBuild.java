package resources;

import java.util.ArrayList;
import java.util.List;

import com.pojo.GetGoogleValues;
import com.pojo.GetLocationValues;

public class TestDataBuild {

	public GetGoogleValues addplacePayload() {
		//we are setting values for Request Json using Java objects
		GetGoogleValues g=new GetGoogleValues();
		g.setAccuracy(50);
		g.setName("Frontline house");
		g.setPhone_number("(+91) 983 893 3937");
		g.setAddress("29, side layout, cohen 09");
		g.setWebsite("http://google.com");
		g.setLanguage("French-IN");
		GetLocationValues l=new GetLocationValues();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		List<String> t=new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		g.setTypes(t);
		return g;
	}

	public GetGoogleValues addplacePayload(String name, String language, String address) {
		//we are setting values for Request Json using Java objects
		GetGoogleValues g=new GetGoogleValues();
		g.setAccuracy(50);
		g.setName(name);
		g.setPhone_number("(+91) 983 893 3937");
		g.setAddress(address);
		g.setWebsite("http://google.com");
		g.setLanguage(language);
		GetLocationValues l=new GetLocationValues();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		List<String> t=new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		g.setTypes(t);
		return g;
	}
	public String  deleteplacePayload(String place_Id) {

		return "{\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}";

	}



}
