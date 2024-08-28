package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
	private By formSelector = By.xpath("/html/body/ul/li[1]/a");
	private By calcSelector = By.xpath("/html/body/ul/li[3]/a");
	
	public HomePage(WebDriver dr) {
		super(dr);
	}
	
	public boolean calculatorLinkExists() {
		return !driver.findElements(calcSelector).isEmpty();
	}
	
	public boolean emailLinkExists() {
		return !driver.findElements(formSelector).isEmpty();
	}
	
	public CalculatorPage clickCalculatorLink() {
		driver.findElement(calcSelector).click();
		return new CalculatorPage();
	}
	
	public EmailPage clickEmailLink() {
		driver.findElement(formSelector).click();
		return new EmailPage(driver);
	}
}
