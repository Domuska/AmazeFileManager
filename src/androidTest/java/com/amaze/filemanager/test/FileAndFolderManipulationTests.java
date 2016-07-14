package com.amaze.filemanager.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName1, folderName2, copyText;

    @Before
    public void setUp(){

        Context contex = InstrumentationRegistry.getContext();
        fileName = TestDataSource.textFileName;
        folderName1 = TestDataSource.folderNames[0];
    }

    @Test
    public void testCreateNewFileAndDeleteIt() throws Exception{

        Utils.createFileWithName(device, fileName);

        //assert file is visible
        assertTrue("New file with name " + fileName + " not found",
                device.findObject(new UiSelector().text(fileName)).exists());

        //delete the file
        device.findObject(By.text(fileName)).longClick();
        device.findObject(By.res("com.amaze.filemanager:id/delete")).click();
//        device.wait(Until.hasObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")),
//                BaseUIAutomatorTest.GENERAL_TIMEOUT);

//        device.findObject(By.res("com.amaze.filemanager:id/buttonDefaultPositive")).click();
        device.findObject(new UiSelector().resourceId("com.amaze.filemanager:id/buttonDefaultPositive"))
                .clickAndWaitForNewWindow();

        //assert it is gone
        assertFalse("File " + fileName + " should be gone",
                device.findObject(new UiSelector().text(fileName)).exists());
    }


}
