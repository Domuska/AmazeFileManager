package com.amaze.filemanager.test;

import android.support.test.InstrumentationRegistry;

import com.amaze.filemanager.R;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

public class BaseTestClass extends BaseRobotiumTest {

    private String generalTestFolderName = TestDataSource.generalTestFolderName;
    private String amazeTestFolderName = TestDataSource.amazeTestFolderName;


    public void setUp() throws Exception{

        //since using JUnit3, we cant use @Before annotation and have to call the setUp first
        super.setUp();

        //set up the testing folder
        Utils.navigateToTestFolder(solo);
        Utils.createFolderWithName(solo, amazeTestFolderName);
        solo.clickOnText(amazeTestFolderName);
    }

    public void tearDown() throws Exception{

        //using ADB shell command (API lvl 21+) to remove folder
        InstrumentationRegistry.getInstrumentation().
                getUiAutomation().executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);

        //since using JUnit3, we cant use @After annotation and have to call the tearDown last
        super.tearDown();
    }
}
