import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class AddingItemsToTheCartTest {
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
    public void addLaptopToCart() {

        // Click to product
        homePage.clickOnProduct1();
        String productName = productDetailsPage.getProductName();

        // click to add cart
        productDetailsPage.clickAddCart();
        // waiting until the popup loaded and successfully added to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ty-product-notification__product-name")));
        // click on Continue Shopping Button
        productDetailsPage.clickContinueShoppingButton();
        // Assertion that product added successfully
        homePage.cart.click();
        List<String> cartItems = productDetailsPage.getProductsNameCartItems();
        assertTrue(cartItems.contains(productName), "The Add to cart not work probably");

    }


    @Test
    public void addMultipleItemsToCart() {
        // Click to product 1
        homePage.clickOnProduct1();
        // save the name of product
        String product1 = productDetailsPage.getProductName();
        List<String> addedItem = new ArrayList<>();
        addedItem.add(product1);
        // click to add cart
        productDetailsPage.clickAddCart();
        // wait until successfully added to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ty-product-notification__product-name")));
        // navigate to home page
        driver.get("https://egyptlaptop.com/");
        // Click to product 2
        homePage.clickOnProduct2();
        // save the name of product
        String product2 = productDetailsPage.getProductName();
        addedItem.add(product2);
        // click to add cart
        productDetailsPage.clickAddCart();
        // wait until successfully added to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ty-product-notification__product-name")));
        productDetailsPage.clickContinueShoppingButton();
        // Assertion that products added successfully by compare the name of products
        homePage.cart.click();
        List<String> cartItems = productDetailsPage.getProductsNameCartItems();

        assertTrue(cartItems.containsAll(addedItem), "The Add to cart not work probably");

    }

    @AfterMethod
    public void tireDown() throws InterruptedException {
        // Close Browser
        Thread.sleep(1000);
        driver.quit();
    }


}
