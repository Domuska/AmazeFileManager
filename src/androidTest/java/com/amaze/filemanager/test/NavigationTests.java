package com.amaze.filemanager.test;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.amaze.filemanager.*;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTests {

    private String storageText, recentFilesText;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        storageText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(com.amaze.filemanager.R.string.storage);
        recentFilesText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(R.string.recent);
    }

    @Test
    public void testSwipingBetweenTwoFolders(){

        //navigate one view into the storage
        Utils.openDrawer();
        onView(withText(storageText)).perform(click());

        //swipe into the other list
        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
                .perform(swipeRight());

        //open recent files
        Utils.openDrawer();
//        onData(allOf(withId(R.id.menu_drawer), withText(recentFilesText))).perform(scrollTo());
//        onData(withClassName(endsWith("DrawerAdapter"))).perform(click());
//        onData(allOf(is(instanceOf(String.class)), is(recentFilesText)))

        //works, but is a scheisse way to do it
//        onData(anything()).inAdapterView(withId(R.id.menu_drawer))
//                .atPosition(11).perform(click());


        onData().perform(click());

//        onData(anything()).inAdapterView(withId(R.id.menu_drawer))
//                .onChildView(withText(recentFilesText)).perform(click());

//        onData(withClassName(endsWith("DrawerAdapter")))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .perform(click());


//                .onChildView(withText(recentFilesText)).perform(click());

    }
}
