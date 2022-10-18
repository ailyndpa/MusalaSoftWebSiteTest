package TestCases;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestCase1 {
	protected WebDriver driver;

	@BeforeTest
	@Parameters({ "URL", "BrowserType" })
	public void beforeTest(String url, String BrowserType) throws Exception {
		switch (BrowserType) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			throw new Exception("Browser" + BrowserType + " not supported");
		}
		driver.get(url);
		driver.manage().window().maximize();
	}
	@Test
	public void contactUs() throws InterruptedException, FileNotFoundException, IOException {
		File doc = new File(".\\src\\test\\resources\\invalid address.txt");
		BufferedReader obj = new BufferedReader(new FileReader(doc));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");
		driver.findElement(By.xpath("//button[@type='button']/span[text()='Contact us']")).click();
		driver.findElement(By.id("cf-1")).sendKeys("Ailyn");
		driver.findElement(By.cssSelector("input[name='your-subject']")).sendKeys("First test");
		driver.findElement(By.cssSelector("textarea[name='your-message']")).sendKeys("This is the message of the first test");
		
		  String currentAddress;
		  while ((currentAddress = obj.readLine()) != null) {
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(currentAddress);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);
			String errorMessage = driver.findElement(By.xpath("//span[@class='wpcf7-not-valid-tip']")).getText();
			Assert.assertTrue(errorMessage.equals("The e-mail address entered is invalid."),"The error message displayed is not as expected.");
			driver.findElement(By.xpath("//input[@type='email']")).clear();
		  }		 
	}
	@AfterClass
	public void After() {
		 driver.close();
	}
}
