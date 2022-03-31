package com.statista.exercises;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class CompanySeleniumApplicationTests {

	private static final File recordFolder = new File("./recordings");

	@Container
	private static BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>()
			.withCapabilities(new ChromeOptions())
			.withRecordingMode(
					BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
					recordFolder,
					VncRecordingContainer.VncRecordingFormat.MP4
			);

	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}

	@Test
	public void test (){

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.statista.com/companydb/search");

		WebElement acceptCookie = driver.findElement(By.id("onetrust-accept-btn-handler"));
		acceptCookie.click();

		WebElement searchInput = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[1]/div/div/ul/li/input"));
		searchInput.sendKeys("statista");
		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[1]/div/div/div/div[2]/button"));
		searchButton.click();

	}


}
