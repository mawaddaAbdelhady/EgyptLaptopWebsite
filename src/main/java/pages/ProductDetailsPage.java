package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailsPage {
//    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".ut2-pb__button button[type=\"submit\"]")
    WebElement addCartButton;
    @FindBy(css = ".ut2-pb__title h1 bdi")
    WebElement productName;
    @FindBy(id = "discounted_price_9650")
    WebElement productPrice;

    @FindBy(className = "ty-product-notification__product-name")
    WebElement productNameInSuccessfullyAdded;
    @FindBy(linkText = "Continue shopping")
    WebElement continueShoppingButton;
    @FindBy(linkText = "Checkout")
    WebElement checkoutButton;
    @FindBy(css = ".ty-cart-items__list-item-desc a")
    public List<WebElement> productsNameCartItems;

    @FindBy(className = "cm-notification-content")
    WebElement notificationMessage;

    public String notificationMessage() {
        return notificationMessage.getText();
    }

    public void clickAddCart() {
        addCartButton.click();
    }
    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public String getProductName() {
        return productName.getText();
    }

    public List<String> getProductsNameCartItems() {
        return productsNameCartItems.stream()
                .map(WebElement::getText).toList();
    }

}
