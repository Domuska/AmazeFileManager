package com.amaze.filemanager.test;

import android.widget.ImageView;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import static junit.framework.Assert.assertTrue;


public class testTest {

    ImageView view;

    AppiumDriver driver;

    @Before
    public void setUp()throws  Exception
    {
        //service.start();
        //reader.readFile();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5x 1");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability("platformVersion", "6.0.1");
        cap.setCapability("appPackage", "com.amaze.filemanager");
//        cap.setCapability("appActivity", );
//        cap.setCapability(MobileCapabilityType.APP, "c://apk//sample.apk");
        cap.setCapability
                ("app", "C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);


    }

    @Test
    public void testcase1() throws  Exception
    {
//        driver.findElement(By.id(""));
        driver.findElement(By.xpath("//*[@text='Alarms']")).click();
//        driver.findElementByID("Example").click();
//        assertTrue(driver.findElementByID("Example").isDisplayed));
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
