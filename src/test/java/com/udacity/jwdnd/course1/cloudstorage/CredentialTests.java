package com.udacity.jwdnd.course1.cloudstorage;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {
    
    @LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
        doMockSignUp("Julius", "Mazer", "jmazer", "123");
        doLogIn("jmazer", "123");
        clickCredentialsButtonFromHomePage();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

    /**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

    private void clickCredentialsButtonFromHomePage() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        WebElement notesButton = driver.findElement(By.id("nav-credentials-tab"));
        notesButton.click();
        try {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentials-section")));
		} catch (org.openqa.selenium.TimeoutException e) {
			fail("Failed to click notes button from home page");
		}
    }

    private void returnHome() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        WebElement homeLink = driver.findElement(By.id("return-home"));
        homeLink.click();
        try {
			webDriverWait.until(ExpectedConditions.titleIs("Home"));
		} catch (org.openqa.selenium.TimeoutException e) {
			fail("Failed to return home from success page");
		}
    }

    private void createOrEditCredential(String url, String username, String password) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalcredentialurl")));
		WebElement inputUrl = driver.findElement(By.id("modalcredentialurl"));
        inputUrl.clear();
		inputUrl.sendKeys(url);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalcredentialusername")));
		WebElement inputUsername = driver.findElement(By.id("modalcredentialusername"));
        inputUsername.clear();
		inputUsername.sendKeys(username);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalcredentialpassword")));
		WebElement inputPassword = driver.findElement(By.id("modalcredentialpassword"));
        inputPassword.clear();
		inputPassword.sendKeys(password);


        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-save")));
		WebElement saveCredentialButton = driver.findElement(By.id("credential-save"));
		saveCredentialButton.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));

        // after clicking the save button we are sent to the success page
        returnHome();
        clickCredentialsButtonFromHomePage();
    }

	@Test
	public void createCredential() {
		// Clicking add new credential button
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		WebElement addANewCredentialButton = driver.findElement(By.id("add-new-credential-button"));
		addANewCredentialButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));

		// Creating Credential
		createOrEditCredential("https://google.com", "jmazer", "123");
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-url")));
		WebElement modalcredentialUrl = driver.findElement(By.id("credential-url"));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-username")));
		WebElement modalcredentialUsername = driver.findElement(By.id("credential-username"));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-password")));
		WebElement modalcredentialPassword = driver.findElement(By.id("credential-password"));
		Assertions.assertEquals("https://google.com", modalcredentialUrl.getText());
		Assertions.assertEquals("jmazer", modalcredentialUsername.getText());
		Assertions.assertEquals(24, modalcredentialPassword.getText().length());
	}

	@Test
	public void deleteCredential() {
		boolean isEditButtonPresent = false;
		createCredential();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-credential-button")));
		WebElement deleteNoteButton = driver.findElement(By.id("delete-credential-button"));
        deleteNoteButton.click();
		Assertions.assertTrue(driver.findElement(By.id("result-success")).getText().contains("The operation was a success"));

		// after clicking the save button we are sent to the success page
		returnHome();
		clickCredentialsButtonFromHomePage();

		try {
			driver.findElement(By.id("edit-credential-button"));
			isEditButtonPresent = true;
		} catch(NoSuchElementException e) {
			isEditButtonPresent = false;
		}
		Assertions.assertFalse(isEditButtonPresent);
	}

	@Test
	public void editCredential() {
		createCredential();
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		// Clicking edit button
		WebElement editCredentialButton = driver.findElement(By.id("edit-credential-button"));
		editCredentialButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));

		// Check that password is decrypted in the modal view 
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalcredentialpassword")));
		WebElement modalCredentialPassword = driver.findElement(By.id("modalcredentialpassword"));
		Assertions.assertEquals("123", modalCredentialPassword.getAttribute("value"));

		// Editing Credential
		createOrEditCredential("https://yahoo.com", "jmazer1", "567");
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-url")));
		WebElement modalcredentialUrl = driver.findElement(By.id("credential-url"));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-username")));
		WebElement modalcredentialUsername = driver.findElement(By.id("credential-username"));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("credential-password")));
		WebElement modalcredentialPassword = driver.findElement(By.id("credential-password"));
		Assertions.assertEquals("https://yahoo.com", modalcredentialUrl.getText());
		Assertions.assertEquals("jmazer1", modalcredentialUsername.getText());
		Assertions.assertEquals(24, modalcredentialPassword.getText().length());

		// Clicking edit button
		editCredentialButton = driver.findElement(By.id("edit-credential-button"));
		editCredentialButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));

		// Check that password is decrypted in the modal view 
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalcredentialpassword")));
		modalCredentialPassword = driver.findElement(By.id("modalcredentialpassword"));
		Assertions.assertEquals("567", modalCredentialPassword.getAttribute("value"));
	}
}
