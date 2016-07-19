package com.amaze.filemanager.test;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amaze.filemanager.R;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import java.util.List;


public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName = TestDataSource.textFileName;
    private String folderName1 = TestDataSource.folderNames[0];
    private String folderName2 = TestDataSource.folderNames[1];
    private String copyText;

    public void setUp() throws Exception{
        super.setUp();
        copyText = getInstrumentation().getTargetContext().getString(R.string.copy);
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

    public void testCopyFileToAnotherFolder(){

        Utils.createFolderWithName(solo, folderName1);
        Utils.createFolderWithName(solo, folderName2);

        //add file to the folder
        solo.clickOnText(folderName1);
        Utils.createFileWithName(solo, fileName);

        //copy the file
        solo.waitForDialogToClose();
        List<ImageButton> views = solo.getCurrentViews(ImageButton.class);
        for(ImageButton button : views){
            if(button.getId() == R.id.properties)
                solo.clickOnView(button);
        }
        solo.clickOnText(copyText);
        solo.goBack();

        //paste the file to another folder
        solo.clickOnText(folderName2);
        solo.clickOnView(solo.getView(R.id.paste));

        //assert file is visible
        boolean fileFound = solo.searchText(fileName);
        assertTrue("File should be visible", fileFound);
    }

}
