package blazeDemoUIAutomation.pages;

import org.junit.Assert;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;

public class IndexPage extends CommonSeleniumActions implements ObjectRepository {

	public static void validateText(String expectedText) {
		String actualText = getText(indexPageHeading).trim();
		Assert.assertEquals(expectedText, actualText);
	}

	public static void ClickTab(String tab) {
		click("//a[text()='" + tab + "']");
		waitForPageToLoad();
	}

	public static void selectCity(String departureOrDestination, String city) {
		if (departureOrDestination.equalsIgnoreCase("departure"))
			selectDropdown(departureDropdown, city);
		else if (departureOrDestination.equalsIgnoreCase("destination"))
			selectDropdown(destinationDropdown, city);
		else
			Assert.fail("No such dropdown.");
	}

	public static void ClickButton(String button) {
		click("//input[@value='" + button + "' and @type='submit']");
		waitForPageToLoad();
	}
}
