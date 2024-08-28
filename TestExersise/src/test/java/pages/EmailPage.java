package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailPage extends HomePage {
	private By nameField = By.cssSelector("#name");
	private By emailField = By.cssSelector("#email");
	private By submitButton = By.xpath("/html/body/form/input[3]");
	
	public EmailPage(WebDriver dr) {
		super(dr);
	}
	
	public HomePage submitEntry(String name, String email) {
		driver.findElement(nameField).sendKeys(name);
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(submitButton).click();
		return new HomePage(driver);
	}

}
