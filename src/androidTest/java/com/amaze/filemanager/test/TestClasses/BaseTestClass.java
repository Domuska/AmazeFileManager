package com.amaze.filemanager.test.TestClasses;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.util.Log;
import android.view.View;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.amaze.filemanager.ui.drawer.EntryItem;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.setFailureHandler;
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

public class BaseTestClass extends BaseEspressoTestClass{

    private String generalTestFolderName, amazeTestFolderName;

    @Before
    public void setupTestingFolder(){
        generalTestFolderName = TestDataSource.generalTestFolderName;
        amazeTestFolderName = TestDataSource.amazeTestFolderName;

        Utils.navigateToTestFolder(generalTestFolderName);

        Utils.createFolderWithName(amazeTestFolderName);
        onView(withText(amazeTestFolderName)).perform(click());
    }

    @After
    public void tearDownTestingFolder(){

        //using ADB shell command (API lvl 21+)
        InstrumentationRegistry.getInstrumentation().
                getUiAutomation().executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
    }
}
