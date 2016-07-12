package com.amaze.filemanager.test;

import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;


public class FileAndFolderManipulationTests extends BaseTestClass {

    private String fileName = TestDataSource.textFileName;
    private String folderName1 = TestDataSource.folderNames[0];
    private String folderName2 = TestDataSource.folderNames[1];


    public void testCreateNewFileAndDeleteIt(){
        Utils.createFileWithName(solo, fileName);

        boolean fileFound = solo.searchText(fileName);
        assertTrue("File is not visible", fileFound);
    }
}
