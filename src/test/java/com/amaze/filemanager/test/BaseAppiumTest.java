package com.amaze.filemanager.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


// credit to http://qaautomated.blogspot.fi/2016/01/setting-up-appium-with-android-studio.html
// for skeleton of this file and instructions on how to setup Appium with Android Studio
public class BaseAppiumTest {

//    AppiumDriver driver;
    AndroidDriver driver;

    @Before
    public void setUp()throws  Exception
    {
        //service.start();
        //reader.readFile();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5x 1");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        cap.setCapability("appPackage", "com.amaze.filemanager");
//        cap.setCapability("appActivity", );
//        cap.setCapability(MobileCapabilityType.APP, "c://apk//sample.apk");
        cap.setCapability(MobileCapabilityType.APP,
                "C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");

        cap.setCapability(MobileCapabilityType.FULL_RESET, false);



        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
