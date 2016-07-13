package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.BaseUIAutomatorTest;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.After;
import org.junit.Before;

public class BaseTestClass extends BaseUIAutomatorTest{

    private String generalTestingFolderName = TestDataSource.generalTestFolderName;

    @Before
    public final void setUpBaseTestClass() throws Exception{
        Utils.navigateToTestFolder(device);
        Utils.createFolderWithName(device, generalTestingFolderName);

    }

    @After
    public final void tearDownBaseTestClass(){

    }
}
