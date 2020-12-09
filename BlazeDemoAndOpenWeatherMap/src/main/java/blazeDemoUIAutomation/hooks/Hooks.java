package blazeDemoUIAutomation.hooks;

import org.openqa.selenium.chrome.ChromeDriver;

import blazeDemoUIAutomation.helperClasses.CommonSeleniumActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@UIAutomation")
	public void BeforeScenario() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		CommonSeleniumActions.driver = new ChromeDriver();
		CommonSeleniumActions.driver.manage().window().maximize();
	}

	@After("@UIAutomation")
	public void AfterScenario() {
		CommonSeleniumActions.driver.close();
	}
}
