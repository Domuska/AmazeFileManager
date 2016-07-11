package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.slf4j.helpers.Util;

import static junit.framework.Assert.assertTrue;

public class NavigationTests extends BaseAppiumTest{

    private String moviesFolderName = "Movies";
    private String recentFilesFolderName = "Recent Files";

    @Test
    public void testSwipingBetweenTwoFolders(){

        //make sure we're on the left screen at start
        Utils.swipeToLeftScreen(driver);

        //navigate one view into the images folder
        Utils.openDrawer(driver);
        Utils.clickInListWithName(driver, moviesFolderName);

        //swipe to the other screen
        Utils.swipeToRightScreen(driver);
        Utils.openDrawer(driver);

        //open recent files
        Utils.clickInListWithName(driver, recentFilesFolderName);

        //assert swiping between the screens works properly
        Utils.swipeToLeftScreen(driver);
        String filePathText =
                driver.findElementById("com.amaze.filemanager:id/fullpath").getText();
        assertTrue("File path does not contain the correct folder: " + moviesFolderName,
                filePathText.contains(moviesFolderName));

        Utils.swipeToRightScreen(driver);
        filePathText =
                driver.findElementById("com.amaze.filemanager:id/fullpath").getText();
        assertTrue("File path does not contain the correct folder: " + recentFilesFolderName,
                filePathText.contains(recentFilesFolderName));

    }

}
