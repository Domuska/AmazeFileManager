package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.helpers.Util;


import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName, folderName;

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName = TestDataSource.folderNames[0];
    }

    @Test
    public void testCreateNewFileAndDeleteIt(){
        //create the file
        Utils.createFileWithName(driver, fileName);

        //assert it exists
        if(driver.findElements(By.name(fileName)).isEmpty()){
            fail("File is not visible");
        }

        //delete the file
        WebElement folder = driver.findElementByName(fileName);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(folder, 2000).release().perform();
        driver.findElementById("com.amaze.filemanager:id/delete").click();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

        //assert the file is deleted
        if(!driver.findElements(By.name(fileName)).isEmpty()){
            fail("File is still visible, should be deleted");
        }

    }

    @Test
    public void testCreateNewFolderAndDeleteIt(){

        //create the folder
        Utils.createFolderWithName(driver, folderName);

        //assert it exists
        if(driver.findElements(By.name(folderName)).isEmpty()){
            fail("Folder is not visible");
        }

        //remove the folder
        WebElement folder = driver.findElementByName(folderName);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(folder, 2000).release().perform();
        driver.findElementById("com.amaze.filemanager:id/delete").click();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

        //assert the folder is deleted
        if(!driver.findElements(By.name(folderName)).isEmpty()){
            fail("Folder is still visible, should be deleted");
        }
    }



}
