package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;

public class BaseTestClass extends BaseAppiumTest{

    String amazeTestFolderName = TestDataSource.amazeTestFolderName;

    @Before
    final public void setUpBaseTestClass(){
        //go to the testing folder in root of sdcard (hopefully)
        Utils.navigateToTestingFolder(driver);

        //create the testing folder
        Utils.createFolderWithName(driver, amazeTestFolderName);
        driver.findElementByName(amazeTestFolderName).click();
    }

    @After
    final public void tearDownBaseTestClass(){

        // move back from the folder we're in so we can push the home
        // button or alternatively close nav drawer
        driver.navigate().back();
        Utils.navigateToTestingFolder(driver);

        //delete the testing folder
        WebElement folder = driver.findElementByName(amazeTestFolderName);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(folder, 2000).release().perform();
        driver.findElementById("com.amaze.filemanager:id/delete").click();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();
    }
}
