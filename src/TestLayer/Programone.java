package TestLayer;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Programone {
	public static WebDriver driver;

	@BeforeClass
	public static void launchbrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Java Eclipse\\Chrome driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public static void passURL() throws InterruptedException {
		driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
	}

	@Test(priority = 4)
	public void Admin() throws InterruptedException {

		driver.findElement(By.id("btnLogin")).click();
		
		// Administrator User Validation
		String actualRole = driver.findElement(By.xpath("//*[text()='Global HR Manager']")).getText();
		Assert.assertTrue(actualRole.contains("Global HR Manager"));
		System.out.println("Admin Validation Done");
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//span[text()='Employee List']")).click();
		Thread.sleep(10000);
		List<WebElement> trow = driver.findElements(By.tagName("tr"));
		String data = null;
		List<WebElement> tdata = trow.get(1).findElements(By.tagName("td"));
		for (int i = 0; i < tdata.size(); i++) {
			data = tdata.get(i).getText();
			System.out.println(data);
		}
	}
	
	@Test(priority = 1)
	public void SystemAdmin() throws InterruptedException {
		
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("(//a[@class='login-as'])[1]")).click();

		// System Administrator User Validation
		String actualRole = driver.findElement(By.xpath("//span[@id='account-name']")).getText();
		Assert.assertTrue(actualRole.contains("System Admin"));
		System.out.println("System Admin Validation Done");
	}

	
	@Test(priority = 2)
	public void ESSUser() throws InterruptedException {
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("(//a[@class='login-as'])[3]")).click();
		// User Validation
		String actualRole = driver.findElement(By.xpath("//span[@id='account-job']")).getText();
		Assert.assertTrue(actualRole.contains("Senior Technical Support Engineer"));
		System.out.println("ESS User Validation Done");
		
	}

	
	@Test(priority = 3)
	public void firstLevelSupervisor() throws InterruptedException {
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("(//a[@class='login-as'])[4]")).click();
		// User Validation
		String actualRole = driver.findElement(By.xpath("//span[@id='account-job']")).getText();
		Assert.assertTrue(actualRole.contains("Production Manager"));
		System.out.println("1st Level Supervisor Validation Done");

	}

	@AfterMethod
	public void logoutPage() throws InterruptedException {
		
		driver.findElement(By.xpath("//i[text()='keyboard_arrow_down']")).click();
		Thread.sleep(2000);
		WebElement logout = driver.findElement(By.id("logoutLink"));
		logout.click();
	}
	
	@AfterClass
	public void quitpage() {
		driver.quit();
	}
	
}
