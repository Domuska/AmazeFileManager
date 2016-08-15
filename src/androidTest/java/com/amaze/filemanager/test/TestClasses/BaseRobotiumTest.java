package com.amaze.filemanager.test.TestClasses;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.amaze.filemanager.R;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.robotium.solo.Solo;

import org.junit.Test;


public class BaseRobotiumTest extends ActivityInstrumentationTestCase2{

    protected Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME =
            "com.amaze.filemanager.MainActivity";

    protected String amazeTestFolderName = TestDataSource.amazeTestFolderName;

    public BaseRobotiumTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
        Utils.swipeToRightScreen(solo);
    }

    public void tearDown() throws Exception{
        //using ADB shell command (API lvl 21+) to remove folder
        InstrumentationRegistry.getInstrumentation().
                getUiAutomation().executeShellCommand("rm -r /storage/emulated/0/Testing/" + amazeTestFolderName);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
