package com.amaze.filemanager.test;

import android.test.ActivityInstrumentationTestCase2;

import com.amaze.filemanager.activities.MainActivity;
import com.robotium.solo.Solo;

import org.junit.Test;


public class BaseRobotiumTest extends ActivityInstrumentationTestCase2{

    protected Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME =
            "com.amaze.filemanager.MainActivity";

    public BaseRobotiumTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

    }

    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testStuff(){
        solo.clickOnText("Alarms");
    }
}
