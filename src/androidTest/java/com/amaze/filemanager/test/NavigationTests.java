package com.amaze.filemanager.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTests {

    private String storageText, recentFilesText, videosText;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        storageText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(com.amaze.filemanager.R.string.storage);
        recentFilesText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(R.string.recent);
        videosText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(R.string.videos);
    }

    @Test
    public void testSwipingBetweenTwoFolders(){

        //make sure we're on the leftmost screen
        swipeScreenRight();

        //navigate one view into the images
        Utils.openDrawer();
        onData(hasToString(videosText))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(click());

        //swipe into the other list
        swipeScreenLeft();


        Utils.openDrawer();

        //open 'Recent Files'
        onData(hasToString(recentFilesText))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(click());

//        onData(hasProperty("Title", equalTo(recentFilesText)))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .perform(click());
        //löytää useampia resultteja, pitäs tarkentaa
//        onData(allOf(is(instanceOf(EntryItem.class)))).perform(click());

        //works, but is a scheisse way to do it
//        onData(anything()).inAdapterView(withId(R.id.menu_drawer))
//                .atPosition(11).perform(click());

        swipeScreenRight();
        onView(withId(R.id.fullpath)).check(matches(allOf(isDisplayed(), withText(videosText))));
        swipeScreenLeft();
        onView(withId(R.id.fullpath)).check(matches(allOf(isDisplayed(), withText(recentFilesText))));


    }

    //0 for left, 1 for right
    private void swipeScreenLeft(){
        onView(allOf(withId(R.id.pager), isDisplayed()))
                .perform(swipeLeft());
    }

    private void swipeScreenRight(){
        onView(allOf(withId(R.id.pager), isDisplayed()))
                .perform(swipeRight());
    }
}
