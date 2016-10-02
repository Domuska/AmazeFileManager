package com.amaze.filemanager.test.Utilities;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.TestClasses.BaseTestClass;
import com.amaze.filemanager.test.TestClasses.BaseUIAutomatorTest;

import static junit.framework.Assert.assertEquals;

public class Utils {

    private static String generalTestingFolderName = TestDataSource.generalTestFolderName;
    private static String storageText = "Storage";
    private static String overflowContentDesc = "More options";

    public static void navigateToTestFolder(UiDevice device) throws Exception{

        openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                storageText).click();

        UiScrollable mainFragment = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/listView"));

        mainFragment.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                generalTestingFolderName)
            .click();
    }

    public static void createFileWithName(UiDevice device, String filename){
        openFabMenu(device);
        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/menu_item1")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(By.res("com.amaze.filemanager:id/menu_item1")).click();
        device.wait(Until.hasObject(By.res("android:id/input")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(By.res("android:id/input")).setText(filename);
        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();
    }

    public static void createFolderWithName(UiDevice device, String folderName) throws Exception{
        openFabMenu(device);
        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/menu_item1")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(By.res("com.amaze.filemanager:id/menu_item")).click();
        device.wait(Until.hasObject(By.res("android:id/input")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(By.res("android:id/input")).setText(folderName);
        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();
    }

    public static void swipeDownInPathBar(UiDevice device){
        UiObject2 pathBar = device.findObject(By.res("com.amaze.filemanager:id/buttonbarframe"));
        Point middlePoint = pathBar.getVisibleCenter();
        Rect bounds = pathBar.getVisibleBounds();

        Point targetPoint = new Point(middlePoint.x, middlePoint.y + bounds.height());
        pathBar.drag(targetPoint, 500);
    }

    private static void openFabMenu(UiDevice device){
        UiObject2 object = device.wait(Until.findObject(By.res("com.amaze.filemanager:id/menu")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
        object.findObject(By.clazz("android.widget.ImageView")).click();
    }

    public static void openDrawer(UiDevice device){

        Point startPoint = new Point(0, device.getDisplayHeight()/2);
        Point endPoint = new Point(device.getDisplayWidth()/2, device.getDisplayHeight()/2);

        //wait that all popup windows and such are closed
        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/pager")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
        device.drag(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 5);
        device.waitForIdle(5000);
    }

    public static void swipeToLeftScreen(UiDevice device){

        Point startPoint = new Point(device.getDisplayWidth()/4, device.getDisplayHeight()/2);
        Point endPoint = new Point(device.getDisplayWidth()-20, startPoint.y);

        device.swipe(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 5);
    }

    public static void swipeToRightScreen(UiDevice device){
        //-20 to width so we dont start from absolute edge, swipe wont work that way
        Point startPoint = new Point(device.getDisplayWidth()-20, device.getDisplayHeight()/2);
        Point endPoint = new Point(device.getDisplayWidth()/4, startPoint.y);

        device.swipe(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 5);
        device.waitForIdle(BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }

    public static void openOverflowMenu(UiDevice device){
        device.findObject(By.descContains(overflowContentDesc)).click();
    }

    public static UiObject2 waitForText(UiDevice device, String text) {
        return device.wait(Until.findObject(By.text(text)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }

    public static void addFileToBookMarks(UiDevice device, String folderName) throws Exception{

        UiScrollable mainFragment = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/listView"));

        UiObject row = mainFragment.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                folderName);

        row.getChild(new UiSelector().className("android.widget.ImageButton")).click();

        device.wait(Until.findObject(By.text("Add to Bookmark")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT)
        .click();
    }

    //dirty workaround for getting consistent long clicking, it can
    //fail unexpectedly at a times. Instead use swipe action.
    public static void myLongClick(UiDevice device, String text){
        UiObject2 object = device.wait(Until.findObject(By.text(text)), BaseTestClass.GENERAL_TIMEOUT);
        Rect coordinates = object.getVisibleBounds();
        device.swipe(coordinates.centerX(), coordinates.centerY(),
                coordinates.centerX(), coordinates.centerY(),
                100);
    }
}
