package com.amaze.filemanager.test;

import com.amaze.filemanager.database.TabHandler;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


// credit to http://qaautomated.blogspot.fi/2016/01/setting-up-appium-with-android-studio.html
// for skeleton of this file and instructions on how to setup Appium with Android Studio
public class BaseAppiumTest {

    AndroidDriver driver;

    @Before
    final public void setUpBaseAppiumTest() throws Exception{

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5x 1");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability("appPackage", "com.amaze.filemanager");
        cap.setCapability(MobileCapabilityType.APP,
                "C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");

        // https://discuss.appium.io/t/android-m-and-permissions/5760/13
        //noReset so the app does not get reinstalled ever so we don't have to deal with permissions
//        cap.setCapability("noReset", false);
        cap.setCapability(MobileCapabilityType.FULL_RESET, false);

        //need to do this so that we can install the .apk with all permissions given
        cap.setCapability("autoLaunch", false);
//        Runtime.getRuntime()
//                .exec("adb install -g C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");


        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
//        driver.startActivity("com.amaze.filemanager", "activities.MainActivity",
//                "com.amaze.filemanager", "activities.MainActivity");
        driver.launchApp();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5000);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("Amaze")
        ));


//        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//*[@text='Amaze']"))
//        );
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//*[@text='Amaze']")
//        ));

        //https://discuss.appium.io/t/giving-app-permissions-for-marshmallow-at-runtime-in-appium/8951/5

    }

    @After
    final public void tearDownBaseAppiumTest() {
        driver.quit();
    }
}
