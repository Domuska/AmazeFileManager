package com.amaze.filemanager.test.TestClasses;

import android.graphics.Rect;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EditBookmarkTests extends BaseUIAutomatorTest{

    private String generalTestFolderName = TestDataSource.generalTestFolderName;
    private String newTestFolderName = TestDataSource.newTestFolderName;

    private UiScrollable navDrawer;

    @Before
    public void setUp(){
        Utils.openDrawer(device);
        device.findObject(By.text(storageText)).click();
    }

    @After
    public void tearDown() throws Exception{
        //remove the bookmark
        navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));
        navDrawer.scrollTextIntoView(newTestFolderName);
        //device.findObject(By.text(newTestFolderName)).longClick();
        Utils.myLongClick(device, newTestFolderName);
        device.wait(Until.findObject(By.res("com.amaze.filemanager:id/buttonDefaultNegative")),
                GENERAL_TIMEOUT).click();
    }

    @Test
    public void testEditBookmark() throws Exception{

        Utils.addFileToBookMarks(device, generalTestFolderName);
        Utils.openDrawer(device);

        //rename the bookmark
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        navDrawer.scrollTextIntoView(generalTestFolderName);
        device.findObject(By.text(generalTestFolderName)).longClick();

        UiObject2 textField =
                device.wait(Until.findObject(By.res("com.amaze.filemanager:id/editText4")),
                GENERAL_TIMEOUT);
        textField.clear();
        textField.setText(newTestFolderName);

        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();

        //assert new name is visible
        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/menu_drawer")), GENERAL_TIMEOUT);
        assertTrue("Bookmark with name " + newTestFolderName + " should be visible",
                device.findObject(new UiSelector().text(newTestFolderName)).exists());
    }

}