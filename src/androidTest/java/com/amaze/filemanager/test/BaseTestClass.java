package com.amaze.filemanager.test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.junit.After;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;

public class BaseTestClass {

    private String storageText, generalTestFolderName, amazeTestFolderName, sdcardText;


    @Before
    public void setUpTestingFolder(){

        storageText = "Storage";
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;
        sdcardText = "sdcard";

        Utils.navigateToTestFolder(generalTestFolderName);

        Utils.createFolderWithName(amazeTestFolderName);
        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

//        Utils.navigateToTestFolder(generalTestFolderName);
//        Utils.openDrawer();
//        onData(allOf(hasToString(storageText), is(instanceOf(EntryItem.class))))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .perform(scrollTo());

//        onData(allOf(hasToString("Download"), is(instanceOf(EntryItem.class))))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .perform(click());

//        onData(hasDescendant(allOf(withId(R.id.firstline), withText("Storage"))))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .perform(scrollTo());

//        onView(withText(storageText)).perform(click());

        //remove the testing folder

        //manual way
//        onView(withText(amazeTestFolderName)).perform(longClick());
//        onView(withId(R.id.delete)).perform(click());
//        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //using ADB shell command (API lvl 21+)
        InstrumentationRegistry.getInstrumentation().
                getUiAutomation().executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
    }
}
