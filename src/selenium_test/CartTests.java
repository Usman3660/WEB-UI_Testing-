package selenium_test;

import static org.junit.Assert.assertEquals; // Importing assertion library

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class CartTests extends BaseTest {

    // This method handles logging in and returns the ProductsPage
    private ProductsPage loginAndGetProductsPage() {
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        return new ProductsPage(driver);
    }

    public void verifyCartPersistence() {
        setup();
        
        // Log in and add item to cart
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addItemToCart();

        // Log out and back in
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        driver.get("https://www.saucedemo.com/");
        
        // Create a new LoginPage instance for re-login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Go to cart after re-login
        CartPage cartPage = new CartPage(driver);
        productsPage.goToCart(); // Navigate to the cart page

        // Verify cart persistence using getCartCount from CartPage
        String cartCount = cartPage.getCartCount();
        assertEquals("1", cartCount); // Assert that cart count is 1
        System.out.println("Cart persistence verified: Item remains in cart.");

        teardown();
    }

    public void addItemToCart() {
        setup();
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addItemToCart();
        System.out.println("Item added to cart successfully.");
        teardown();
    }

    public void removeItemFromCart() {
        setup();
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addItemToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItemFromCart();
        
        // Assert that the cart is empty using getCartCount from CartPage
        String cartCount = cartPage.getCartCount(); // Use CartPage's method to get cart count
        assertEquals("0", cartCount); // Assert that cart count is 0
        System.out.println("Item removed from cart successfully.");
        teardown();
    }

    public void checkoutProcess() {
        setup();
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addItemToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);

        // Verify the cart count before proceeding to checkout
        String cartCount = cartPage.getCartCount();
        System.out.println("Current cart count: " + cartCount);
        
        // Check if the cart is not empty before attempting checkout
        if (!cartCount.equals("0")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
            
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.completeCheckout("ABDULLAH", "CHEEMA", "12345");
            
            // Assert checkout confirmation message
            String confirmationText = checkoutPage.getConfirmationText();
            assertEquals("Thank you for your order!", confirmationText); // Ensure this matches your app's confirmation message
            System.out.println("Checkout completed: " + confirmationText);
        } else {
            System.out.println("Cart is empty, cannot proceed to checkout.");
        }
        
        teardown();
    }

}
