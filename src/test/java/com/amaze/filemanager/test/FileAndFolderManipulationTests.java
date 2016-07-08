package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.Utils;


import org.junit.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.ActionChainExecutor;

public class FileAndFolderManipulationTests extends BaseTestClass{

    @Test
    public void testCreateFolderAndDeleteIt(){

//        Utils.clickAllowAccess(driver);
        Utils.navigateToTestingFolder(driver);
        Utils.createFileWithName(driver, "asf");

    }

}
