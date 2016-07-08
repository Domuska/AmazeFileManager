package com.amaze.filemanager.test;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.amaze.filemanager.*;
import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;

public class BookMarkTests {

    private String addToBookmark;
    private String generalTestFolderName;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        addToBookmark =
                myActivityRule.getActivity().getApplicationContext().getString(R.string.addtobook);
        generalTestFolderName = TestDataSource.generalTestFolderName;
    }

    //it is presumed that at this point there is .../Testing folder
    @Test
    public void testBookmarking(){

        //click on the overflow button with custom viewAction
        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(generalTestFolderName)),
                        clickOverflowAction.clickOverflowButton(com.amaze.filemanager.R.id.properties)
                ));

        onView(withText(addToBookmark)).perform(click());

        //assert the bookmark is visible
        Utils.openDrawer();
        onData(allOf(hasToString(generalTestFolderName), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(com.amaze.filemanager.R.id.menu_drawer))
                .check(matches(isDisplayed()));
    }

    private static class clickOverflowAction{

        public static ViewAction clickOverflowButton(final int id){
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on overflow button";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }
    }
}
