package com.amaze.filemanager.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName1, folderName2, copyText;

    @Before
    public void setUp(){
        fileName = TestDataSource.textFileName;
        folderName1 = TestDataSource.folderNames[0];
        folderName2 = TestDataSource.folderNames[1];
        copyText = "Copy";
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

        device.findObject(new UiSelector().resourceId("com.amaze.filemanager:id/buttonDefaultPositive"))
                .clickAndWaitForNewWindow();

        //assert it is gone
        assertFalse("File " + fileName + " should be gone",
                device.findObject(new UiSelector().text(fileName)).exists());
    }

    @Test
    public void testCreateNewFolderAndDeleteIt() throws Exception{
        Utils.createFolderWithName(device, folderName1);

        //assert folder is visible
        assertTrue("Folder should be visible",
                device.findObject(new UiSelector().text(folderName1)).exists());

        //delete the folder
        device.findObject(new UiSelector().text(folderName1)).longClick();

        device.wait(Until.findObject(
                By.res("com.amaze.filemanager:id/delete")), GENERAL_TIMEOUT).click();
        device.findObject(new UiSelector().resourceId("com.amaze.filemanager:id/buttonDefaultPositive"))
                .clickAndWaitForNewWindow();

        //assert folder is deleted
        assertFalse("Folder should be gone",
                device.findObject(new UiSelector().text(folderName1)).exists());
    }

    @Test
    public void testCopyFileToAnotherFolder() throws Exception{

        Utils.createFolderWithName(device, folderName1);
        device.wait(Until.hasObject(By.text(folderName1)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        Utils.createFolderWithName(device, folderName2);
        device.wait(Until.hasObject(By.text(folderName2)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        //add file to the folder
        device.findObject(By.text(folderName1)).click();
        Utils.createFileWithName(device, fileName);
        device.wait(Until.hasObject(By.text(fileName)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);

        //copy the file
        device.findObject(By.res("com.amaze.filemanager:id/properties")).click();
        device.wait(Until.findObject(By.text(copyText)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT).click();

        device.pressBack();
        Utils.swipeDownInPathBar(device);

        //paste the file
//        device.findObject(By.text(folderName2)).click();
        device.wait(Until.findObject(By.text(folderName2)), GENERAL_TIMEOUT).click();
        device.findObject(By.res("com.amaze.filemanager:id/paste")).click();

        //assert it is visible
        device.wait(Until.hasObject(By.text(fileName)),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
        assertTrue("File" + fileName + " should be copied in folder " + folderName2,
                device.findObject(new UiSelector().text(fileName)).exists());
    }

}
