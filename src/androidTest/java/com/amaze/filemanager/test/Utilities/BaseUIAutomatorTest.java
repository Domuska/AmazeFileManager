package com.amaze.filemanager.test.Utilities;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.database.TabHandler;

import org.junit.After;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class BaseUIAutomatorTest {

    protected UiDevice device;
    protected final int GENERAL_TIMEOUT = 5000;
    private final String AMAZE_PACKAGE = "com.amaze.filemanager";

    @Before
    public final void setUpBaseUIAutomatorTest(){

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

        device.wait(Until.hasObject(By.pkg(AMAZE_PACKAGE).depth(0)), GENERAL_TIMEOUT);
    }


}
