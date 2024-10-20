package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.time.Duration; // Correct Duration import
import java.util.List;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_test.BaseTest; // Import the base class for setup and teardown
import selenium_test.CartPage;
import selenium_test.CheckoutPage;
import selenium_test.LoginPage;
import selenium_test.ProductsPage;

public class UISteps extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        setup(); // Call setup from BaseTest
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @When("the user adds an item to the cart")
    public void the_user_adds_an_item_to_the_cart() {
        productsPage = new ProductsPage(driver);
        productsPage.addItemToCart();
    }

    @Then("the cart should contain {int} item")
    public void the_cart_should_contain_item(int expectedCount) {
        String cartCount = productsPage.getCartCount();
        assertEquals("Cart count does not match", String.valueOf(expectedCount), cartCount);
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @When("the user logs back in with username {string} and password {string}")
    public void the_user_logs_back_in_with_username_and_password(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        loginPage.login(username, password);
    }

    @Then("the cart should still contain {int} item")
    public void the_cart_should_still_contain_item(int expectedCount) {
        String cartCount = productsPage.getCartCount();
        assertEquals("Cart count does not match after logging back in", String.valueOf(expectedCount), cartCount);
    }

    @When("the user goes to the cart")
    public void the_user_goes_to_the_cart() {
        productsPage.goToCart();
    }

    @When("the user removes the item from the cart")
    public void the_user_removes_the_item_from_the_cart() {
        cartPage = new CartPage(driver);
        cartPage.removeItemFromCart();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        String cartCount = productsPage.getCartCount();
        assertEquals("Cart should be empty", "0", cartCount);
    }

    @When("the user initiates the checkout process")
    public void the_user_initiates_the_checkout_process() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Updated to use java.time.Duration
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
    }

    @When("the user completes the checkout with first name {string}, last name {string}, and postal code {string}")
    public void the_user_completes_the_checkout_with(String firstName, String lastName, String postalCode) {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.completeCheckout(firstName, lastName, postalCode);
    }

    @Then("the checkout should be confirmed with a success message")
    public void the_checkout_should_be_confirmed_with_a_success_message() {
        String confirmationText = checkoutPage.getConfirmationText();
        assertEquals("Checkout was not successful", "Thank you for your order!", confirmationText);
    }

    @Then("the user should see the list of product names")
    public void the_user_should_see_the_list_of_product_names() {
        List<String> productNames = productsPage.getProductNames();
        assertEquals("Product list is empty", false, productNames.isEmpty());
    }

    @When("the user sorts the products by {string}")
    public void the_user_sorts_the_products_by(String sortOption) {
        productsPage.sortProducts(sortOption);
    }

    @Then("the products should be sorted accordingly")
    public void the_products_should_be_sorted_accordingly() {
        // Implementation to verify that products are sorted correctly can go here
    }

    @After
    public void tearDown() {
        teardown(); // Call teardown from BaseTest
    }
}
