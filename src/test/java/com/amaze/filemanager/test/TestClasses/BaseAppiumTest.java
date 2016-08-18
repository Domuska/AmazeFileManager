package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.database.TabHandler;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


// credit to http://qaautomated.blogspot.fi/2016/01/setting-up-appium-with-android-studio.html
// for skeleton of this file and instructions on how to setup Appium with Android Studio
public class BaseAppiumTest {

    AndroidDriver<MobileElement> driver;
    private final int KEYCODE_HOME = 3;
    protected WebDriverWait stareAtPixies;

    protected String storageText = "Storage";

    private final String TEST_RESULT_FOLDER = "C:\\Users\\Tomi\\testAutomation\\appium_screenshot_folder";

    @Before
    final public void setUpBaseAppiumTest() throws Exception{

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5x 1");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability("appPackage", "com.amaze.filemanager");
//        cap.setCapability(MobileCapabilityType.APP,
//                "C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");
        cap.setCapability(MobileCapabilityType.APP,
                "C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\AmazeFileManager-play-debug.apk");

        // https://discuss.appium.io/t/android-m-and-permissions/5760/13
        //noReset so the app does not get reinstalled ever so we don't have to deal with permissions
        cap.setCapability("noReset", false);
//        cap.setCapability(MobileCapabilityType.FULL_RESET, true);

        //need to do this so that we can install the .apk with all permissions given
        cap.setCapability("autoLaunch", false);
        Runtime.getRuntime()
                .exec("adb install -g C:\\Users\\Tomi\\Projects\\amazeFileManager\\AmazeFileManager\\build\\outputs\\apk\\AmazeFileManager-play-debug.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        stareAtPixies = new WebDriverWait(driver, 10);
        driver.pressKeyCode(KEYCODE_HOME);
        driver.findElementByAccessibilityId("Apps").click();

        stareAtPixies.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@text='Amaze']"))).click();
//        stareAtPixies.until(ExpectedConditions.visibilityOfElementLocated(
//                By.id("com.android.packageinstaller:id/permission_allow_button")))
//                .click();
        //https://discuss.appium.io/t/giving-app-permissions-for-marshmallow-at-runtime-in-appium/8951/5
    }

    @After
    final public void tearDownBaseAppiumTest() {
        driver.pressKeyCode(KEYCODE_HOME);
        driver.quit();
    }

    //credit to bitbar & testDroid for this
    // https://github.com/bitbar/testdroid-samples/tree/master/appium/sample-scripts/java/src/test/java/com/testdroid/appium
    protected void takeScreenshot(String screenshotName) {

//        String fullFileName = System.getProperty("user.dir") + "/Screenshots/" + "_" + screenshotName + ".png";
        String fullFileName = TEST_RESULT_FOLDER + "\\" + screenshotName + ".png";
        screenshot(fullFileName);
    }

    private File screenshot(String name) {
        System.out.println("Taking screenshot...");
        File scrFile = driver.getScreenshotAs(OutputType.FILE);

        try {

            File testScreenshot = new File(name);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
