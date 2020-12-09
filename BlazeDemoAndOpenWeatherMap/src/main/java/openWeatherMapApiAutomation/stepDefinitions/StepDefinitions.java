package openWeatherMapApiAutomation.stepDefinitions;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import openWeatherMapApiAutomation.resources.ApiResources;
import openWeatherMapApiAutomation.resources.CommonActions;

public class StepDefinitions extends CommonActions {
	public static RequestSpecification requestSpec;
	public static Response response;

	@Given("As user I load the {string} api with city name as {string}")
	public void loadApiWithCity(String api, String city) throws IOException {
		requestSpec = given().spec(requestSpecification()).queryParam("q", city);
	}

	@Given("As user I load the {string} api'")
	public void loadApiWithDefaultAPIKey(String api) throws IOException {
		requestSpec = given().spec(requestSpecification());
	}

	@Given("As user I load the {string} api with api key as {string} and with city name as {string}")
	public void loadApiWithGivenApiKey(String api, String apiKey, String city) throws IOException {
		requestSpec = given().spec(requestSpecification(apiKey)).queryParam("q", city);
	}

	@Given("As user I load the {string} api without api key and with city name as {string}")
	public void loadApiWithoutApiKeyAndWithCityName(String api, String city) throws IOException {
		requestSpec = given().spec(requestSpecification(false)).queryParam("q", city);
	}

	@When("I call {string} api with {string} Http request")
	public void callApiWithHttpRequest(String api, String method) {
		ApiResources resource = ApiResources.valueOf(api);
		if (method.equalsIgnoreCase("Get"))
			response = requestSpec.when().get(resource.getResource());
		else
			Assert.fail("Method not allowed or not scripted in automation.");
	}

	@Then("I validate {string} api response with status code {int} and city name as {string}")
	public void validateApiResponseStatusCodeAndCityName(String api, int expectedStatusCode, String expectedCiyName) {
		String actualCityName = getJsonPath(response, "name");

		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
		Assert.assertEquals(expectedCiyName, actualCityName);
	}

	@Then("I validate {string} api response with status code {int} and message as {string}")
	public void validateApiResponseWithStatusCodeAndMessage(String api, int expectedStatusCode,	String expectedMessage) {
		String actualMessage = getJsonPath(response, "message");

		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Then("I validate {string} api response with status code {int}")
	public void validateApiResponseWithStatusCode(String api, int expectedStatusCode) {
		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
	}

	@Then("I validate {string} api response headers")
	public void validateApiResponseHeaders(String api, DataTable responseHeaders) {
		String header, expectedHeaderValue, actualHeaderValue;
		List<Map<String, String>> responseHeadersTable = responseHeaders.asMaps();
		List<String> fields = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		for (Map<String, String> v : responseHeadersTable) {
			for (Object field : v.keySet()) {
				fields.add(String.valueOf(field));
			}
			for (Object value : v.values()) {
				values.add(String.valueOf(value));
			}
		}

		for (int columns = 0; columns < fields.size(); columns++) {
			header = fields.get(columns);
			expectedHeaderValue = values.get(columns);
			actualHeaderValue = response.getHeader(header);

			switch (header) {
			case "Server":
				Assert.assertEquals(expectedHeaderValue, actualHeaderValue);
				break;
			case "Content-Type":
				Assert.assertTrue(actualHeaderValue.contains(expectedHeaderValue));
				break;
			default:
				Assert.fail("No Such Header or Header not handled in automation code.");
			}
		}
	}

	@Then("I validate {string} api response contains {string} collection details")
	public void validateApiResponseDetails(String api, String responseCollection) {
		String actualResponse;

		switch (responseCollection) {
		case "coord":
			actualResponse = getJsonPath(response, "coord.lon");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "coord.lat");
			Assert.assertTrue(actualResponse != null);
			break;
		case "weather":
			actualResponse = getJsonPath(response, "weather.id");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "weather.main");
			Assert.assertTrue(actualResponse != null);
			break;
		case "main":
			actualResponse = getJsonPath(response, "main.temp");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "main.feels_like");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "main.temp_min");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "main.temp_max");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "main.pressure");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "main.humidity");
			Assert.assertTrue(actualResponse != null);
			break;

		case "wind":
			actualResponse = getJsonPath(response, "wind.speed");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "wind.deg");
			Assert.assertTrue(actualResponse != null);
			break;
		case "clouds":
			actualResponse = getJsonPath(response, "clouds.all");
			Assert.assertTrue(actualResponse != null);
			break;
		case "sys":
			actualResponse = getJsonPath(response, "sys.country");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "sys.sunrise");
			Assert.assertTrue(actualResponse != null);
			actualResponse = getJsonPath(response, "sys.sunset");
			Assert.assertTrue(actualResponse != null);
			break;
		case "base":
		case "visibility":
		case "dt":
		case "timezone":
		case "id":
		case "name":
		case "cod":
			actualResponse = getJsonPath(response, responseCollection);
			Assert.assertTrue(actualResponse != null);
			break;
		default:
			Assert.fail("No such response details found.");

		}
	}
}
