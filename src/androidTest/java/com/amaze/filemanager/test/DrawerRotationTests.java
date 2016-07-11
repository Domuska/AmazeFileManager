package com.amaze.filemanager.test;

import android.support.test.rule.ActivityTestRule;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.Utils;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.amaze.filemanager.test.Utilities.OrientationChangeAction.orientationLandscape;
import static com.amaze.filemanager.test.Utilities.OrientationChangeAction.orientationPortrait;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;

public class DrawerRotationTests {

    private String quickAccessText, settingsText;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        quickAccessText = myActivityRule.getActivity().getString(R.string.quick);
        settingsText = myActivityRule.getActivity().getString(R.string.setting);
    }

    @Test
    public void testDrawerOpenRotateScreen(){
        //open drawer
        Utils.openDrawer();

        //assert some elements are visible
        assertElementsDisplayed();

        //rotate screen
        onView(isRoot()).perform(orientationLandscape());

        //assert same views are still visible
        assertElementsDisplayed();

        //rotate screen
        onView(isRoot()).perform(orientationPortrait());

        //assert views are still visible
        assertElementsDisplayed();
    }

    private void assertElementsDisplayed() {
        onData(allOf(hasToString(quickAccessText), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(R.id.menu_drawer))
                .check(matches(isDisplayed()));
        onView(withText(settingsText)).check(matches(isDisplayed()));
    }
}
