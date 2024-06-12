package com.delhivery;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import delhivery_Page.Delhivery_Page;

public class Baseclass {
    public static AndroidDriver driver;
    public AppiumDriverLocalService service;
    public Delhivery_Page delhiveryPage;

    @BeforeSuite
    public void startServer() {
        File f = new File("C:\\Users\\USER\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
        service = new AppiumServiceBuilder()
                .withAppiumJS(f)
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(100))
                .build();
        service.start();
    }

    @BeforeClass
    public void setup() {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("automationName", "uiautomator2");
        dc.setCapability("platformName", "android");
        dc.setCapability("deviceName", "POCO M4 Pro");
        dc.setCapability("UDID", "8HKZLB5PZTV44XYX");
        dc.setCapability("appPackage", "com.delhiveryConsigneeApp");
        dc.setCapability("appActivity", "com.delhiveryConsigneeApp.MainActivity");

        driver = new AndroidDriver(service.getUrl(), dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        delhiveryPage = new Delhivery_Page(driver);
    }

    @AfterSuite
    public void stopService() {
        service.stop();
    }
}
