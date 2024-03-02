import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.time.Duration;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class RemovingItemsFromCartTest {
    HomePage homePage;
    WebDriver driver;
    ProductDetailsPage productDetailsPage;
    WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        // set Up Chrome
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else if (Objects.equals(browser, "FireFox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        // maximize Window
        driver.manage().window().maximize();

        // navigate to home page
        driver.get("https://egyptlaptop.com/");

        // Identify Page Objects
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
    }


    @Test
    public void userRemovingItemFromCart() {
        // Click to product 1
        homePage.clickOnProduct1();
        // click to add cart
        productDetailsPage.clickAddCart();
        // wait until successfully added to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ty-product-notification__product-name")));
        // click Continue Shopping Button
        productDetailsPage.clickContinueShoppingButton();
        // Click cart icon
        homePage.cart.click();
        // Hover to Product
        Actions action = new Actions(driver);
        action.moveToElement(productDetailsPage.productsNameCartItems.get(0)).perform();
        // Click remove Icon
        homePage.removeCartButton.click();
        // Wait until the product successfully removed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cm-notification-content")));
        // assert the product removed
        assertTrue(productDetailsPage.notificationMessage().contains("Product has been deleted successfully."),
                "The Removing from cart functionality not work probably");

    }


    @AfterMethod
    public void tireDown() throws InterruptedException {
        // Close Browser
        Thread.sleep(1000);
        driver.quit();
    }

}
