package com.amaze.filemanager.test.TestClasses;

import android.preference.PreferenceManager;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.database.TabHandler;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasToString;

public class SetHomeTest{

    private String generalTestFolderName,
            setAsHomeText, quickAccessText;

    @Rule
    public IntentsTestRule<MainActivity> myActivityRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){

        Utils.swipeToRightScreen();
        generalTestFolderName = TestDataSource.generalTestFolderName;
        setAsHomeText = myActivityRule.getActivity().getString(R.string.setashome);
        quickAccessText =  myActivityRule.getActivity().getString(R.string.quick);

        Utils.navigateToTestFolder(generalTestFolderName);

    }

    @After
    public void tearDown(){
        //clear preferences
        PreferenceManager.
                getDefaultSharedPreferences(
                        myActivityRule.getActivity().getApplicationContext())
                .edit().clear().commit();

        TabHandler.clearDatabase(myActivityRule.getActivity().getApplicationContext());
    }

    @Test
    public void testSetHome(){

        Utils.openOverflowMenu();

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

        //assert we're at the .../Testing folder
        onView(allOf(withId(R.id.fullpath), isDisplayed()))
                .check(matches(withText(endsWith(generalTestFolderName))));
    }
}
