package openWeatherMapApiAutomation.resources;

public enum ApiResources {

	weather("/data/2.5/weather");

	private String resource = "";

	ApiResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
