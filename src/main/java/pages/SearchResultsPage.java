package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
//    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".ut2-gl__name a")
    List<WebElement> searchResultsProduct;

    @FindBy(id = "elm_7")
    WebElement lastname;


    public List<String> productsTitles() {
        return searchResultsProduct.stream()
                .map(webElement -> webElement.getAttribute("title")).toList();
    }

}
