package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
    }

    // Correct locators for the login page
    By usernameField = By.id("user-name");  // Correct locator for the username field
    By passwordField = By.id("password");   // Correct locator for the password field
    By loginButton = By.id("login-button");  // Using the correct locator for the login button
    By errorMessage = By.cssSelector(".error-message-container");

    // Method to enter username
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    // Method to click login button (explicit wait for clickable state)
    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        try {
            loginBtn.click();  // Try regular click first
        } catch (Exception e) {
            // Fallback to JavaScript click if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
        }
    }


    // Method to get error message
    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElement.isDisplayed() ? errorElement.getText() : null;
    }

    // Method to log in
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
