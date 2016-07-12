package com.amaze.filemanager.test;

import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.amaze.filemanager.R;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;


public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName = TestDataSource.textFileName;
    private String folderName1 = TestDataSource.folderNames[0];
    private String folderName2 = TestDataSource.folderNames[1];
    private String cutText;

    public void setUp() throws Exception{
        super.setUp();
        cutText = getInstrumentation().getTargetContext().getString(R.string.copy);
    }



    public void testCreateNewFileAndDeleteIt(){
        Utils.createFileWithName(solo, fileName);

        //assert it is visible
        boolean fileFound = solo.searchText(fileName);
        assertTrue("File is not visible", fileFound);

        //delete the file
        solo.clickLongOnText(fileName);
        solo.clickOnView(solo.getView(R.id.delete));
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));

        //assert it is deleted
        fileFound = solo.searchText(fileName);
        assertFalse("File should be gone", fileFound);
    }

    public void testCreateNewFolderAndDeleteIt(){

        Utils.createFolderWithName(solo, folderName1);

        //assert folder is visible
        boolean folderFound = solo.searchText(folderName1);
        assertTrue("Folder should be visible", folderFound);

        //delete the folder
        solo.clickLongOnText(folderName1);
        solo.clickOnView(solo.getView(R.id.delete));
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));

        folderFound = solo.searchText(folderName1);
        assertFalse("Folder should be gone", folderFound);
    }

    //this test does not work. For some reason it is not possible to click on the properties.
    public void testCopyFileToAnotherFolder(){

        Utils.createFolderWithName(solo, folderName1);
        Utils.createFolderWithName(solo, folderName2);

        //add file to the folder
        solo.clickOnText(folderName1);
        Utils.createFileWithName(solo, fileName);

        //cut the file
        solo.clickOnView(solo.getView("com.amaze.filemanager:id/properties", 0));
//        solo.clickInRecyclerView(0, 0, R.id.properties);
//        RelativeLayout relativeLayout = (RelativeLayout)solo.getView(R.id.second, 0);
//
//        for(int i = 0; i < relativeLayout.getChildCount(); i++){
//            if(relativeLayout.getChildAt(i) instanceof ImageButton)
//                solo.clickOnView(relativeLayout.getChildAt(i));
//        }


        solo.clickOnText(cutText);

        solo.goBack();

        //paste the file to another folder
        solo.clickOnText(folderName2);
        solo.clickOnView(solo.getView(R.id.paste));

        //assert file is visible
        boolean fileFound = solo.searchText(fileName);
        assertTrue("File should be visible", fileFound);
    }

}
