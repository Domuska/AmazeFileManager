package com.amaze.filemanager.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.R;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

public class BaseTestClass extends BaseUIAutomatorTest{

    private String amazeTestingFolder = TestDataSource.amazeTestFolderName;
    private String storageText;

    @Before
    public final void setUpBaseTestClass() throws Exception{
        storageText = InstrumentationRegistry.getTargetContext().getString(R.string.storage);
        Utils.navigateToTestFolder(device);
        Utils.createFolderWithName(device, amazeTestingFolder);
        device.wait(Until.hasObject(By.text(amazeTestingFolder)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

//        device.findObject(By.text(amazeTestingFolder)).click();
        device.findObject(new UiSelector().text(amazeTestingFolder)).click();
    }

    @After
    public final void tearDownBaseTestClass() throws Exception{

//        Utils.navigateToTestFolder(device);
//        device.findObject(By.text(amazeTestingFolder)).longClick();
//        Utils.swipeDownInPathBar(device);
//        device.findObject(By.res("com.amaze.filemanager:id/delete")).click();
//
//        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")),
//                BaseUIAutomatorTest.GENERAL_TIMEOUT);
//
//        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();


        //go back to the main storage list
        Utils.openDrawer(device);
        device.findObject(By.text(storageText)).click();
        //wait so that the nav drawer is closed
        device.wait(Until.hasObject(By.text("Testing")), 3000);

        //run adb shell command to remove the testing folder
        InstrumentationRegistry.getInstrumentation().getUiAutomation()
                .executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestingFolder);
    }
}
