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
		boolean profilePicture = driver.findElements(By.xpath("(//*[name()='image'])[1]")).size() != 0;
		Assert.assertTrue(profilePicture, "Musala Soft's profile picture does not appear on the new page.");
		driver.quit();
	}

}
