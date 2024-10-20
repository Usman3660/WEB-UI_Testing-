package selenium_test;

public class UITest {
    public static void main(String[] args) {
        CartTests cartTests = new CartTests();
        
        // Run all tests
        cartTests.addItemToCart();
        cartTests.removeItemFromCart();
        cartTests.checkoutProcess();
        cartTests.verifyCartPersistence();
    }
}