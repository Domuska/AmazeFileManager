package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class EditBookmarksTests extends BaseAppiumTest{

    private String generalTestFoldername = TestDataSource.generalTestFolderName;
    private String newTestFolderName = TestDataSource.newTestFolderName;

    @After
    public void tearDown(){
        //remove the bookmarked file
        WebElement newBookMark = Utils.findElementByName(driver, newTestFolderName);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(newBookMark, 2000).release().perform();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultNegative").click();
    }

    @Before
    public void setUp(){
        Utils.openDrawer(driver);
        Utils.findElementByName(driver, storageText).click();
    }

    @Test
    public void editBookmarkTest(){

        Utils.addFileToBookMarks(driver, generalTestFoldername);
        Utils.openDrawer(driver);

        //rename the test folder bookmark
        WebElement folder = Utils.searchInVisibleListWithName(driver, generalTestFoldername);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(folder, 2000).release().perform();

        driver.findElementById("com.amaze.filemanager:id/editText4").clear();
        driver.findElementById("com.amaze.filemanager:id/editText4").sendKeys(newTestFolderName);
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

        //assert new name is visible
        WebElement drawer = driver.findElementById("com.amaze.filemanager:id/left_drawer");
        if(drawer.findElements(By.xpath("//*[@text='" + newTestFolderName +"']")).isEmpty()){
            fail("The bookmark named " + newTestFolderName  + " should be visible");
        }
    }
}
