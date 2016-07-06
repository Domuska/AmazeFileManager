package com.amaze.filemanager.test;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.amaze.filemanager.*;
import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

public class BaseTestClass {

    private String storageText, generalTestFolderName, amazeTestFolderName;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUpTestingFolder(){

        storageText = myActivityRule.getActivity().
                getApplicationContext().getResources().getString(R.string.storage);
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;

        navigateToTestFolder();

        Utils.createFolderWithName(amazeTestFolderName);
        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

        navigateToTestFolder();

        //remove the testing folder
        onView(withText(amazeTestFolderName)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());
    }


    private void navigateToTestFolder() {
        Utils.openDrawer();
        onView(withText(storageText)).perform(click());


        //have to use the isDisplayed because there's actually two lists in the app open at this point
        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(generalTestFolderName)), click()
                ));

    }
}
