package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.DriverFactory;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test(priority = 1, description = "Verify that user can log in with valid credentials")
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isPageLoaded(), "Products page is not loaded after login");
    }

    @Test(priority = 2, description = "Verify that an error message is displayed for invalid credentials")
    public void invalidLoginTest() {
        loginPage.login("invalid_user", "invalid_pass");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message is not displayed");
        Assert.assertTrue(errorMessage.contains("Username and password do not match"), "Error message is incorrect");
    }

    @Test(priority = 3, description = "Verify that an error message is displayed when username is empty")
    public void emptyUsernameTest() {
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message is not displayed");
        Assert.assertTrue(errorMessage.contains("Username is required"), "Error message is incorrect");
    }

    @Test(priority = 4, description = "Verify that an error message is displayed when password is empty")
    public void emptyPasswordTest() {
        loginPage.enterUsername("standard_user");
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message is not displayed");
        Assert.assertTrue(errorMessage.contains("Password is required"), "Error message is incorrect");
    }

    @Test(priority = 5, description = "Verify that user can log out after login")
    public void logoutTest() {
        // Login with valid credentials
        loginPage.login("standard_user", "secret_sauce");

        // Assuming logout is possible with a menu option
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        // Verify if user is redirected back to the login page
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout failed, not redirected to login page");
    }
}
