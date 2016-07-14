package com.amaze.filemanager.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Before;
import org.junit.Test;

public class NavigationTests extends BaseUIAutomatorTest{

    private String storageText, recentFilesText, videosText;
    private String gridViewText, listViewText;
    private String generalTestFolderName;

    @Before
    public void setUp(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        storageText = context.getString(R.string.storage);
        recentFilesText = context.getString(R.string.recent);
        videosText = context.getString(R.string.videos);
    }

    @Test
    public void testSwipingBetweenFolders() throws Exception{

//        Utils.swipeToLeftScreen(device);
//
//        device.wait(Until.hasObject(By.res("android:id/input")),
//                BaseUIAutomatorTest.GENERAL_TIMEOUT);
        
        Utils.swipeToRightScreen(device);
        device.wait(Until.hasObject(By.res("android:id/input")),
                BaseUIAutomatorTest.GENERAL_TIMEOUT);
    }
}
