package com.amaze.filemanager.test.TestClasses;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import com.amaze.filemanager.R;

import java.util.List;

public class EditBookmarkTests extends BaseRobotiumTest{

    private String generalTestFolderName = TestDataSource.generalTestFolderName;
    private String newTestFoldername = TestDataSource.newTestFolderName;

    public void setUp() throws Exception{
        super.setUp();
        solo.clickOnView(solo.getView(R.id.home));
    }

    public void tearDown() throws Exception{
        //remove the bookmark
        longClickFolderWithNameInDrawer(newTestFoldername);
        solo.clickOnView(solo.getView(R.id.buttonDefaultNegative));

        super.tearDown();
    }

    public void testEditBookmark(){

        Utils.addFileToBookMarks(solo, generalTestFolderName);
        Utils.openDrawer(solo);

        //rename the test folder
        longClickFolderWithNameInDrawer(generalTestFolderName);
        EditText editNameField = solo.getEditText(generalTestFolderName);
        solo.clearEditText(editNameField);
        solo.typeText(editNameField, newTestFoldername);
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));

        //assert the new name is visible
        Boolean bookmarkFound = Utils.isElementFoundInDrawer(solo, newTestFoldername);
        assertTrue("Bookmark with name " + newTestFoldername + " was not found in drawer", bookmarkFound);
    }

    private void longClickFolderWithNameInDrawer(String folderName){
        List<View> drawerViews = solo.getViews(solo.getView(com.amaze.filemanager.R.id.menu_drawer));
        for(View row : drawerViews){
            if(row.getId() == com.amaze.filemanager.R.id.firstline){
                if(((TextView)row).getText().toString().equals(folderName))
                    solo.clickLongOnView(row);
            }
        }
    }
}
