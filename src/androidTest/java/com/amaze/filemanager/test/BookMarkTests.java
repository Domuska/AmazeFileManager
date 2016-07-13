package com.amaze.filemanager.test;

import android.view.View;
import android.widget.TextView;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import com.amaze.filemanager.R;

import java.util.List;

public class BookMarkTests extends BaseRobotiumTest{

    private String generalTestingFolder = TestDataSource.generalTestFolderName;


    public void testBookMarking(){

        Utils.addFileToBookMarks(solo, generalTestingFolder);

        //assert the bookmark is visible
        Utils.openDrawer(solo);
        boolean bookmarkFound = Utils.isElementFoundInDrawer(solo, generalTestingFolder);

        assertTrue("Folder/file " + generalTestingFolder + " was not found in bookmarks", bookmarkFound);

        //remove the bookmark
        //can't just do solo.longClickText(generalTestingFolder) since it tries to click the
        //view behind the drawer -> wrong row is clicked in the nav drawer
        List<View> drawerViews = solo.getViews(solo.getView(R.id.menu_drawer));
        for(View row : drawerViews){
            if(row.getId() == R.id.firstline){
                if(((TextView)row).getText().toString().equals(generalTestingFolder))
                    solo.clickLongOnView(row);
            }
        }

        solo.waitForDialogToOpen();
        solo.clickOnView(solo.getView(R.id.buttonDefaultNegative));

        //assert bookmarked file/folder is no longer visible
        bookmarkFound = Utils.isElementFoundInDrawer(solo, generalTestingFolder);
        assertFalse("Folder/file " + generalTestingFolder + " should no longer be visible", bookmarkFound);
    }

}
