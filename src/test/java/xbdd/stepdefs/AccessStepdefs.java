/**
 * Copyright (C) 2015 Orion Health (Orchestral Development Ltd)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xbdd.stepdefs;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import xbdd.XbddDriver;
import xbdd.utils.XBDDInstance;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class AccessStepdefs extends XbddDriver{

	@Before
	public void before() {
		startWebDriver();
	}

	@After
	public void after() {
		stopWebDriver();
	}

	@Given ("^user go to google site$")
	public void openGooglePage() {
		driver.navigate().to("https://www.google.com");
	}

	@Given("^Enter search term '(.*?)'$")
	public void searchFor(String searchTerm) {
		WebElement searchField = driver.findElement(By.id("searchInput"));
		searchField.sendKeys(searchTerm);
	}

	@When("^Do search$")
	public void clickSearchButton() {
		driver.findElement(By.id("lst-ib")).click();
		driver.findElement(By.id("lst-ib")).sendKeys("football");
		wait(1);
		driver.findElement(By.className("lsb")).click();
		wait(1);
	}
	//http://www.bbc.com/sport/football
	//By.cssSelector("a[data-racename = 'All Races']")
	// Football - BBC Sport
	@Then("^Football page shown as '(.*?)'$")
	public void verifyElement(String elementText) {
		WebElement element = driver.findElement(By.cssSelector("a[data-href = 'http://www.bbc.com/sport/football']"));
		assertEquals(elementText, element.getText());
	}
}