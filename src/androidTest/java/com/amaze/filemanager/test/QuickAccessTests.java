package com.amaze.filemanager.test;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.view.KeyEvent;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.BaseTestClass;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

public class QuickAccessTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String quickAccessText;

    public void setUp() throws Exception{
        super.setUp();

        quickAccessText = getActivity().getString(R.string.quick);
    }

    //it would seem this test is not possible to do, since opening the file will launch an intent
    //and we will go outside of this app's scope, which is not possible with Robotium. If it was
    //possible to mock or stub the launched intent somehow it could be doable, but did not find
    //a way to do it
    public void estOpenFileCheckRecents(){
        Utils.createFileWithName(solo, fileName);

        //open the file multiple times so it is added to "Quick Access"
        openFileAndGoBack();
        openFileAndGoBack();
        openFileAndGoBack();
        openFileAndGoBack();

        //go to quick access
        Utils.openDrawer(solo);
        solo.clickOnText(quickAccessText);

        //assert file name is visible here
        boolean fileFound = solo.searchText(fileName);
        assertTrue("File is not visible in quick access", fileFound);
    }

    private void openFileAndGoBack(){
        solo.clickOnText(fileName);
        solo.sleep(500);
        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
    }
}
