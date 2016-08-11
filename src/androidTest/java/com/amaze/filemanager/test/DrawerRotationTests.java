package com.amaze.filemanager.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DrawerRotationTests extends BaseUIAutomatorTest{

    private String quickAccessText, settingsText;

    @Before
    public void setUp(){
        quickAccessText = InstrumentationRegistry.getTargetContext().getString(R.string.quick);
        settingsText = InstrumentationRegistry.getTargetContext().getString(R.string.setting);
    }

    @After
    public void tearDown() throws Exception{
        device.unfreezeRotation();
    }

    @Test
    public void testDrawerOpenRotateScreen() throws Exception{

        Utils.openDrawer(device);

        //assert some elements are visible
        assertElementsDisplayed();

        //rotate screen
        device.setOrientationLeft();

        //assert some elements still visible
        assertElementsDisplayed();

        //rotate screen
        device.setOrientationNatural();

        //assert elements still visible
        assertElementsDisplayed();
    }

    private void assertElementsDisplayed() throws Exception{
        UiScrollable navDrawer = new UiScrollable(
                new UiSelector().resourceId("com.amaze.filemanager:id/menu_drawer"));
        assertTrue("Element " + quickAccessText + " should be visible",
                navDrawer.scrollTextIntoView(quickAccessText));
        assertTrue("Element " + settingsText + " should be visible",
                device.findObject(new UiSelector().text(settingsText)).exists());
    }
}