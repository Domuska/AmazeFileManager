package com.amaze.filemanager.test;

import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.amaze.filemanager.*;
import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.database.TabHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasToString;

public class SetHomeTest{

    private String storageText, generalTestFolderName,
            amazeTestFolderName, sdcardText, newFolderName,
            setAsHomeText, quickAccessText, historyText;

    @Rule
    public IntentsTestRule<MainActivity> myActivityRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){

        Utils.swipeToRightScreen();

        storageText = "Storage";
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;
        sdcardText = "sdcard";
        newFolderName = TestDataSource.folderNames[0];
        setAsHomeText = myActivityRule.getActivity().getString(R.string.setashome);
        quickAccessText =  myActivityRule.getActivity().getString(R.string.quick);
        historyText = myActivityRule.getActivity().getString(R.string.history);

        Utils.navigateToTestFolder(generalTestFolderName);

//        Utils.createFolderWithName(amazeTestFolderName);
//        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

//        Utils.navigateToTestFolder(generalTestFolderName);
        //open overflow menu and select history to navigate back to /storage/emulated/0
//        openActionBarOverflowOrOptionsMenu(
//                InstrumentationRegistry.getInstrumentation().getTargetContext());
//        onView(withText(historyText)).perform(click());
//        onView(withText("/storage/emulated/0")).perform(click());


        //go to Testing folder in sdcard
//        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
//                .perform(RecyclerViewActions.actionOnItem(
//                        hasDescendant(withText(generalTestFolderName)), click()
//                ));
//
//        //remove the amazeTesting folder
//        onView(withText(amazeTestFolderName)).perform(longClick());
//        onView(withId(com.amaze.filemanager.R.id.delete)).perform(click());
//        onView(withId(com.amaze.filemanager.R.id.buttonDefaultPositive)).perform(click());

        //clear preferences
        PreferenceManager.
                getDefaultSharedPreferences(
                        myActivityRule.getActivity().getApplicationContext())
                .edit().clear().commit();

        TabHandler.clearDatabase(myActivityRule.getActivity().getApplicationContext());
    }

    @Test
    public void testSetHome(){

//        Utils.createFolderWithName(newFolderName);
//        onView(withText(newFolderName)).perform(click());

        openActionBarOverflowOrOptionsMenu(
                InstrumentationRegistry.getInstrumentation().getTargetContext());

        //set this folder as home
        onView(withText(setAsHomeText)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //navigate to somewhere else
        Utils.openDrawer();
        onData(hasToString(quickAccessText))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(click());

        //go back to home
        onView(withId(R.id.home)).perform(click());

        onView(allOf(withId(R.id.fullpath), isDisplayed()))
                .check(matches(withText(endsWith(generalTestFolderName))));
    }
}
