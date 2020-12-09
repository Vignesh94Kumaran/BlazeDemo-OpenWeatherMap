package blazeDemoUIAutomation.stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;
import blazeDemoUIAutomation.pages.ConfirmationPage;
import blazeDemoUIAutomation.pages.IndexPage;
import blazeDemoUIAutomation.pages.PurchasePage;
import blazeDemoUIAutomation.pages.ReservePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends CommonSeleniumActions {

	@Given("As Customer I navigate to {string} application")
	public void navigateToApplicationInBrowser(String applicationName) {
		navigateToApplication("https://blazedemo.com");
	}

	@When("I click on {string} tab in {string} page")
	public void i_click_on_tab_in_page(String tab, String page) {
		switch (page) {
		case "index":
			IndexPage.ClickTab(tab);
			break;
		default:
			Assert.fail("No Such page.");
		}
	}

	@When("I select {string} as {string} city in {string} page")
	public void selectCityInPage(String city, String departureOrDestination, String page) {
		switch (page) {
		case "index":
			IndexPage.selectCity(departureOrDestination, city);
			;
			break;
		default:
			Assert.fail("No Such page.");
		}
	}

	@When("I click on {string} button in {string} page")
	public void clickButtonInPage(String button, String page) {
		switch (page) {
		case "index":
			IndexPage.ClickButton(button);
			break;
		case "purchase":
			PurchasePage.clickButton(button);
			break;
		default:
			Assert.fail("No Such page.");
		}
	}

	@Then("I validate {string} text in {string} page")
	public void validateTextInPage(String expectedText, String page) {
		switch (page) {
		case "index":
			IndexPage.validateText(expectedText);
			break;
		case "reserve":
			ReservePage.validateText(expectedText);
			break;
		case "confirmation":
			ConfirmationPage.validateText(expectedText);
			break;
		default:
			Assert.fail("No Such page.");
		}

	}

	@When("I click on {string} button for {string} flight number in {string} page")
	public void clickButtonForChosenFlightInPage(String button, String flightNumber, String page) {
		switch (page) {
		case "reserve":
			ReservePage.chooseFlight(button, flightNumber);
			break;
		default:
			Assert.fail("No Such page.");
		}
	}

	@When("I fill the form in {string} page")
	public void fillFormInPage(String page, DataTable personalDetails) {

		List<Map<String, String>> personalDetailsTable = personalDetails.asMaps();
		List<String> fields = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		for (Map<String, String> v : personalDetailsTable) {
			for (Object field : v.keySet()) {
				fields.add(String.valueOf(field));
			}
			for (Object value : v.values()) {
				values.add(String.valueOf(value));
			}
		}

		switch (page) {
		case "purchase":
			PurchasePage.fillForm(fields, values);
			break;
		default:
			Assert.fail("No Such page.");
		}
	}

	@Then("I validate {string} has been generated in {string} page")
	public void validateGeneratedConfirmationDetails(String field, String page) {
		switch (page) {
		case "confirmation":
			ConfirmationPage.validateConfirmationFieldHasValues(field);
			break;
		default:
			Assert.fail("No such page.");
		}
	}

	@Then("I validate {string} column has {string} in {string} page")
	public void validateColumnNameAsCity(String columnName, String city, String page) {
		switch (page) {
		case "reserve":
			ReservePage.validateDepartOrArrivesCity(columnName, city);
			break;
		default:
			Assert.fail("No such page.");
		}
	}
}
