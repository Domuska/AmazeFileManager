package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertTrue;

public class NavigationTests extends BaseAppiumTest{

    private String moviesFolderName = "Movies";
    private String recentFilesFolderName = "Recent Files";

    private String generalTestFolderName = TestDataSource.generalTestFolderName;
    private String overflowGridText = "Grid View";
    private String overflowLIstText = "List View";

    @Before
    public void setUp(){
        Utils.openDrawer(driver);
        Utils.findElementByName(driver, storageText).click();
    }

    @Test
    public void testSwipingBetweenTwoFolders(){

        //make sure we're on the left screen at start
        Utils.swipeToLeftScreen(driver);

        //navigate one view into the images folder
        Utils.openDrawer(driver);
        Utils.searchInVisibleListWithName(driver, moviesFolderName).click();

        //swipe to the other screen
        Utils.swipeToRightScreen(driver);
        Utils.openDrawer(driver);

        //open recent files
        Utils.searchInVisibleListWithName(driver, recentFilesFolderName).click();

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

    @Test
    public void testGridView(){
        Utils.swipeToRightScreen(driver);

        //assert .../Testing is visible
        Utils.searchInVisibleListWithName(driver, generalTestFolderName);

        //switch to grid layout
        swipeUpInMainView();
        Utils.openOverflowMenu(driver);
        Utils.findElementByName(driver, overflowGridText).click();
        Utils.searchInVisibleListWithName(driver, generalTestFolderName);

        //switch back to list layout
        swipeUpInMainView();
        Utils.openOverflowMenu(driver);
        Utils.findElementByName(driver, overflowLIstText).click();
        Utils.searchInVisibleListWithName(driver, generalTestFolderName);
    }

    private void swipeUpInMainView(){
        WebElement mainPager = driver.findElementById("com.amaze.filemanager:id/pager");

        Dimension dimension = mainPager.getSize();
        Point startPoint = new Point(dimension.getWidth()/2, dimension.getHeight()/2);
        Point endPoint = new Point(startPoint.getX(), startPoint.getY()+100);

        driver.swipe(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), 200);
    }

}
