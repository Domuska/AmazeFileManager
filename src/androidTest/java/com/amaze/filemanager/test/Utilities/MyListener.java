package com.amaze.filemanager.test.Utilities;

import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.io.IOException;



//credit to Russel Collins' blog Everybody Tests
//http://www.everybodytests.com/2015/06/boom-screenshot-level-up-your-test.html

public class MyListener extends RunListener{

    Description description;

    @Override
    public void testStarted (Description description){
        this.description = description;
    }

    @Override
    public void testFailure(Failure failure) throws IOException{

        String path =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Testing/screenshots/"
                        + "fail_UiAutomator_" + System.currentTimeMillis() + ".png";

        File file = new File(path);
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        String path =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Testing/screenshots/"
                        + "fail_UiAutomator_" + System.currentTimeMillis() + ".png";

        File file = new File(path);
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file);
    }
}
