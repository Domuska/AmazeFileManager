package com.amaze.filemanager.test;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class PermissionGranter {

    private UiDevice device;
    private final String AMAZE_PACKAGE = "com.amaze.filemanager";
    public static final int GENERAL_TIMEOUT = 5000;
    private int KEYCODE_HOME = 3;


    @Test
    public void grantPermissions(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //start up application
        String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());

        //wait for launcher
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                GENERAL_TIMEOUT);

        //launch app
        Context context = InstrumentationRegistry.getContext();
        Intent intent = context.getPackageManager().
                getLaunchIntentForPackage(AMAZE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //this would be the way to handle permissions. Would add 5s to every test run.
        device.wait(Until.hasObject(
                By.pkg("com.google.android.packageinstaller")), GENERAL_TIMEOUT);

        UiObject2 allowButton = device.findObject(By.res("com.android.packageinstaller:id/permission_allow_button"));
        if(allowButton != null)
            allowButton.click();

        device.pressKeyCode(KEYCODE_HOME);
    }

}
