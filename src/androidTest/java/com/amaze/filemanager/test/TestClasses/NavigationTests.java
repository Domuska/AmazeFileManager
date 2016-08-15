package com.amaze.filemanager.test.TestClasses;

import android.widget.TextView;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.robotium.solo.Solo;

public class NavigationTests extends BaseRobotiumTest{


    private String storageText, recentFilesText, videosText;
    private String gridViewText, listViewText;
    private String generalTestFolderName = TestDataSource.generalTestFolderName;

    public void setUp() throws Exception{
        super.setUp();
        recentFilesText = getInstrumentation().getTargetContext().getString(R.string.recent);
        videosText = getInstrumentation().getTargetContext().getString(R.string.videos);
        storageText = getInstrumentation().getTargetContext().getString(R.string.storage);

        gridViewText = getInstrumentation().getTargetContext().getString(R.string.gridview);
        listViewText = getInstrumentation().getTargetContext().getString(R.string.listview);

        Utils.openDrawer(solo);
        solo.waitForView(solo.getView(R.id.menu_drawer));
        solo.clickOnText(storageText);
        solo.waitForDialogToClose();
    }

    public void testSwipingBetweenFolders(){

        //make sure we're on the leftmost screen
        Utils.swipeToLeftScreen(solo);

        //navigate this viewPager into the videos
        Utils.openDrawer(solo);
        solo.clickOnText(videosText);

        //swipe to the right viewPager
        Utils.swipeToRightScreen(solo);

        //open recent files
        Utils.openDrawer(solo);
        solo.clickOnText(recentFilesText);

        //assert the viewPager filepaths include the folder that was navigated into
        Utils.swipeToLeftScreen(solo);
        TextView filePath = (TextView)solo.getView(R.id.fullpath);
        assertTrue("File path does not include " + videosText,
                filePath.getText().toString().contains(videosText));


        Utils.swipeToRightScreen(solo);
        filePath = (TextView)solo.getView(R.id.fullpath);
        assertTrue("File path does not include " + recentFilesText,
                filePath.getText().toString().contains(recentFilesText));

    }

    //for some reason this test fails since Robotium does not scroll all the way down with the
    //grid layout mode active, issue submitted, see Github
    //also, this recyclerview is defined in fragments/Main
    public void testGridView(){
        Utils.swipeToRightScreen(solo);

        //assert .../Testing folder is visible
        assertGeneralTestingFolderVisible();

        //switch to grid layout
        Utils.openOverflowMenu(solo);
        solo.clickOnText(gridViewText);
        assertGeneralTestingFolderVisible();

        //switch back to list layout
        Utils.openOverflowMenu(solo);
        solo.clickOnText(listViewText);
        assertGeneralTestingFolderVisible();
    }

    private void assertGeneralTestingFolderVisible() {
//        solo.scrollRecyclerViewToBottom(0);
        boolean testFolderFound = solo.searchText(generalTestFolderName);
        assertTrue("Folder " + generalTestFolderName + " should be visible", testFolderFound);
    }
}
