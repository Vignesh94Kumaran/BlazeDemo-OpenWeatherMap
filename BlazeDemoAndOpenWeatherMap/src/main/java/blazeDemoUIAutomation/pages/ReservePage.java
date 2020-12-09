package blazeDemoUIAutomation.pages;

import org.junit.Assert;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;

public class ReservePage extends CommonSeleniumActions implements ObjectRepository {

	public static void validateText(String expectedText) {
		String actualText = getText(reservePageHeading).trim();
		Assert.assertEquals(expectedText, actualText);
	}

	public static void chooseFlight(String button, String flightNumber) {
		click("//tr/td[.=\"" + flightNumber + "\"]/preceding-sibling::td/input[@type='submit' and @value='" + button
				+ "']");
		waitForPageToLoad();
	}

	public static void validateDepartOrArrivesCity(String columnName, String city) {
		int numberOfColumnNames = numberOfElements("//th");
		String column = null;
		int columnNumber = 0;
		boolean columnPresent = false;
		for (columnNumber = 1; columnNumber < numberOfColumnNames; columnNumber++) {
			column = getText("//th[" + columnNumber + "]");

			if (column.contains(columnName)) {
				Assert.assertTrue(column.contains(city));
				columnPresent = true;
				break;
			} else if (column.contains(columnName)) {
				Assert.assertTrue(column.contains(city));
				columnPresent = true;
				break;
			}
			if (columnPresent) {
				Assert.fail("No such column present.");
			}
		}
	}
}
