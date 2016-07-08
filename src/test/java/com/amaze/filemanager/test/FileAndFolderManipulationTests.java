package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.ActionChainExecutor;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName;

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName = TestDataSource.folderNames[0];
    }


    @Test
    public void testCreateFolderAndDeleteIt(){

        Utils.navigateToTestingFolder(driver);

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
