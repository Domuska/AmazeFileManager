package com.amaze.filemanager.test.TestClasses;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNot.not;

public class QuickAccessFileTests extends BaseTestClass{

    private String fileName, quickAccessText;

    @Rule
    public IntentsTestRule<MainActivity> myActivityRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        fileName = TestDataSource.movieFileName;
        quickAccessText = myActivityRule.getActivity().getString(R.string.quick);
    }

    @Before
    public void stubAllExternalIntents(){
        intending(not(isInternal()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void testOpenFileCheckRecents(){
        Utils.createFileWithName(fileName);

        //open the file multiple times so it is added to "Quick Access"
        onView(withText(fileName)).perform(click());
        onView(withText(fileName)).perform(click());
        onView(withText(fileName)).perform(click());
        onView(withText(fileName)).perform(click());

        //go to quick access
        Utils.openDrawer();
        onData(hasToString(quickAccessText))
                .inAdapterView(withId(com.amaze.filemanager.R.id.menu_drawer))
                .perform(click());

        //assert file name is visible here
        onView(withText(fileName)).check(matches(isDisplayed()));
    }
}
