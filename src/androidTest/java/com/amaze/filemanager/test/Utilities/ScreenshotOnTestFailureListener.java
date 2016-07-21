package com.amaze.filemanager.test.Utilities;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.io.File;

import jcifs.util.DES;

//credit to Russel Collins' blog Everybody Tests
//http://www.everybodytests.com/2015/06/boom-screenshot-level-up-your-test.html

public class ScreenshotOnTestFailureListener extends RunListener{

    Description description;

    public void testStarted (Description description){
        this.description = description;
    }

    public void testFailure(){

        String name = String.format("%s.%s.png",
                description.getClassName(),
                description.getMethodName());

        File file = new File("/storage/emulated/0/Testing/Screenshots", name);

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file);
    }
}
