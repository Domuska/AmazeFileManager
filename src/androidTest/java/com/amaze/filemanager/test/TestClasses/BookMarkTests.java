package com.amaze.filemanager.test.TestClasses;


import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BookMarkTests extends BaseUIAutomatorTest{

    private String generalTestFolderName = TestDataSource.generalTestFolderName;

    @Test
    public void testBookmarking() throws Exception{

        //click on the overflow button with custom viewAction
        Utils.addFileToBookMarks(device, generalTestFolderName);

        //assert the bookmark is visible
        Utils.openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        assertTrue("Bookmark " + generalTestFolderName + " is not visible",
                navDrawer.scrollTextIntoView(generalTestFolderName));

        //remove the bookmark
        //device.findObject(By.text(generalTestFolderName)).longClick();
        Utils.myLongClick(device, generalTestFolderName);
        device.wait(Until.findObject(
                By.res("com.amaze.filemanager:id/buttonDefaultNegative")),
                GENERAL_TIMEOUT)
            .click();

        //assert it is no longer visible, use custom withAdaptedData (see Utils)
        assertFalse("Bookmark " + generalTestFolderName + " should be gone",
                navDrawer.scrollTextIntoView(generalTestFolderName));
    }
}
