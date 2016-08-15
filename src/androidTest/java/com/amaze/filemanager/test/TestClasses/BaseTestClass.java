package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

public class BaseTestClass extends BaseRobotiumTest {

    private String generalTestFolderName = TestDataSource.generalTestFolderName;

    public void setUp() throws Exception{

        //since using JUnit3, we cant use @Before annotation and have to call the setUp first
        super.setUp();

        //set up the testing folder
        Utils.navigateToTestFolder(solo);
        Utils.createFolderWithName(solo, amazeTestFolderName);
        solo.clickOnText(amazeTestFolderName);
    }

    public void tearDown() throws Exception{
        //since using JUnit3, we cant use @After annotation and have to call the tearDown last
        super.tearDown();
    }
}
