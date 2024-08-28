package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	protected WebDriver driver;
	private By titleSelector = By.cssSelector("body > h1");

	public BasePage(WebDriver dr) {
		driver = dr;
	}

	public String getTitle() {
		return driver.findElement(titleSelector).getText();
	}
}
