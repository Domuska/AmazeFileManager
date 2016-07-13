package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;
import com.robotium.solo.Solo;

public class MainFragmentRotationTests extends BaseTestClass{

    private String fileName = TestDataSource.textFileName;
    private String folderName = TestDataSource.folderNames[0];

    public void testRotateMainFragment(){

        Utils.createFileWithName(solo, fileName);
        Utils.createFolderWithName(solo, folderName);

        //rotate the screen
        solo.setActivityOrientation(Solo.LANDSCAPE);

        //assert file and folder still visible
        assertFileAndFolderFound();

        //rotate again
        solo.setActivityOrientation(Solo.PORTRAIT);

        //assert file and folder still visible
        assertFileAndFolderFound();
    }

    private void assertFileAndFolderFound() {
        boolean fileFound = solo.searchText(fileName);
        assertTrue("File " + fileName + " is not visible", fileFound);
        boolean folderFound = solo.searchText(folderName);
        assertTrue("Folder " + folderName + " is not visible", folderFound);
    }
}
