package com.amaze.filemanager.test;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasToString;

public class BaseTestClass {

    private String storageText, generalTestFolderName, amazeTestFolderName, sdcardText;


    @Before
    public void setUpTestingFolder(){

        storageText = "Storage";
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;
        sdcardText = "sdcard";

        Utils.navigateToTestFolder(generalTestFolderName);

        Utils.createFolderWithName(amazeTestFolderName);
        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

        Utils.navigateToTestFolder(generalTestFolderName);

        //remove the testing folder
        onView(withText(amazeTestFolderName)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());
    }



}
