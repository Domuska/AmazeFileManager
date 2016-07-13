package com.amaze.filemanager.test.Utilities;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.afollestad.materialdialogs.GravityEnum;

public class Utils {

    private static String generalTestingFolderName = TestDataSource.generalTestFolderName;

    public static void navigateToTestFolder(UiDevice device) throws Exception{
        device.findObject(By.res("com.amaze.filemanager:id/home")).click();

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

    public static void createFolderWithName(UiDevice device, String folderName) {
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
        UiObject2 pathBar = device.findObject(By.res("com.amaze.filemanager:id/pathbar"));
        Point middlePoint = pathBar.getVisibleCenter();
        Rect bounds = pathBar.getVisibleBounds();

        Point targetPoint = new Point(middlePoint.x, middlePoint.y + bounds.height());
        pathBar.drag(targetPoint, 1000);
    }

    private static void openFabMenu(UiDevice device){
        UiObject2 object = device.findObject(By.res("com.amaze.filemanager:id/menu"));
        object.findObject(By.clazz("android.widget.ImageView")).click();
    }
}
