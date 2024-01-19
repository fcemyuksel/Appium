package Test.apkYukleme;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class apk {
    AndroidDriver<AndroidElement> driver;
    @Test
    public void apkYukleme() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); // eger kullanmis oldugumuz cihazin android
        // surumu 6 yada 6 dan buyukse UiAutomator2 kullanmamiz gerekiyor, 6 dan kucukse UiAutomator kullanmamiz gerekiyor
        //capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\toshiba\\IdeaProjects\\Appium\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");// uygulamayi cihaza yuklemek icin kullnailiyor
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\toshiba\\IdeaProjects\\Appium\\Apps\\Kiwi.com - Book Cheap Flights_2023.14.0_Apkpure (1).apk");// uygulamayi cihaza yuklemek icin kullnailiyor



        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
