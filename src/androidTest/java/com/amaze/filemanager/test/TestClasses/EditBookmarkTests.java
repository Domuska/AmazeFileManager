package com.amaze.filemanager.test.TestClasses;

import android.support.test.rule.ActivityTestRule;

import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;

public class EditBookmarkTests{

    private String generalTestFolderName;
    private String newTestFolderName;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){
        generalTestFolderName = TestDataSource.generalTestFolderName;
        newTestFolderName = TestDataSource.newTestFolderName;

        onView(withId(R.id.home)).perform(click());
    }

    @After
    public void tearDown(){

        //remove the bookmarked file
        onView(withText(newTestFolderName)).perform(longClick());

        onView(withId(R.id.buttonDefaultNegative)).perform(click());
    }

    @Test
    public void testEditBookmark(){

        Utils.addFileToBookMarks(generalTestFolderName);
        Utils.openDrawer();

        //rename the test folder
        onData(allOf(hasToString(generalTestFolderName), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(com.amaze.filemanager.R.id.menu_drawer))
                .perform(longClick());
        onView(withId(R.id.editText4)).perform(clearText());
        onView(withId(R.id.editText4)).perform(typeText(newTestFolderName));
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //assert new name is visible
        onData(allOf(hasToString(newTestFolderName), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(com.amaze.filemanager.R.id.menu_drawer))
                .perform(longClick());
    }
}
