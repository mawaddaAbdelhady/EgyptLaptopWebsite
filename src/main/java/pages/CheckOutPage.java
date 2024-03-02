package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
//    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name=\"accept_terms\"]")
    WebElement acceptTermsCheckBox;

    @FindBy(id = "litecheckout_place_order")
    WebElement placeOrderButton;
    @FindBy(id = "litecheckout_s_address_error_message")
    public WebElement addressErrorMessage;

    @FindBy(id = "litecheckout_s_address")
    public WebElement addressInput;

    @FindBy(className = "cm-notification-content")
    WebElement notificationMessage;

    public void enterAddress(String address) {
        addressInput.sendKeys(address);
    }

    public void clickAcceptTermsCheckBox() {
        acceptTermsCheckBox.click();
    }

    public void clickPlaceOrderButton() {
        placeOrderButton.click();
    }

    public String getNotificationMessage() {

        return notificationMessage.getText();
    }


}
