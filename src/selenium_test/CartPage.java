package selenium_test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private By removeButton = By.cssSelector(".cart_item button"); // Selects any button within cart_item
    private By cartBadge = By.className("shopping_cart_badge"); // Updated to match the actual cart badge

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeItemFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the remove button to be visible before clicking
        WebElement removeButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
        removeButtonElement.click();
    }

    // Method to get cart count
 // Method to get cart count
    public String getCartCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for the cart badge element
            WebElement cartBadgeElement = wait.until(ExpectedConditions.presenceOfElementLocated(cartBadge));
            
            // If the badge is found but has no text, return "0"
            String badgeText = cartBadgeElement.getText();
            return badgeText.isEmpty() ? "0" : badgeText; // Return "0" if empty
        } catch (org.openqa.selenium.TimeoutException e) {
            // If the badge is not found, return "0" indicating an empty cart
            System.out.println("Cart badge was not visible or does not exist: " + e.getMessage());
            return "0";
        }
    }

}
