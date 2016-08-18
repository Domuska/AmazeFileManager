package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.openqa.selenium.By;

import static junit.framework.Assert.fail;

public class QuickAccessFileTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String quickAccessText = "Quick Access";

    @Test
    public void testOpenFileCheckRecents(){
        try {
            Utils.createFileWithName(driver, fileName);

            //open the test file a couple of times
            openTestFile();
            openTestFile();
            openTestFile();
            openTestFile();

            //navigate to quick access
            Utils.openDrawer(driver);
            Utils.searchInVisibleListWithName(driver, quickAccessText).click();

            //assert the test file is visible
            if (Utils.findElementsByName(driver, fileName).isEmpty()) {
                fail("The element named " + fileName + " should be visible");
            }
        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }

    // possibly a bit brittle way to do it, we are presuming here that a popup comes up
    // and asks which text editor to use to open the file - pick the first one that is visible
    private void openTestFile() {
        Utils.findElementByName(driver, fileName).click();
        driver.findElementById("android:id/button_once").click();
        driver.navigate().back();
    }

}
