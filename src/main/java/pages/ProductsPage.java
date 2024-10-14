package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    // Locator for the products page (Assumed element after login)
    By pageTitle = By.cssSelector(".title");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to verify that the user has been redirected to the products page
    public boolean isPageLoaded() {
        return driver.findElement(pageTitle).isDisplayed();
    }
}
