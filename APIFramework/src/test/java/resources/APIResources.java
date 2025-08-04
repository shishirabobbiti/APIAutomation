package resources;

public enum APIResources {
//Eum is special class in java which has collection of constants and methods

	AddPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");

	private String resource;

	APIResources(String resource){
		this.resource=resource;
	}


	public String getresource() {
		return resource;
	}

}
