package com.amaze.filemanager.test.TestClasses;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.R;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class QuickAccessFileTests extends BaseTestClass{

    private String fileName, quickAccessText;

    @Before
    public void setUp(){
        fileName = TestDataSource.textFileName;
        quickAccessText = InstrumentationRegistry.getTargetContext().getString(R.string.quick);
    }

    @Test
    public void testOpenFileCheckRecents() throws Exception{
        Utils.createFileWithName(device, fileName);

        //open the file multiple times so it is added to "Quick Access"
        clickFileAndPushBack();
        clickFileAndPushBack();
        clickFileAndPushBack();
        clickFileAndPushBack();

        //go to quick access
        Utils.openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                quickAccessText).click();

        //assert file name is visible here
        assertTrue("File " + fileName + " should be visible in quick access",
                device.findObject(new UiSelector().text(fileName)).exists());
    }

    private void clickFileAndPushBack() throws Exception{
        Utils.waitForText(device, fileName).click();
        device.wait(Until.hasObject(By.res("android:id/button_once")), GENERAL_TIMEOUT);
        device.findObject(new UiSelector().text("Just once")).clickAndWaitForNewWindow();
        device.pressBack();
    }
}
