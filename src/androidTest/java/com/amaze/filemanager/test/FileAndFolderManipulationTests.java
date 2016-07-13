package com.amaze.filemanager.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.test.ActivityInstrumentationTestCase2;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.BaseUIAutomatorTest;
import com.amaze.filemanager.test.Utilities.TestDataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName1, folderName2, copyText;

    @Before
    public void setUp(){

        Context contex = InstrumentationRegistry.getContext();
        fileName = TestDataSource.textFileName;
    }

    @Test
    public void testCreateNewFileAndDeleteIt(){
        //we do many things
    }


}
