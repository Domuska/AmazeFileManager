package com.amaze.filemanager.test;

import com.amaze.filemanager.database.TabHandler;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.helpers.Util;

import javax.sql.DataSource;

import static org.junit.Assert.assertTrue;

public class SetHomeTest extends BaseAppiumTest{

    private String setAsHomeText = "Set As Home";
    private String quickAccessText = "Quick Access";
    private String generalTestingFolderName = TestDataSource.generalTestFolderName;

    //todo handle the @before annotation problems
    @Before
    public void setUpasfdasfdsadfsdfsadfsadfsfasfdasdfafasdfasdfasdfasdffasdfasdfsdf(){
        Utils.swipeToRightScreen(driver);
        Utils.navigateToTestingFolder(driver);
    }

    //todo this way of clearing down does not work
    /*
    see if the database or preferences needs to be cleared using espresso tests.
    could also be that we cant call the app's methods in these tests
     */
    @After
    public void tearDown(){
//        TabHandler.clearDatabase();
    }

    @Test
    public void testSetHome(){

        //set this folder as home folder
        Utils.swipeDownInPathBar(driver);
        Utils.openOverflowMenu(driver);
        driver.findElementByName(setAsHomeText).click();
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();

        //navigate to somewhere else
        Utils.openDrawer(driver);
        Utils.searchInVisibleListWithName(driver, quickAccessText).click();

        //go back home
        driver.findElementById("com.amaze.filemanager:id/home").click();

        //assert we're in the .../Testing folder
        String filePathText =
                driver.findElementById("com.amaze.filemanager:id/fullpath").getText();
        assertTrue("Wrong file path, should contain folder name " + generalTestingFolderName,
                filePathText.contains(generalTestingFolderName));

    }
}
