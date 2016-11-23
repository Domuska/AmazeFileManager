package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

public class BaseTestClass extends BaseAppiumTest{

    String amazeTestFolderName = TestDataSource.amazeTestFolderName;


    @Before
    final public void setUpBaseTestClass(){
        //go to the testing folder in root of sdcard (hopefully)
        Utils.navigateToTestingFolder(driver);

        //create the testing folder
        Utils.createFolderWithName(driver, amazeTestFolderName);
//        driver.findElementByName(amazeTestFolderName).click();
        Utils.findElementByName(driver, amazeTestFolderName).click();
    }

    @After
    final public void tearDownBaseTestClass(){
        // move back from the folder we're in so we can push the home
        // button or alternatively to close nav drawer
        driver.navigate().back();

        //delete the testing folder
        try {
            Runtime.getRuntime().exec("adb shell rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
