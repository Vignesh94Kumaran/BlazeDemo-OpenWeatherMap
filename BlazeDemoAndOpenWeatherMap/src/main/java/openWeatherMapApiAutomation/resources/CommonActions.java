package openWeatherMapApiAutomation.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonActions {

	public static String getGlobalValue(String key) throws IOException {
		Properties property = new Properties();
		FileInputStream input = new FileInputStream(
				"./src/main/java/openWeatherMapApiAutomation/resources/global.properties");
		property.load(input);
		return property.getProperty(key);
	}

	public RequestSpecification requestSpecification() throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri"))
				.addQueryParam("appid", "15e6e04de370a3c49736b37cda29732a").setContentType(ContentType.JSON).build();
		return request;
	}

	public RequestSpecification requestSpecification(boolean apiKey) throws IOException {
		RequestSpecification request;
		if (apiKey)
			request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri"))
					.addQueryParam("appid", "15e6e04de370a3c49736b37cda29732a").setContentType(ContentType.JSON)
					.build();
		else
			request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).setContentType(ContentType.JSON)
					.build();
		return request;
	}

	public RequestSpecification requestSpecification(String apiKey) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri"))
				.addQueryParam("appid", apiKey).setContentType(ContentType.JSON).build();
		return request;
	}

	public String getJsonPath(Response response, String key) {
		String receivedResponse = response.asString();
		System.out.println(receivedResponse);
		JsonPath js = new JsonPath(receivedResponse);
		return js.get(key).toString();
	}
}
