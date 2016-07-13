package com.amaze.filemanager.test.Utilities;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

public class Utils {

    private static String generalTestingFolderName = TestDataSource.generalTestFolderName;

    public static void navigateToTestFolder(UiDevice device) throws Exception{
        device.findObject(By.res("com.amaze.filemanager:id/home")).click();

        UiScrollable mainFragment = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/listView"));

//        mainFragment.scrollTextIntoView(generalTestingFolderName);
        mainFragment.getChildByText(
                new UiSelector().resourceId("com.amaze.filemanager:id/second"),
                generalTestingFolderName)
            .click();

//        device.findObject(By.text(generalTestingFolderName)).click();
    }

    public static void createFileWithName(UiDevice device, String filename){

    }

    public static void createFolderWithName(UiDevice device, String folderName) {

    }

    private static void openFabMenu(){

    }
}
