package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class BookMarkTests extends BaseAppiumTest{

    private String generalTestFoldername = TestDataSource.generalTestFolderName;
    private String storageText = "Storage";

    @Before
    public void setUp(){
        Utils.openDrawer(driver);
        Utils.findElementByName(driver, storageText).click();
    }

    @Test
    public void testBookMarking(){
        try {
            Utils.addFileToBookMarks(driver, generalTestFoldername);

            //assert the bookmark is visible
            Utils.openDrawer(driver);
            Utils.searchInVisibleListWithName(driver, generalTestFoldername);

            //remove the bookmark
            WebElement bookmark =
                    Utils.searchInVisibleListWithName(driver, generalTestFoldername);
            TouchAction longPress = new TouchAction(driver);
            longPress.longPress(bookmark, 2000).release().perform();
            driver.findElementById("com.amaze.filemanager:id/buttonDefaultNegative").click();

            //assert it is no longer visible in the nav drawer, we can assert like this since after deleting
            //the bookmark the navigation drawer stays at the same position
            WebElement drawer = driver.findElementById("com.amaze.filemanager:id/left_drawer");
            if (!drawer.findElements(By.xpath("//*[@text='" + generalTestFoldername + "']")).isEmpty()) {
                fail("The bookmark with name " + generalTestFoldername + " should not be visible any more");
            }
        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }
}
