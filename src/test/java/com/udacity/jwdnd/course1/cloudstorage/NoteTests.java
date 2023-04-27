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
public class NoteTests {
    
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
        clickNotesButtonFromHomePage();
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

    private void clickNotesButtonFromHomePage() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        WebElement notesButton = driver.findElement(By.id("nav-notes-tab"));
        notesButton.click();
        try {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notes-section")));
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

	@Test
	public void createNote() {
		// Clicking add new note button
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		WebElement addANewNoteButton = driver.findElement(By.id("add-new-note-button"));
		addANewNoteButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));

		String title = "mynote";
		String description = "123";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement inputTitle = driver.findElement(By.id("note-title"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement inputDescription = driver.findElement(By.id("note-description"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-save")));
		WebElement saveNotesButton = driver.findElement(By.id("note-save"));
		saveNotesButton.click();

		Assertions.assertTrue(driver.findElement(By.id("result-success")).getText().contains("The operation was a success"));

		// after clicking the save button we are sent to the success page
		returnHome();
		clickNotesButtonFromHomePage();
	}

	@Test
	public void deleteNote() {
		boolean isEditButtonPresent = false;

		createNote();
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-note-button")));
		WebElement deleteNoteButton = driver.findElement(By.id("delete-note-button"));
        deleteNoteButton.click();
		Assertions.assertTrue(driver.findElement(By.id("result-success")).getText().contains("The operation was a success"));

		// after clicking the save button we are sent to the success page
		returnHome();
		clickNotesButtonFromHomePage();

		try {
			driver.findElement(By.id("edit-note-button"));
			isEditButtonPresent = true;
		} catch(NoSuchElementException e) {
			isEditButtonPresent = false;
		}
		Assertions.assertFalse(isEditButtonPresent);
	}

	@Test
	public void editNote() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		createNote();
		// Clicking edit button
		WebElement editNoteButton = driver.findElement(By.id("edit-note-button"));
		editNoteButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));

		// Editing Note
		String title = "myothernote";
		String description = "456";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement inputTitle = driver.findElement(By.id("note-title"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement inputDescription = driver.findElement(By.id("note-description"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-save")));
		WebElement saveNotesButton = driver.findElement(By.id("note-save"));
		saveNotesButton.click();


		Assertions.assertTrue(driver.findElement(By.id("result-success")).getText().contains("The operation was a success"));

		// after clicking the save button we are sent to the success page
		returnHome();
		clickNotesButtonFromHomePage();



		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("note-title-text")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("note-description-text")));
        WebElement noteTitleText = driver.findElement(By.id("note-title-text"));
        Assertions.assertEquals("myothernote", noteTitleText.getText());
        WebElement noteDescriptionText = driver.findElement(By.id("note-description-text"));
        Assertions.assertEquals("456", noteDescriptionText.getText());

	}
}
