package TestCases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestCase3 {
	protected WebDriver driver;

	@Test
	public void company() throws InterruptedException {
		driver.findElement(By.linkText("Careers")).click();
	}

}
