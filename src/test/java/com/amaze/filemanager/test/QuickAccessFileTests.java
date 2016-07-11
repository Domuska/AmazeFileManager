package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.openqa.selenium.By;
import org.slf4j.helpers.Util;

import static junit.framework.Assert.fail;

public class QuickAccessFileTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String quickAccessText = "Quick Access";

    @Test
    public void testOpenFileCheckRecents(){

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
        if(driver.findElements(By.name(fileName)).isEmpty()){
            fail("The element named " + fileName  + " should be visible");
        }

    }

    // possibly a bit brittle way to do it, we are presuming here that a popup comes up
    // and asks which text editor to use to open the file - pick the first one that is visible
    private void openTestFile() {
        driver.findElementByName(fileName).click();
        driver.findElementById("android:id/button_once").click();
        driver.navigate().back();
    }

}
