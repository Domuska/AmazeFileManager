# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils

class FileAndFolderManipulationTests(UITestCase):
    
    def setUp(self):
        global folderName1
        global folderName2
        global testingFolderName
        global amazeTestingFolder
        global fileName
        
        testingFolderName = TestDataSource.generalTestFolderName
        folderName1 = TestDataSource.folderName1
        folderName2 = TestDataSource.folderName2
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
        self.createFolderWithName(folderName1)
        
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
        self.createFileWithName(fileName)
        
        #assert file is visible
        exists.text(fileName)
        
        #delete the file
        tap.long.text(fileName)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        #assert it is gone
        exists.no.text(fileName)
        
        
    @testCaseInfo('<Copy a file to another folder>', deviceCount=1)
    def testCopyFileToAnotherFolder(self):
        self.createFolderWithName(folderName1)
        self.createFolderWithName(folderName2)
        
        #add file to the folder
        tap.text(folderName1)
        self.createFileWithName(fileName)
        
        #copy the file
        tap.resourceId("com.amaze.filemanager:id/properties")
        tap.text("Copy")
        
        input.key.back()
        Utils.swipeDownInPathBar()
        
        #paste the file to another folder
        tap.text(folderName2)
        tap.resourceId("com.amaze.filemanager:id/paste")
        
        #assert file is visible
        exists.text(fileName, wait=10000)
        
    
    def createFolderWithName(self, folderName):
        tap.instanceOf('android.widget.ImageView', index=1)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
    def createFileWithName(self, name):
        tap.instanceOf('android.widget.ImageView', index=1)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(name)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
    