package RdzIGameTestSteps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RdzIGameTestLoginSteps {
	
	private WebDriver driver;

	@Given("^I open Login form$")
	public void OpenLoginPage() {
		//Get web driver from common
		driver = RdzIGameTestCommonSteps.GetWebDriver();
			
		//Open Login form
		RdzIGameTestCommonSteps.LogToOuput("Opening login form");
		driver.findElement(By.cssSelector("a[data-test-name='Button'")).click();
	}

	@When("^I enter username as \"(.*)\"$")
	public void SetUsername(String uname) {
		//Enter user name
		RdzIGameTestCommonSteps.LogToOuput("Setting usernam");
		driver.findElement(By.cssSelector("input[data-test-name='UsernameField'")).sendKeys(uname);	
	}

	@When("^I enter password as \"(.*)\"$")
	public void SetPassword(String pass) {
		//Enter password name
		RdzIGameTestCommonSteps.LogToOuput("Setting password");
		driver.findElement(By.cssSelector("input[data-test-name='PasswordField'")).sendKeys(pass);	
	}

	@When("^I press login button$")
	public void ClickLogin() {
		//Click on login button
		RdzIGameTestCommonSteps.LogToOuput("Click Login Button");
		driver.findElement(By.cssSelector("button[data-test-name$='LoginButton'")).click();	
	}

	@Then("^Log In button should remain disabled$")
	public void IsLogInDisabled() {		
		RdzIGameTestCommonSteps.LogToOuput("Verifying that Login Button is disabled");
		//Find login button
		WebElement element = driver.findElement(By.cssSelector("button[data-test-name$='LoginButton'"));
		
		//Check if login button is disabled
		boolean isEnabled = element.isEnabled();
		
		//Fail test if button is not enabled
		Assert.assertFalse("Login button is not disabled!", isEnabled);
	}

	@Then("^Validation message should appear$")
	public void IsValidationMessageDisplayed() throws InterruptedException {	
		RdzIGameTestCommonSteps.LogToOuput("Verifying that validation message is displayed");			
		//Check if validation message exists 
		boolean exists = RdzIGameTestCommonSteps.WebElementExist(By.cssSelector("div[data-test-name='messagebox'"), 15);
		
		//Fail test if message doesn't exist
		Assert.assertTrue("Validation message is not present!", exists);
	}	

	@Then("^Mesage text should be \"(.*)\"$")
	public void CheckErrorMessage(String msg) {
		RdzIGameTestCommonSteps.LogToOuput("Verifying that validation message text is correct");
		//Enter user name
		String msgText = driver.findElement(By.cssSelector("div[data-test-name='messagebox'")).getText();
		
		//Fail text if message text is wrong
		Assert.assertTrue("Message text is wrong!", msg.equalsIgnoreCase(msgText));
	}

	@Then("^User should be loged in$")
	public void IsUserLoggedIn() throws InterruptedException {	
		RdzIGameTestCommonSteps.LogToOuput("Verifying that user is logged in");			
		//Check if validation message exists 
		boolean exists = RdzIGameTestCommonSteps.WebElementExist(By.cssSelector("div[data-test-name='user-section'"), 15);
		
		//Fail test if message doesn't exist
		Assert.assertTrue("Validation message is not present!", exists);
	}
}

