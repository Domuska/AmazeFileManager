package com.amaze.filemanager.test;

import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName1, folderName2, copyText;


    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName1 = TestDataSource.folderNames[0];
        folderName2 = TestDataSource.folderNames[1];
        copyText = myActivityRule.getActivity().getString(R.string.copy);
    }

    @Test
    public void testCreateNewFileAndDeleteIt(){

        Utils.createFileWithName(fileName);

        //assert it is visible
        onView(withText(fileName)).check(matches(isDisplayed()));

        //delete the file
        onView(withText(fileName)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //assert it is deleted
        onView(withText(fileName)).check(doesNotExist());
    }

    @Test
    public void testCreateNewFolderAndDeleteIt(){

        Utils.createFolderWithName(folderName1);

        //assert folder is visible
        onView(withText(folderName1)).check(matches(isDisplayed()));

        //delete the folder
        onView(withText(folderName1)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //assert the folder is deleted
        onView(withText(folderName1)).check(doesNotExist());

    }

    @Test
    public void testCopyFileToAnotherFolder(){

        Utils.createFolderWithName(folderName1);
        Utils.createFolderWithName(folderName2);

        //add file to the folder
        onView(withText(folderName1)).perform(click());
        Utils.createFileWithName(fileName);

        //copy the file
        onView(allOf(withId(R.id.properties), isDisplayed())).perform(click());
        onView(withText(copyText)).perform(click());

        Espresso.pressBack();

        //paste the file to another folder
        onView(withText(folderName2)).perform(click());
        onView(withId(R.id.paste)).perform(click());


        //assert file is visible
        onView(withText(fileName)).check(matches(isDisplayed()));
    }

}
