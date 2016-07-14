package com.amaze.filemanager.test;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.R;

import com.amaze.filemanager.database.TabHandler;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SetHomeTest extends BaseUIAutomatorTest{

    private String generalTestFolderName,
            amazeTestFolderName, setAsHomeText,
            quickAccessText;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getTargetContext();
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;

        setAsHomeText = context.getString(R.string.setashome);
        quickAccessText = context.getString(R.string.quick);

        Utils.navigateToTestFolder(device);
        Utils.swipeDownInPathBar(device);
    }

    @After
    public final void tearDown(){

        //clear database and preferences to remove the set home
        PreferenceManager.
                getDefaultSharedPreferences(
                        InstrumentationRegistry.getContext())
                .edit().clear().commit();

        TabHandler.clearDatabase(InstrumentationRegistry.getContext());
    }

    @Test
    public void testSetHome() throws Exception{
        Utils.openOverflowMenu(device);

        //set this folder as home
        Utils.waitForText(device, setAsHomeText).click();
//        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();
        device.wait(Until.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")),
                GENERAL_TIMEOUT).click();

        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/fullpath")),
                GENERAL_TIMEOUT);

        //navigate to somewhere else
        Utils.openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

//        navDrawer.scrollTextIntoView(storageText);
        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                quickAccessText).click();

        //go back to home
        device.findObject(By.res("com.amaze.filemanager:id/home")).click();

        //assert that we're at .../Testing folder
        UiObject2 pathBar = device.wait(Until.findObject(By.res("com.amaze.filemanager:id/fullpath")),
                GENERAL_TIMEOUT);

        assertTrue("Path does not contain folder " + generalTestFolderName,
                pathBar.getText().endsWith(generalTestFolderName));
    }

}
