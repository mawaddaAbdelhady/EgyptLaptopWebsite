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
import pages.CheckOutPage;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.time.Duration;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class CompletingPurchasesTest {
    WebDriver driver;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;

    CheckOutPage checkOutPage;
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
        checkOutPage = new CheckOutPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Parameters({"email", "password"})
    @Test(priority = 1)
    public void loggedUserCompletePurchasesWithOutAddress(String email, String password) {
        // Click on account icon and then sign in button
        homePage.navigateToSignInForm();
        // Enter Account Data
        homePage.enterDataToLogin(email, password);
        // waiting for successfully log in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cm-notification-content")));
        // Click to product
        homePage.clickOnProduct1();
        // click to add cart
        productDetailsPage.clickAddCart();
        // waiting until the popup loaded and click checkout button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Checkout")));
        productDetailsPage.clickCheckoutButton();
        // click to accept Terms CheckBox
        checkOutPage.clickAcceptTermsCheckBox();
        // click to place Order Button
        checkOutPage.clickPlaceOrderButton();
        // Assert that the address error message will appear
        assertTrue(checkOutPage.addressErrorMessage.isEnabled(), "The Order Continue");

    }

    @Parameters({"email", "password"})
    @Test(priority = 2)
    public void loggedUserCompletePurchasesWithValidData(String email, String password) {
        // Click on account icon and then sign in button
        homePage.navigateToSignInForm();
        // Enter Account Data
        homePage.enterDataToLogin(email, password);
        // waiting for successfully log in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cm-notification-content")));
        // Click to product
        homePage.clickOnProduct1();
        // click to add cart
        productDetailsPage.clickAddCart();
        // waiting until the popup loaded and click checkout button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Checkout")));
        productDetailsPage.clickCheckoutButton();
        // Enter Address data
        checkOutPage.enterAddress("Cairo");
        // click to accept Terms CheckBox
        checkOutPage.clickAcceptTermsCheckBox();
        // click to place Order Button
        checkOutPage.clickPlaceOrderButton();
        // Wait Until successfully confirming Order
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cm-notification-content")));
        // Assert that the Order successfully Confirmed
        assertTrue(checkOutPage.getNotificationMessage()
                        .contains("successfully"),
                "The Order Failed to placed");
    }

    @AfterMethod
    public void tireDown() throws InterruptedException {
        // Close Browser
        Thread.sleep(1000);
        driver.quit();
    }
}
