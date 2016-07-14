package com.amaze.filemanager.test;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

public class BaseTestClass extends BaseUIAutomatorTest{

    private String amazeTestingFolder = TestDataSource.amazeTestFolderName;

    @Before
    public final void setUpBaseTestClass() throws Exception{
        Utils.navigateToTestFolder(device);
        Utils.createFolderWithName(device, amazeTestingFolder);
        device.wait(Until.hasObject(By.text(amazeTestingFolder)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

//        device.findObject(By.text(amazeTestingFolder)).click();
        device.findObject(new UiSelector().text(amazeTestingFolder)).click();
    }

    @After
    public final void tearDownBaseTestClass() throws Exception{

//        device.pressBack();
//        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/pathbar")),
//                BaseUIAutomatorTest.GENERAL_TIMEOUT);

//        Utils.swipeDownInPathBar(device);
        Utils.navigateToTestFolder(device);
        device.findObject(By.text(amazeTestingFolder)).longClick();
        Utils.swipeDownInPathBar(device);
        device.findObject(By.res("com.amaze.filemanager:id/delete")).click();

        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();
    }
}
