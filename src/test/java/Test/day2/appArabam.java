package Test.day2;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class appArabam {
    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); // eger kullanmis oldugumuz cihazin android
        // surumu 6 yada 6 dan buyukse UiAutomator2 kullanmamiz gerekiyor, 6 dan kucukse UiAutomator kullanmamiz gerekiyor
        //capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\toshiba\\IdeaProjects\\Appium\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");// uygulamayi cihaza yuklemek icin kullnailiyor
        capabilities.setCapability("appPackage","com.dogan.arabam");
       //appPackege bir uygulamamnin kimlik bilgisidir.biz bu capability sayesinde hangi uygulama uzerinde calısacagimizi
        // test oncesinde belirtebiliriz
        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        //appActivity uzerinde calisacak oldugumuz uygulamanin hangi sayfadan baslayacagimizi
        //belirtir. yani bu Sayade istedigimiz pencereden uygulamayi baslatabiliriz
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void arabamAppTest() throws InterruptedException {
        //driver.activateApp("com.dogan.arabam");  BeforeTestte yazildigi icin artik burada yazmaya gerek yoktur
        //istenen uygulamanin yuklendigi test edilir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        //uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='Alırken, satarken kullanırken']").isDisplayed());
        //alt menuden ilan ara butonuna basilir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();
        //kategori olarak otomobil secilir
        driver.findElementByXPath("//*[@text='Otomobil']").click();
        //arac olarak Volkwagen secilir
        Thread.sleep(1000);
        TouchAction action=new TouchAction<>(driver);
        action.press(PointOption.point(700,2750)) // press kismi ekranda tiklama yapacagimiz ilk nokta
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))) // baslangic ile bitis noktasi arasindaki gecen zaman
                //eger wait suresi uzun olursa gidilen yol azalir. sura kisalirsa daha fazla yol alir
                .moveTo(PointOption.point(500,150))// press kismindan elmizi biraktigimiz yer
                .release().perform();//parmagimizi birakma eylemi ve perform ile isi yap eylemi
        action.press(PointOption.point(500,2100))
                        .release().perform();
        //driver.findElementByXPath("//*[@text='Volkswagen']").click();  bu locate de is gorur
        //model olarak passat secilir
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='Passat']").click();
        //1.4 TSI BlueMotion secilir
        Thread.sleep(500);
        driver.findElementByXPath("//*[@text='1.4 TSi']").click();
        //paket secimi yapilir
        driver.findElementByXPath("//*[@text='Exclusive']").click();
        //ucuzdan pahaliya sirelama yapilarak filtreleme yapilir
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Sıralama']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();
        //gelen en ucuz aracin 500.000tlden buyuk oldugu dogrulanir
        String aracinFiyati= driver.findElementByXPath("//*[@resource-id='com.dogan.arabam:id/tvPrice']").getText();
        System.out.println(aracinFiyati);

        aracinFiyati=aracinFiyati.replaceAll("\\D","");

        Assert.assertTrue(Integer.parseInt(aracinFiyati)>=500000);




    }

}
