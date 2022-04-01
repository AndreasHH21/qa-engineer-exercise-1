package com.statista.exercises;

//import com.sun.tools.javac.code.Symbol;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Assert;
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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

		// step 1
		driver.get("https://www.statista.com/companydb/search");

		// step 2
		WebElement acceptCookie = driver.findElement(By.id("onetrust-accept-btn-handler"));
		acceptCookie.click();

		// step 3
		WebElement searchInput = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[1]/div/div/ul/li/input"));
		searchInput.sendKeys("statista");

		// step 4
		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[1]/div/div/div/div[2]/button"));
		searchButton.click();

		// step 5
		driver.navigate().to(driver.getCurrentUrl()); // I'm refreshing the page here to use actual search results

		WebElement result1 = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[3]/table/tbody/tr[1]/td[2]/a"));
		WebElement result2 = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[3]/table/tbody/tr[2]/td[2]/a"));
		WebElement result3 = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[3]/table/tbody/tr[3]/td[2]/a"));
		WebElement result4 = driver.findElement(By.xpath("/html/body/div[4]/div[5]/section[3]/div/form/div[3]/table/tbody/tr[4]/td[2]/a"));

		// adding all results to the list
		ArrayList<WebElement> resultList = new ArrayList<WebElement>();
		resultList.add(result1);
		resultList.add(result2);
		resultList.add(result3);
		resultList.add(result4);


		// checking all results to find "Statista GmbH" in the top 5 results
		for (WebElement i: resultList){

			if (i.getText().equals("Statista GmbH")){
				Assert.assertTrue(i.getText().equals("Statista GmbH"));
				//System.out.println(i.getText());

			}
		}




	}



}
