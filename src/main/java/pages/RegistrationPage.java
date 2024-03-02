package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
//    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "elm_6")
    WebElement firstname;
    @FindBy(id = "elm_7")
    WebElement lastname;
    @FindBy(id = "elm_9")
    WebElement phone;
    @FindBy(id = "email")
    WebElement email;
    @FindBy(id = "password1")
    WebElement password;
    @FindBy(id = "password2")
    WebElement confirmPassword;
    @FindBy(name = "dispatch[profiles.update]")
    WebElement registerButton;
    @FindBy(id = "email_error_message")
    WebElement emailError;
    @FindBy(className = "cm-notification-content")
    WebElement notificationMessage;


    public void enterRegisterData(String firstname, String lastname, String phone, String email, String password) {
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.phone.sendKeys(phone);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
        registerButton.click();
    }

    public String errorMessage() {
        return emailError.getText();
    }
    public String notificationMessage() {
        return notificationMessage.getText();
    }

}
