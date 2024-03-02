import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegistrationPage;
import pages.SearchResultsPage;

import java.lang.System;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest {
    WebDriver driver;

    HomePage homePage;
    SearchResultsPage searchResultsPage;


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
        searchResultsPage = new SearchResultsPage(driver);

    }

    @Test
    public void searchesForSpecificLaptop() {
        String laptop = "HP ProBook 440";
        boolean search = false;
        // Enter data in search input
        homePage.searchByText(laptop);
        // Get name of product Results
        List<String> products = searchResultsPage.productsTitles();
        // Assert the product appear successfully
        for (String product : products) {
            if (product.contains(laptop)) {
                search = true;
                break;
            }
        }
        assertTrue(search, "Search Functionality dose not word probably");
    }

    @Test(dataProvider = "Non-exiting Products")
    public void searchesForNonExistentProduct(String ProductName) {
        // Enter data in search input
        homePage.searchByText(ProductName);
        // Get name of product Results
        List<String> products = searchResultsPage.productsTitles();
        // Assert the results is empty
        assertEquals(products.size(), 0, "Search Functionality dose not word probably");
    }

    @DataProvider(name = "Non-exiting Products")
    public Object[] testData() {
        return new Object[]{
                "Mobile phone", "Hello"
        };
    }

    @AfterMethod
    public void tireDown() throws InterruptedException {
        // Close browser
        Thread.sleep(1000);
        driver.quit();
    }
}
