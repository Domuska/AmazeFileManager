package com.amaze.filemanager.test.TestClasses;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;

public class BaseTestClass extends BaseUIAutomatorTest{

    @Before
    public final void setUpBaseTestClass() throws Exception{
        Utils.navigateToTestFolder(device);
        Utils.createFolderWithName(device, amazeTestingFolder);
        device.wait(Until.hasObject(By.text(amazeTestingFolder)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(new UiSelector().text(amazeTestingFolder)).click();
    }
}
