package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.amaze.filemanager.test.Utilities.Utils.withAdaptedData;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;

public class BookMarkTests extends BaseEspressoTestClass{

    private String generalTestFolderName;

    @Before
    public void initStrings(){
        generalTestFolderName = TestDataSource.generalTestFolderName;
    }

    //it is presumed that at this point there is .../Testing folder
    @Test
    public void testBookmarking(){

        //click on the overflow button with custom viewAction
        Utils.addFileToBookMarks(generalTestFolderName);

        //assert the bookmark is visible
        Utils.openDrawer();
        onData(allOf(hasToString(generalTestFolderName), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(com.amaze.filemanager.R.id.menu_drawer))
                .check(matches(isDisplayed()));

        //remove the bookmark
        onData(allOf(hasToString(generalTestFolderName), is(instanceOf(EntryItem.class))))
                .inAdapterView(withId(R.id.menu_drawer))
                .perform(longClick());
        onView(withId(R.id.buttonDefaultNegative)).perform(click());

        //assert it is no longer visible, use custom withAdaptedData (see Utils)
        onView(withId(R.id.menu_drawer))
                .check(matches(not(withAdaptedData(hasToString(generalTestFolderName)))));

    }
}
