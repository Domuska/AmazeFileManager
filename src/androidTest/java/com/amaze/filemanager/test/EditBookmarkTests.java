package com.amaze.filemanager.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EditBookmarkTests extends BaseUIAutomatorTest{

    private String addToBookmark;
    private String generalTestFolderName = TestDataSource.generalTestFolderName;
    private String newTestFolderName = TestDataSource.newTestFolderName;
    private String amazeTestFolderName = TestDataSource.amazeTestFolderName;

    @Before
    public void setUp(){
        addToBookmark = InstrumentationRegistry.getTargetContext().getString(R.string.addtobook);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testEditBookmark(){
        
    }
}
