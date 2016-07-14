package com.amaze.filemanager.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NavigationTests extends BaseUIAutomatorTest{

    private String storageText, recentFilesText, videosText;
    private String gridViewText, listViewText;
    private String generalTestFolderName;

    @Before
    public void setUp(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        storageText = context.getString(R.string.storage);
        recentFilesText = context.getString(R.string.recent);
        videosText = context.getString(R.string.videos);

        gridViewText = context.getString(R.string.gridview);
        listViewText = context.getString(R.string.listview);
        generalTestFolderName = TestDataSource.generalTestFolderName;

        Utils.openDrawer(device);
        device.findObject(By.text(storageText)).click();
    }

    @Test
    public void testSwipingBetweenFolders() throws Exception{

        //make sure we're on the leftmost screen
        Utils.swipeToLeftScreen(device);

        //navigate to pager to images
        Utils.openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        navDrawer.scrollTextIntoView(videosText);
        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                videosText
        ).click();

        Utils.waitForText(device, videosText);

        //swipe to other screen
        Utils.swipeToRightScreen(device);


        //navigate to recent files
        Utils.openDrawer(device);
        navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        navDrawer.scrollTextIntoView(recentFilesText);
        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                recentFilesText
        ).click();

        Utils.waitForText(device, recentFilesText);

        //assert we can swipe between the two folders
        Utils.swipeToLeftScreen(device);
        assertTrue(videosText + " should be visible in path",
                device.findObject(By.res("com.amaze.filemanager:id/fullpath"))
                        .getText().endsWith(videosText));

        Utils.swipeToRightScreen(device);
        assertTrue(recentFilesText + " should be visible in path",
                device.findObject(By.res("com.amaze.filemanager:id/fullpath"))
                        .getText().endsWith(recentFilesText));
    }

    @Test
    public void testGridView() throws Exception{
        Utils.swipeToRightScreen(device);

        //assert .../Testing is visible
        assertGeneralTestingFolderVisible();

        //switch to grid layout
        swipeUpInMainView();
        Utils.openOverflowMenu(device);
        Utils.waitForText(device, gridViewText).click();
        assertGeneralTestingFolderVisible();

        //switch back to list layout
        swipeUpInMainView();
        Utils.openOverflowMenu(device);
        Utils.waitForText(device, listViewText).click();
        assertGeneralTestingFolderVisible();
    }



    private void swipeUpInMainView() throws Exception{
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/listView"));

        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                generalTestFolderName)
        .swipeDown(10);
    }

    private void assertGeneralTestingFolderVisible() throws Exception{
        UiScrollable recyclerView = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/listView"));

        assertTrue("Folder " + generalTestFolderName + " is not visible",
                recyclerView.scrollTextIntoView(generalTestFolderName));
    }
}

