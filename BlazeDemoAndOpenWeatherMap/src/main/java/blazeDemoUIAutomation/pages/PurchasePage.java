package blazeDemoUIAutomation.pages;

import java.util.List;

import org.junit.Assert;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;

public class PurchasePage extends CommonSeleniumActions implements ObjectRepository {

	public static void fillForm(List<String> fields, List<String> values) {
		String field, fieldValue;
		for (int columns = 0; columns < fields.size(); columns++) {
			field = fields.get(columns);
			fieldValue = values.get(columns);
			String elementLocation = "";

			if (field.equals("Card Type")) {
				elementLocation = CardTypeDropdown;
				selectDropdown(elementLocation, fieldValue);
			} else {

				switch (field) {
				case "Name":
					elementLocation = customerNameTextField;
					break;
				case "Address":
					elementLocation = customerAddressTextField;
					break;
				case "City":
					elementLocation = customerCityTextField;
					break;
				case "State":
					elementLocation = customerStateTextField;
					break;
				case "Zip Code":
					elementLocation = customerZipCodeTextField;
					break;
				case "Credit Card Number":
					elementLocation = customerCreditCardNumberField;
					break;
				case "Month":
					elementLocation = customerCreditCardMonthNumberField;
					break;
				case "Year":
					elementLocation = customerCreditCardYearNumberField;
					break;
				case "Name on Card":
					elementLocation = customerNameOnCardTextField;
					break;
				default:
					Assert.fail("No Such field.");
				}
			}

			sendText(elementLocation, fieldValue);
		}
	}

	public static void clickButton(String button) {
		click("//input[@type='submit' and @value='" + button + "']");
	}
}
