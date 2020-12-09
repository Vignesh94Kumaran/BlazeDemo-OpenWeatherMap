package blazeDemoUIAutomation.pages;

public interface ObjectRepository {

	// -------Confirmation Page Elements-------//
	String confirmationPageHeading = "//h1";

	// -------Index Page Elements-------//
	String indexPageHeading = "//h1";

	// -------Reserve Page Elements-------//
	String reservePageHeading = "//h3";

	// -------Purchase Page Elements-------//
	String departureDropdown = "//select[@name='fromPort']";
	String destinationDropdown = "//select[@name='toPort']";
	String CardTypeDropdown = "//select[@id ='cardType']";
	String customerNameTextField = "//input[@id='inputName']";
	String customerAddressTextField = "//input[@id='address']";
	String customerCityTextField = "//input[@id='city']";
	String customerStateTextField = "//input[@id='state']";
	String customerZipCodeTextField = "//input[@id='zipCode']";
	String customerCreditCardNumberField = "//input[@id='creditCardNumber']";
	String customerCreditCardMonthNumberField = "//input[@id='creditCardMonth']";
	String customerCreditCardYearNumberField = "//input[@id='creditCardYear']";
	String customerNameOnCardTextField = "//input[@id='nameOnCard']";

}
