package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
	private WebDriverWait wait;
    
    // Constructor
    public LoginPage(WebDriver driver) {
    	this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    // Locators for the login page
    By usernameField = By.id("standard_user");
    By passwordField = By.id("secret_sauce");
    By loginButton = By.id("login-button");
    By errorMessage = By.cssSelector(".error-message-container");

   

    // Method to enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
    	  driver.findElement(passwordField).sendKeys(password);

    }

    // Method to click login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Method to get error message
    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() ? errorElement.getText() : null;
    }

    // Method to log in
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
