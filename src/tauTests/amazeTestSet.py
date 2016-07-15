# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils

class FileAndFolderManipulationTests(UITestCase):
    
    def setUp(self):
        global folderName1
        global testingFolderName
        global amazeTestingFolder
        global fileName
        
        testingFolderName = TestDataSource.generalTestFolderName
        folderName1 = TestDataSource.folderName1
        amazeTestingFolder = TestDataSource.amazeTestFolderName
        fileName = TestDataSource.textFileName
        launch.activity('com.amaze.filemanager', '.activities.MainActivity')
        
        Utils.navigateToTestFolder()
        Utils.createFolderWithName(amazeTestingFolder)
        tap.text(amazeTestingFolder)
        
    def tearDown(self):
        #packages.clearData('com.amaze.filemanager')
        #remove the folder
        Utils.navigateToTestFolder()
        tap.long.text(amazeTestingFolder)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        
        
        
        
    @testCaseInfo('<Add a new folder>', deviceCount=1)
    def testCreateNewFolderAndDeleteIt(self):
      
        #create the file
        #Utils.createFolderWithName(folderName1)
        #for some reason the way it is done in utils does not work!
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName1)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert it is visible
        exists.text(folderName1)
        
        #delete the file
        tap.long.text(folderName1)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        #assert it is gone
        exists.no.text(folderName1)
        
    @testCaseInfo('<Add a new file>', deviceCount=1)
    def testCreateNewFileAndDeleteIt(self):
        
        #Utils.createFileWithName(fileName)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(fileName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert file is visible
        exists.text(fileName)
        
        #delete the file
        tap.long.text(fileName)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        #assert it is gone
        exists.no.text(fileName)
        
        
        