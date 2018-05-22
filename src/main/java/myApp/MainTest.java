package myApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.HideKeyboardStrategy;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainTest {


    public AppiumDriver driver;
    public String hostURL = "http://localhost:4723/wd/hub";

    @Test
    @Parameters({"deviceId", "deviceName", "platformName", "version", "testType"})
    public void setUp(String deviceId, String deviceName, String platformName, String version, String testType) throws Exception{
        URL url;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability("udid", deviceId);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.VERSION, version);
        switch (testType.toUpperCase()){
            case "APP":
                capabilities.setCapability("appPackage", "<Package Name>");
                capabilities.setCapability("appActivity", "<Main Activity Name>");
                capabilities.setCapability(MobileCapabilityType.APP, "<path to APK>");
                url = new URL(hostURL);
                driver = new AndroidDriver(url, capabilities);
                testNativeApp();
                break;
            case "WEB":
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                capabilities.setCapability("noReset", true);
                url = new URL(hostURL);
                driver = new AndroidDriver(url, capabilities);
                testMobileWebApp();
                break;
            default:
                throw new Exception("Test type not valid");
        }
    }

    public void testNativeApp() throws Exception {

    }

    public void testMobileWebApp() throws Exception {

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
