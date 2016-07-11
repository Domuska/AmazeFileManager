package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.helpers.Util;


import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName, folderName1, folderName2;

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName1 = TestDataSource.folderNames[0];
        folderName2 = TestDataSource.folderNames[1];
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
        Utils.createFolderWithName(driver, folderName1);

        //assert it exists
        if(driver.findElements(By.name(folderName1)).isEmpty()){
            fail("Folder is not visible");
        }

        //remove the folder
        WebElement folder = driver.findElementByName(folderName1);
        TouchAction longPress = new TouchAction(driver);
        longPress.longPress(folder, 2000).release().perform();
        driver.findElementById("com.amaze.filemanager:id/delete").click();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

        //assert the folder is deleted
        if(!driver.findElements(By.name(folderName1)).isEmpty()){
            fail("Folder is still visible, should be deleted");
        }
    }

    @Test
    public void testCopyFileToAnotherFolder(){
        Utils.createFolderWithName(driver, folderName1);
        Utils.createFolderWithName(driver, folderName2);

        //add file to folder 1
        driver.findElementByName(folderName1).click();
        Utils.createFileWithName(driver, fileName);

        //cut the file to the other folder
        driver.findElementById("com.amaze.filemanager:id/properties").click();
        driver.findElementByName("Cut").click();

        driver.navigate().back();

        //have to swipe down to make the top part of toolbar visible
        Utils.swipeDownInPathBar(driver);

        //paste the file to another folder
        driver.findElementByName(folderName2).click();

        driver.findElementById("com.amaze.filemanager:id/paste").click();

        //assert the file is is present
        if(driver.findElements(By.name(fileName)).isEmpty()){
            fail("File should be pasted in, is not visible on the screen");
        }
    }

}
