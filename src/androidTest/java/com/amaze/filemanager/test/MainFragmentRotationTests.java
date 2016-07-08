package com.amaze.filemanager.test;

import android.support.test.rule.ActivityTestRule;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.amaze.filemanager.test.Utilities.OrientationChangeAction.orientationLandscape;
import static com.amaze.filemanager.test.Utilities.OrientationChangeAction.orientationPortrait;


public class MainFragmentRotationTests extends BaseTestClass{

    private String fileName, folderName;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName = TestDataSource.folderNames[0];
    }


    @Test
    public void testRotateMainFragment(){

        Utils.createFileWithName(fileName);
        Utils.createFolderWithName(folderName);

        //rotate screen
        onView(isRoot()).perform(orientationLandscape());

        //assert file and folder still visible
        onView(withText(fileName)).check(matches(isDisplayed()));
        onView(withText(folderName)).check(matches(isDisplayed()));

        //rotate again
        onView(isRoot()).perform(orientationPortrait());

        //assert file and folder still visible
        onView(withText(fileName)).check(matches(isDisplayed()));
        onView(withText(folderName)).check(matches(isDisplayed()));

    }
}
