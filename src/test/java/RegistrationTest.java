import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Objects;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    HomePage homePage;
    RegistrationPage registrationPage;


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
        registrationPage = new RegistrationPage(driver);
    }

    @Parameters({"email", "password"})
    @Test(priority = 0)
    public void registerWithValidCredentials(String email, String password) {
        // Click on account icon, then register button and navigate to Registration Page
        homePage.navigateToRegisterPage();
        // Enter Register data
        registrationPage.enterRegisterData("Mawadda", "Abdelhady", "+20102222222",
                email, password);
        // Assert tha account has been Created
        var text = registrationPage.notificationMessage();
        assertTrue(text.contains("The account has been created successfully."),
                "The email is already exist");
    }

    @Test(priority = 1)
    public void registerWithInvalidEmailFormat() {
        // Click on account icon, then register button and navigate to Registration Page
        homePage.navigateToRegisterPage();
        // Enter Register data
        registrationPage.enterRegisterData("Mawadda", "Abdelhady", "+20102222222",
                "test.com", "test12345678");
        // Assert tha email format is invalid
        var text = registrationPage.errorMessage();
        assertTrue(text.contains("The email address in the Email field is invalid."),
                "The email input accept invalid format");
    }

    @Test(priority = 1)
    public void registerWithEmailAlreadyUsed() {
        // Click on account icon, then register button and navigate to Registration Page
        homePage.navigateToRegisterPage();
        // Enter Register data
        registrationPage.enterRegisterData("Mawadda", "Abdelhady", "+20102222222",
                "test@test.com", "test12345678");
        var text = registrationPage.notificationMessage();
        // Assert tha email already exists
        assertTrue(text.contains("The username or email you have chosen already exists. Please try another one."),
                "The email is not exist before");
    }

    @AfterMethod
    public void tireDown() throws InterruptedException {
        // Close Browser
        Thread.sleep(1000);
        driver.quit();
    }

}
