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
        boolean bookmarkFound = assertViewInList();

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
        bookmarkFound = assertViewInList();
        assertFalse("Folder/file " + generalTestingFolder + " should no longer be visible", bookmarkFound);
    }

    //this way to do searching is dirty-ish, but it seems there is no way to search
    //in a specific list with Robotium. This way also would not work if the nav drawer list
    //was long enough. Could be made smarter but as a whole, bad idea.
    private boolean assertViewInList(){
        boolean elementFound = false;
        elementFound = Utils.isElementFoundInDrawer(solo, generalTestingFolder);

        //if element was not found, scroll down and try searching again
        if(!elementFound) {
            solo.scrollDownList(0);
            elementFound = Utils.isElementFoundInDrawer(solo, generalTestingFolder);
        }
        return elementFound;
    }
}
