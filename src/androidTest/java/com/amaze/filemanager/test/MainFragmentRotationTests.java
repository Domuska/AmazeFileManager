package com.amaze.filemanager.test;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainFragmentRotationTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String folderName = TestDataSource.folderNames[0];

    @After
    public void tearDown() throws Exception{
        device.unfreezeRotation();
    }

    @Test
    public void testRotateMainFragment() throws Exception{
        Utils.createFileWithName(device, fileName);
        Utils.createFolderWithName(device, folderName);

        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/menu")), GENERAL_TIMEOUT);

        //rotate screen
        device.setOrientationLeft();

        //assert file and folder visible
        assertFileAndFolderVisible();

        //rotate screen
        device.setOrientationNatural();

        //assert file and folder visible
        assertFileAndFolderVisible();
    }

    private void assertFileAndFolderVisible() {
        assertTrue("File " + fileName + " should be visible",
                device.findObject(new UiSelector().text(fileName)).exists());
        assertTrue("Folder " + folderName + " should be visible",
                device.findObject(new UiSelector().text(folderName)).exists());
    }

}
