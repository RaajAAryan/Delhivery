package Script;

import org.testng.annotations.Test;
import com.delhivery.Baseclass;

public class OTP_Test extends Baseclass {
    @Test
    public void testContinueBtnAndEnterMobileNumber() {
    	delhiveryPage.clickAllowNotification();
        delhiveryPage.clickContinueBtn();
        delhiveryPage.enterMobileNumber("9620058143");
        delhiveryPage.clickOnOTP();
        delhiveryPage.openNotifications();

        String otp = delhiveryPage.getOTPFromNotification();
        if (otp != null) {
        System.out.println("OTP received: " + otp);
        driver.navigate().back(); 
        delhiveryPage.enterOTP(otp);
        delhiveryPage.clickOnOTP();
        }
        else {
        System.out.println("OTP not received.");
        	}

        driver.quit();
}
}