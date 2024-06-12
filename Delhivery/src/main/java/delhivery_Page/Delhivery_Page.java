package delhivery_Page;

import java.util.List;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

public class Delhivery_Page {
    private AndroidDriver driver;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement allowNotification;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Continue\"]")
    private WebElement continueBtn;

    @FindBy(xpath = "//android.widget.EditText[@text=\"Enter mobile number\"]")
    private WebElement enterMobileNumber;

    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
    private WebElement OTP;

    public Delhivery_Page(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getAllowNotification() {
        return allowNotification;
    }

    public WebElement getContinueBtn() {
        return continueBtn;
    }

    public WebElement getEnterMobileNumber() {
        return enterMobileNumber;
    }

    public WebElement getOTP() {
        return OTP;
    }

    public void clickAllowNotification() {
        allowNotification.click();
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }

    public void enterMobileNumber(String mobileNumber) {
        enterMobileNumber.sendKeys(mobileNumber);
    }

    public void clickOnOTP() {
        OTP.click();
    }

    public void enterOTP(String otp) {
        OTP.sendKeys(otp);
    }

    public void setClipboard(String text) {
        Map<String, Object> args = new HashMap<>();
        args.put("content", text);
        driver.executeScript("mobile: setClipboard", args);
    }

    public String getClipboard() {
        return (String) driver.executeScript("mobile: getClipboard");
    }

    public void openNotifications() {
        driver.openNotifications();
    }

    public String getOTPFromNotification() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        List<WebElement> notifications = driver.findElements(By.className("android.widget.TextView"));
        for (WebElement notification : notifications) {
            String notificationText = notification.getText();
            if (notificationText.matches(".*\\d{6}.*")) {  // Assuming OTP is a 6-digit number
                return notificationText.replaceAll("[^\\d]", "");
            }
        }
        return null;
    }
}
