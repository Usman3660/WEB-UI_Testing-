package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.DriverFactory;

public class LoginStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    private WebDriverWait wait;

    @Given("^the user is on the Sauce Labs Demo App login page$")
    public void givenUserIsOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("^the user enters a valid username \"([^\"]*)\"$")
    public void whenUserEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("^the user enters a valid password \"([^\"]*)\"$")
    public void whenUserEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("^the user clicks the \"([^\"]*)\" button$")
    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        loginBtn.click();  // Perform a regular click
    }


    @Then("^the user should be redirected to the products page$")
    public void thenUserIsRedirectedToProductsPage() {
        Assert.assertTrue(productsPage.isPageLoaded());
    }

    @Then("^an error message \"([^\"]*)\" should be displayed$")
    public void thenErrorMessageIsDisplayed(String errorMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(actualMessage);
        Assert.assertTrue(actualMessage.contains(errorMessage));
    }

    @When("^the user leaves the username field empty$")
    public void whenUserLeavesUsernameEmpty() {
        loginPage.enterUsername("");
    }

    @When("^the user leaves the password field empty$")
    public void whenUserLeavesPasswordEmpty() {
        loginPage.enterPassword("");
    }

    @When("^the user clicks on the menu icon$")
    public void whenUserClicksMenuIcon() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @When("^the user selects the \"([^\"]*)\" option$")
    public void whenUserSelectsLogoutOption(String option) {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("^the user should be redirected to the login page$")
    public void thenUserIsRedirectedToLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
