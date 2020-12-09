package blazeDemoUIAutomation.pages;

import org.junit.Assert;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;

public class ConfirmationPage extends CommonSeleniumActions implements ObjectRepository {

	public static void validateText(String expectedText) {
		String actualText = getText(confirmationPageHeading).trim();
		Assert.assertEquals(expectedText, actualText);
	}

	public static void validateConfirmationFieldHasValues(String field) {
		String actualFieldValue = getText("//td[text()='" + field + "']/following-sibling::td");
		Assert.assertTrue(field + " - " + actualFieldValue + " is generated.", actualFieldValue != null);
	}
}
