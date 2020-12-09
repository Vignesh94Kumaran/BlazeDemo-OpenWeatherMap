package blazeDemoUIAutomation.helperClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonSeleniumActions {
	public static WebDriver driver;

	public static void navigateToApplication(String url) {
		driver.get(url);
		waitForPageToLoad();
	}

	public static void waitForPageToLoad() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	public static void click(String actionElement) {
		waitForElement(actionElement);
		driver.findElement(By.xpath(actionElement)).click();
		waitForPageToLoad();
	}

	public static void selectDropdown(String element, String value) {
		Select dropdown = new Select(driver.findElement(By.xpath(element)));
		dropdown.selectByVisibleText(value);
	}

	public static String getText(String element) {
		return driver.findElement(By.xpath(element)).getText();
	}

	public static void sendText(String element, String inputText) {
		waitForElement(element);
		driver.findElement(By.xpath(element)).sendKeys(inputText);
	}

	public static int numberOfElements(String element) {
		return driver.findElements(By.xpath(element)).size();
	}

	public static void waitForElement(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
	}
}
