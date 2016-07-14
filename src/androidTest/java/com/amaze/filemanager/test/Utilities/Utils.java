package com.amaze.filemanager.test.Utilities;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.view.Display;
import android.view.WindowManager;

import com.amaze.filemanager.test.BaseUIAutomatorTest;

import static junit.framework.Assert.assertEquals;

public class Utils {

    private static String generalTestingFolderName = TestDataSource.generalTestFolderName;
    private static String storageText = "Storage";
    private static String overflowContentDesc = "More options";

    public static void navigateToTestFolder(UiDevice device) throws Exception{

        openDrawer(device);
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));

//        navDrawer.scrollTextIntoView(storageText);
        navDrawer.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                storageText).click();

//        device.findObject(By.res("com.amaze.filemanager:id/home")).click();

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
//        device.findObject(new UiSelector().resourceId("com.amaze.filemanager:id/buttonDefaultPositive"))
//                .clickAndWaitForNewWindow();
    }

    public static void swipeDownInPathBar(UiDevice device){
//        UiObject2 pathBar = device.findObject(By.res("com.amaze.filemanager:id/pathbar"));
        UiObject2 pathBar = device.findObject(By.res("com.amaze.filemanager:id/buttonbarframe"));
        Point middlePoint = pathBar.getVisibleCenter();
        Rect bounds = pathBar.getVisibleBounds();

        Point targetPoint = new Point(middlePoint.x, middlePoint.y + bounds.height());
        pathBar.drag(targetPoint, 500);
    }

    private static void openFabMenu(UiDevice device){
        UiObject2 object = device.findObject(By.res("com.amaze.filemanager:id/menu"));
        object.findObject(By.clazz("android.widget.ImageView")).click();
    }

    public static void openDrawer(UiDevice device){
        device.findObject(By.res("com.amaze.filemanager:id/pager")).swipe(Direction.RIGHT, 0.6f);
//        WindowManager manager = (WindowManager)
//                InstrumentationRegistry.getTargetContext().getSystemService(Context.WINDOW_SERVICE);
//        Display display = manager.getDefaultDisplay();
//        Point screenSize = new Point();
//        display.getSize(screenSize);
//
//        Point startPoint = new Point(0, screenSize.y/2);
//        Point endPoint = new Point(screenSize.x/2, screenSize.y/2);
//
//        device.drag(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 3);
    }

    public static void swipeToLeftScreen(UiDevice device) throws Exception{

        Point startPoint = new Point(device.getDisplayWidth()/4, device.getDisplayHeight()/2);
        Point endPoint = new Point(device.getDisplayWidth(), startPoint.y);

        device.swipe(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 5);
        device.waitForIdle(BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }

    public static void swipeToRightScreen(UiDevice device){
        //-20 to width so we dont start from absolute edge, swipe wont work that way
        Point startPoint = new Point(device.getDisplayWidth()-20, device.getDisplayHeight()/2);
        Point endPoint = new Point(device.getDisplayWidth()/4, startPoint.y);

        device.swipe(startPoint.x, startPoint.y, endPoint.x, endPoint.y, 5);
        device.waitForIdle(BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }

    public static void openOverflowMenu(UiDevice device){
//        device.pressMenu();
        device.findObject(By.descContains(overflowContentDesc)).click();
    }

    public static UiObject2 waitForText(UiDevice device, String text) {
        return device.wait(Until.findObject(By.text(text)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }
}
