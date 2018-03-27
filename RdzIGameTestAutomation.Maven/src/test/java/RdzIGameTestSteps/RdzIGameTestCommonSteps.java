package RdzIGameTestSteps;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;


public class RdzIGameTestCommonSteps {	
	
	private static WebDriver driver;

	@Given("^I open IGame page in \"([^\"]*)\"$")
	public void OpenIGameWebPage(String browser) {		
		//Open WebDriver
		LogToOuput("Opening browser");
		switch(browser.toLowerCase()) {
			case "firefox":	driver =  new FirefoxDriver();	
							break;
			case "chrome":
			default: 		driver = new ChromeDriver();
							break;
		}
		
		//Maximize window
		LogToOuput("Maximazing browser windowr");
		driver.manage().window().maximize();
		
		//Open IGame web page
		LogToOuput("Navigating to IGame webpage");
		driver.get("https://www.igame.com/");
	}
	
	@After
	public void CloseBrowser(Scenario scenario) throws InterruptedException {
		this.CheckScenarioResult(scenario, false);
		
		//Closing webdriver
		LogToOuput("Closing browser");
		driver.quit();
	}
	
	@After("@DoLogOut")
	public void LogoutAndCloseBrowser(Scenario scenario) throws InterruptedException {
		this.CheckScenarioResult(scenario, true);

		//Closing webdriver
		LogToOuput("Closing browser");
		driver.quit();
	}	
	
	public static WebDriver GetWebDriver() {				
		return driver;
	}
	
	public static boolean WebElementExist(By selector, long secondWait) throws InterruptedException {
		//Check if target element exist
		boolean exists = !driver.findElements(selector).isEmpty();
		//Setting time to wait
		LocalDateTime waitUntil = LocalDateTime.now().plusSeconds(secondWait);
		//If element doesn't exist wait to appear unit time set
		while(!exists && waitUntil.isAfter(LocalDateTime.now()))
		{
			Thread.sleep(500);
			exists = !driver.findElements(selector).isEmpty();			
		}
		
		return exists;
	}
	
	public static void LogToOuput(String text ) {
		System.out.print("<<<<< ");
		System.out.print(text);
		System.out.println(" >>>>>");
	}
	
	private void CheckScenarioResult(Scenario scenario, boolean executeLogout) throws InterruptedException {
		if (scenario.isFailed())
		{
			LogToOuput("Taking screenshot");			
			scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
			scenario.write("SCENARIO FAILED!");
		}
		else
		{
			//If we need to logout and there is logout footer do logout
			if (executeLogout && WebElementExist(By.cssSelector("div[data-test-name='footer-logout'"), 15))
			{
				LogToOuput("Log out");
				driver.findElement(By.cssSelector("div[data-test-name='footer-logout'")).click();
			}
				
			scenario.write("SCENARIO PASSED");
		}		
	}
}
