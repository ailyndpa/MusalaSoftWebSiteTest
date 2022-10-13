package TestCases;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


public class TestCase1 {
	protected WebDriver driver;

	@BeforeClass
	@Parameters({ "URL", "BrowserType" })
	public void beforeClass(String url, String BrowserType) throws Exception {
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");
		driver.findElement(By.xpath("//button[@type='button']/span[text()='Contact us']")).click();
		driver.findElement(By.id("cf-1")).sendKeys("Ailyn");
		driver.findElement(By.cssSelector("input[name='your-subject']")).sendKeys("First test");
		driver.findElement(By.cssSelector("textarea[name='your-message']")).sendKeys("This is the message of the first test");
		
		/*4) Para el caso de prueba 1, prepare un archivo de datos de prueba en un formato de su elección, 
		el archivo debe contener 5 conjuntos de direcciones de correo electrónico no válidas. Realice la 
		prueba para que se ejecute 5 veces con cada dirección de correo electrónico.*/
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("usuario.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String errorMessage=driver.findElement(By.xpath("//span[@class='wpcf7-not-valid-tip']")).getText();
		Assert.assertTrue(errorMessage.equals("The e-mail address entered is invalid."), "The error message displayed is not as expected.");
		driver.close();
	}
	

}
