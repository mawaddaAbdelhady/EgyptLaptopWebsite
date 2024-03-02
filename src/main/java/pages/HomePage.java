package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
//    WebDriver driver;

    public HomePage(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_input")
    WebElement searchInput;
    @FindBy(id = "sw_dropdown_624")
    WebElement account;
    @FindBy(css = "#dropdown_624 a[href=\"https://egyptlaptop.com/profiles-add\"]")
    WebElement registerButton;
    @FindBy(css = "#dropdown_624 a[href=\"https://egyptlaptop.com/login/?return_url=index.php\"]")
    WebElement signInButton;
    @FindBy(css = "form[name=\"product_form_530009650\"]")
    public WebElement newArrivalLaptop;
    @FindBy(css = "form[name=\"product_form_530009648\"]")
    public WebElement newArrivalSeething;
    @FindBy(id = "cart_icon_4700")
    public WebElement cart;

    @FindBy(css = "span[title=\"Remove\"]")
    public WebElement removeCartButton;
    @FindBy(id = "login_popup4699")
    public WebElement emailSingIn;
    @FindBy(id = "psw_popup4699")
    public WebElement passwordSingIn;

    @FindBy(css = "form[name=\"popup4699_l_form\"] button[type=\"submit\"]")
    public WebElement signInButtonForm;
    @FindBy(className = "cm-notification-content")
    WebElement notificationMessage;
    @FindBy(css = ".grid-list__load-more .ty-column6")
    List<WebElement> productsList;

    public void enterDataToLogin(String email, String password) {
        emailSingIn.sendKeys(email);
        passwordSingIn.sendKeys(password);
        signInButtonForm.click();
    }


    public void clickOnProduct1() {
        productsList.get(0).click();
    }

    public void clickOnProduct2() {
        productsList.get(3).click();
    }

    public void navigateToRegisterPage() {
        account.click();
        registerButton.click();
    }

    public void navigateToSignInForm() {
        account.click();
        signInButton.click();
    }

    public void searchByText(String text) {
        searchInput.sendKeys(text);
        searchInput.submit();

    }


}
