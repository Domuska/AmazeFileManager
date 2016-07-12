package com.amaze.filemanager.test;

import android.preference.PreferenceManager;
import android.widget.TextView;

import com.amaze.filemanager.database.TabHandler;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import com.amaze.filemanager.R;

public class SetAsHomeTest extends BaseRobotiumTest{

    private String generalTestFolderName, quickAccessText, setAsHomeText;


    public void setUp() throws Exception{
        super.setUp();

        generalTestFolderName = TestDataSource.generalTestFolderName;
        quickAccessText = getActivity().getString(R.string.quick);
        setAsHomeText = getActivity().getString(R.string.setashome);

        Utils.navigateToTestFolder(solo);
    }

    public void tearDown() throws Exception{

        PreferenceManager.getDefaultSharedPreferences(
                getActivity().getApplicationContext())
                .edit().clear().commit();

        TabHandler.clearDatabase(getActivity().getApplicationContext());

        super.tearDown();
    }

    public void testSetHome(){

        Utils.openOverflowMenu(solo);

        //set this folder as home
        solo.clickOnText(setAsHomeText);
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));

        //navigate to somewhere else
        Utils.openDrawer(solo);
        solo.clickOnText(quickAccessText);

        //go back to home
        solo.clickOnView(solo.getView(R.id.home));

        //assert that we're at .../Testing folder
        TextView fullPath = (TextView)solo.getView(R.id.fullpath);
        String path = fullPath.getText().toString();
        assertTrue("Path does not end with  " + generalTestFolderName,
                path.endsWith(generalTestFolderName));
    }
}
