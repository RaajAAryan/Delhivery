package Script;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OTPHardCoded {
@Test
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("automationName", "uiautomator2");
        dc.setCapability("platformName", "android");
        dc.setCapability("deviceName", "POCO M4 Pro");
        dc.setCapability("UDID", "8HKZLB5PZTV44XYX");
//        dc.setCapability("appPackage", "com.delhiveryConsigneeApp");
//        dc.setCapability("appActivity", "com.delhiveryConsigneeApp.MainActivity");
        URL u = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(u, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.activateApp("com.delhiveryConsigneeApp");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"))
              .click();
       driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Continue\"]")).click();
       driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Enter mobile number\"]"))
              .sendKeys("9620058143");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Get OTP\"]")).click();
        String otp = getOTPFromNotification(driver);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Enter OTP\"]")).sendKeys(otp);
    }
    private String getOTPFromNotification(AndroidDriver driver) {
        return driver.getClipboardText();
    }
}
