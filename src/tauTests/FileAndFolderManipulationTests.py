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
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
        Utils.navigateToTestFolder()
        #Utils.createFolderWithName(amazeTestingFolder)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(amazeTestingFolder)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        tap.text(amazeTestingFolder)
        
    def tearDown(self):
        packages.clearData('com.amaze.filemanager')
        #remove the testing folder
        shell('rm -r /storage/emulated/0/Testing/%s' % amazeTestingFolder)
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")
 
        
    @testCaseInfo('<Add a new folder>', deviceCount=1)
    def testCreateNewFolderAndDeleteIt(self):
      
        #create the folder
        #Utils.createFolderWithName(folderName1)
        #for some reason the way it is done in utils does not work!
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName1)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert it is visible
        verify.text(folderName1)
        
        #delete the file
        tap.long.text(folderName1)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        #assert it is gone
        verify.no.text(folderName1)
        
    @testCaseInfo('<Add a new file>', deviceCount=1)
    def testCreateNewFileAndDeleteIt(self):
        
        #create folder, doesnt work if helper method is used
        #Utils.createFileWithName(fileName)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(fileName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert file is visible
        #assert exists.text(fileName), \
        #"file " + fileName + " should be visible"
        verify.text(fileName)
        
        #delete the file
        tap.long.text(fileName)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
        #assert it is gone
        #assert exists.no.text(fileName), \
        #"file " + fileName + " should not be visible"
        verify.no.text(fileName)
        
        
    @testCaseInfo('<Copy a file to another folder>', deviceCount=1)
    def testCopyFileToAnotherFolder(self):
        #create folders, doesnt work if helper methods are used
        #self.createFolderWithName(folderName1)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName1)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #self.createFolderWithName(folderName2)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName2)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #add file to the folder
        tap.text(folderName1)
        #self.createFileWithName(fileName)
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(fileName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #copy the file
        tap.resourceId("com.amaze.filemanager:id/properties")
        tap.text("Copy")
        
        input.key.back()
        Utils.swipeDownInPathBar()
        
        #paste the file to another folder
        tap.text(folderName2)
        tap.resourceId("com.amaze.filemanager:id/paste")
        
        #assert file is visible
        #assert exists.text(fileName, wait=10000), \
        #"file " + fileName + " should be visible"
        verify.text(fileName, wait=10000)
        
    
    def createFolderWithName(self, folderName):
        tap.instanceOf('android.widget.ImageView')
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
    def createFileWithName(self, name):
        tap.instanceOf('android.widget.ImageView')
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(name)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
    