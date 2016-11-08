package com.amaze.filemanager.test.TestClasses;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.hasToString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTests extends BaseEspressoTestClass{

    private String storageText, recentFilesText, videosText;
    private String gridViewText, listViewText;
    private String generalTestFolderName;

    @Before
    public void setUp(){
        storageText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(com.amaze.filemanager.R.string.storage);
        recentFilesText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(R.string.recent);
        videosText = myActivityRule.getActivity().getApplicationContext()
                .getResources().getString(R.string.videos);
        generalTestFolderName = TestDataSource.generalTestFolderName;

        gridViewText = myActivityRule.getActivity().getApplicationContext().getString(R.string.gridview);
        listViewText = myActivityRule.getActivity().getApplicationContext().getString(R.string.listview);

        Utils.openDrawer();
        onView(withText(storageText)).perform(click());
    }

    @Test
    public void testSwipingBetweenTwoFolders(){

        //make sure we're on the leftmost screen
        Utils.swipeToLeftScreen();

        //navigate one view into the images
        Utils.openDrawer();
        onData(hasToString(videosText))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(click());

        //swipe into the other list
        Utils.swipeToRightScreen();

        Utils.openDrawer();

        //open 'Recent Files'
        onData(hasToString(recentFilesText))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(click());

        Utils.swipeToLeftScreen();
        onView(withId(R.id.fullpath)).check(matches(allOf(isDisplayed(), withText(videosText))));
        Utils.swipeToRightScreen();
        onView(withId(R.id.fullpath)).check(matches(allOf(isDisplayed(), withText(recentFilesText))));
    }

    @Test
    public void testGridVIew(){
        Utils.swipeToRightScreen();

        //assert .../Testing is visible
        assertGeneralTestingFolderVisible();

        //switch to grid layout
        Utils.openOverflowMenu();
        onView(withText(gridViewText)).perform(click());
        assertGeneralTestingFolderVisible();

        //switch back to list layout
        Utils.openOverflowMenu();
        onView(withText(listViewText)).perform(click());
        assertGeneralTestingFolderVisible();
    }

    private void assertGeneralTestingFolderVisible() {
        onView(allOf(withId(R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText(generalTestFolderName))
                ));
    }
}
