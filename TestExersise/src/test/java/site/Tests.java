package site;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;

public class Tests {
	private static String domain = "http://localhost:5000/";
	private static String emailsPath = System.getProperty("user.dir") + "\\src\\test\\resources\\emails.csv";
	private WebDriver driver;
	private HomePage homePage;
	
	public String[][] emailProviderMethod() throws IOException, CsvException {
		Reader reader = Files.newBufferedReader(Path.of(emailsPath));
		CSVReader csvReader = new CSVReader(reader);
		String[][] allRows = csvReader.readAll().toArray(new String[0][0]);
		csvReader.close();
		return allRows;
	}
	
	@BeforeTest
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(domain);
		homePage = this.goToHomePage();
	}
	
	@Test
	public void HomePageTest() {
		String expectedTitle = "Welcome to the Carlo's Website";
		Assert.assertEquals(homePage.getTitle(), expectedTitle);
		Assert.assertTrue(homePage.calculatorLinkExists());
		Assert.assertTrue(homePage.emailLinkExists());
	}
	
	@Test
	public void EmailTest() throws IOException, CsvException {
		homePage = this.goToHomePage();
		
		String[][] rows = emailProviderMethod();
		
		for(String[] nextRecord: rows) {
			EmailPage emailPage = homePage.clickEmailLink();
			Assert.assertEquals(emailPage.getTitle(), "Submit Your Details");
			
		    String name = nextRecord[0];
		    String email = nextRecord[1];
		    homePage = emailPage.submitEntry(name, email);
			Assert.assertEquals(emailPage.getTitle(), "Welcome to the Carlo's Website");
		}
	}
	
	@Test
	public void DataDrivenEmailTest() {
		// TODO: Create a data driven version of EmailTest()
		Assert.fail();
	}
	
	// TODO: Create tests that tests the calculator that does division, cover all cases
	@Test
	public void CalculatorHappyPathTest() {
		Assert.fail();
	}
	
	private HomePage goToHomePage() {
		return new HomePage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
	        driver.quit();
	    }
	}
}
