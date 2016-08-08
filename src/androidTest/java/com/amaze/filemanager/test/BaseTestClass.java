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

    @Before
    public final void setUpBaseTestClass() throws Exception{
        Utils.navigateToTestFolder(device);
        Utils.createFolderWithName(device, amazeTestingFolder);
        device.wait(Until.hasObject(By.text(amazeTestingFolder)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        device.findObject(new UiSelector().text(amazeTestingFolder)).click();
    }

    @After
    public final void tearDownBaseTestClass() throws Exception{
        //run adb shell command to remove the testing folder
        InstrumentationRegistry.getInstrumentation().getUiAutomation()
                .executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestingFolder);
    }
}
