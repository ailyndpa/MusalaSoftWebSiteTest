package TestCases;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestCase2 {
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
			/*
			 * ProfilesIni profile = new ProfilesIni();
			 * profile.getProfile("default").setPreference("network.cookie.cookieBehavior",
			 * 2); FirefoxOptions options = new FirefoxOptions();
			 * options.setProfile(profile.getProfile("default")); driver = new
			 * FirefoxDriver(options);
			 */
			break;
		default:
			throw new Exception("Browser" + BrowserType + " not supported");
		}
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test
	public void company() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='menu']/ul[@id='menu-main-nav-1']/li[1]/a")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.musala.com/company/");
		boolean leadership = driver.findElements(By.xpath("//section[@class='company-members']")).size() != 0;
		Assert.assertTrue(leadership, "Leadership section not found");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.id("wt-cli-accept-all-btn")).click();
		driver.findElement(By.xpath("//a[@href='https://www.facebook.com/MusalaSoft?fref=ts']")).click();
		 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		 driver.switchTo().window(newTab.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/MusalaSoft?fref=ts");
		Thread.sleep(10000);
		List <WebElement> photo=driver.findElements(By.xpath("//div/div/div/div/div/div/div[@class='x9f619 x1n2onr6 x1ja2u2z x78zum5 x2lah0s xl56j7k x1qjc9v5 xozqiw3 x1q0g3np x1l90r2v x1ve1bff']/div/div/div/div/div/div/a[@aria-label='Musala Soft profile photo']"));
		
		//Assert.assertTrue(photo.isDisplayed(), ":::::::::::");
		System.out.println(">>>>>>>>>>>>>"+ photo.size());
	}

}
