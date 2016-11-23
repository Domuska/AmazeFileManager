package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;

import static junit.framework.Assert.fail;

public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName = TestDataSource.textFileName;
    private String folderName1 = TestDataSource.folderNames[0];
    private String folderName2= TestDataSource.folderNames[1];

    @Test
    public void testCreateNewFileAndDeleteIt(){
        try {
            //create the file
            Utils.createFileWithName(driver, fileName);

            //assert it exists
            if (Utils.findElementsByName(driver, fileName).isEmpty()) {
                fail("File is not visible");
            }

            //delete the file
            WebElement folder = stareAtPixies.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='" + fileName + "']")));
            TouchAction longPress = new TouchAction(driver);
            longPress.longPress(folder, 2000).release().perform();
            driver.findElementById("com.amaze.filemanager:id/delete").click();
            driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

            //assert the file is deleted
            if (!Utils.findElementsByName(driver, fileName).isEmpty()) {
                fail("File is still visible, should be deleted");
            }
        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }

    @Test
    public void testCreateNewFolderAndDeleteIt(){
        try {
            //create the folder
            Utils.createFolderWithName(driver, folderName1);

            //assert it exists
            if (Utils.findElementsByName(driver, folderName1).isEmpty()) {
                fail("Folder is not visible");
            }

            //remove the folder
            WebElement folder = Utils.findElementByName(driver, folderName1);
            TouchAction longPress = new TouchAction(driver);
            longPress.longPress(folder, 2000).release().perform();
            driver.findElementById("com.amaze.filemanager:id/delete").click();
            driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

            //assert the folder is deleted
            if (!Utils.findElementsByName(driver, folderName1).isEmpty()) {
                fail("Folder is still visible, should be deleted");
            }
        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }

    @Test
    public void testCopyFileToAnotherFolder() {
        try {
            Utils.createFolderWithName(driver, folderName1);
            Utils.createFolderWithName(driver, folderName2);

            //add file to folder 1
            Utils.findElementByName(driver, folderName1).click();
            Utils.createFileWithName(driver, fileName);

            //copy the file to the other folder
            driver.findElementById("com.amaze.filemanager:id/properties").click();
            stareAtPixies.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='Copy']")))
                    .click();

            driver.navigate().back();

            //have to swipe down to make the top part of toolbar visible
            Utils.swipeDownInPathBar(driver);

            //paste the file to another folder
            Utils.findElementByName(driver, folderName2).click();
            driver.findElementById("com.amaze.filemanager:id/paste").click();

            //wait for the slow copy action to perform
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='" + fileName + "']")
            ));

            //assert the file is is visible
            if (Utils.findElementsByName(driver, fileName).isEmpty()) {
                fail("File should be pasted in, is not visible on the screen");
            }
        } catch (Exception e) {
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }

    }
}
