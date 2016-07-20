package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;

public class BaseTestClass extends BaseAppiumTest{

    String amazeTestFolderName = TestDataSource.amazeTestFolderName;

    @Before
    final public void setUpBaseTestClass(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@text='Alarms']"))
        );

        //go to the testing folder in root of sdcard (hopefully)
        Utils.navigateToTestingFolder(driver);

        //create the testing folder
        Utils.createFolderWithName(driver, amazeTestFolderName);
        driver.findElementByName(amazeTestFolderName).click();
    }

    @After
    final public void tearDownBaseTestClass(){

        // move back from the folder we're in so we can push the home
        // button or alternatively to close nav drawer
        driver.navigate().back();

        //delete the testing folder
        try {
            Runtime.getRuntime().exec("adb shell rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
