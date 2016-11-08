package com.amaze.filemanager.test.TestClasses;

import android.support.test.InstrumentationRegistry;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class BaseTestClass extends BaseEspressoTestClass{

    private String generalTestFolderName, amazeTestFolderName;

    @Before
    public void setupTestingFolder(){
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;

        Utils.navigateToTestFolder(generalTestFolderName);

        Utils.createFolderWithName(amazeTestFolderName);
        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

        //using ADB shell command (API lvl 21+)
        InstrumentationRegistry.getInstrumentation().
                getUiAutomation().executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
    }
}
