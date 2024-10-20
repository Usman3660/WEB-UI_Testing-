package selenium_test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class ProductsPage {
    private WebDriver driver;
    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartLink = By.id("shopping_cart_container");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time
        try {
            // Using XPath to find the cart link more generically
            WebElement cartLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'shopping_cart_link')]")));
            cartLinkElement.click(); // Click the cart link
            System.out.println("Clicked on the cart link successfully.");
        } catch (TimeoutException e) {
            System.out.println("Cart link was not clickable: " + e.getMessage());
        }
    }



    public String getCartCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time
        try {
            WebElement cartBadgeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
            return cartBadgeElement.getText(); // Get text directly from the WebElement
        } catch (TimeoutException e) {
            System.out.println("Cart badge was not visible: " + e.getMessage());
            return "0"; // Return "0" or a suitable default value if the badge is not found
        }
    }


    public void sortProducts(String sortOption) {
        WebElement sortDropdownElement = driver.findElement(sortDropdown);
        sortDropdownElement.click(); // Open dropdown
        sortDropdownElement.sendKeys(sortOption); // Select the sort option
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement product : driver.findElements(productNames)) {
            names.add(product.getText());
        }
        return names;
    }
}
