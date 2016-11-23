package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

import static junit.framework.Assert.fail;

public class MainFragmentRotationTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String folderName = TestDataSource.folderNames[0];


    @Test
    public void testRotateMainFragment(){
        try {
            //add test files
            Utils.createFileWithName(driver, fileName);
            Utils.createFolderWithName(driver, folderName);

            //rotate screen and assert views visible
            driver.rotate(ScreenOrientation.LANDSCAPE);
            assertViewsVisible();

            //rotate screen again and assert views visible
            driver.rotate(ScreenOrientation.PORTRAIT);
            assertViewsVisible();
        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }

    private void assertViewsVisible(){

        if(Utils.findElementsByName(driver, fileName).isEmpty()){
            fail("The element named " + fileName  + " should be visible");
        }
        if(Utils.findElementsByName(driver, folderName).isEmpty()){
            fail("The element named " + folderName  + " should be visible");
        }

    }
}
