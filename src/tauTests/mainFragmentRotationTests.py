# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils
class MainFragmentRotationTests(UITestCase):

    def setUp(self):
        global fileName, folderName, amazeTestingFolder
        fileName = TestDataSource.textFileName
        folderName = TestDataSource.folderName1
        amazeTestingFolder = TestDataSource.amazeTestFolderName
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
        Utils.navigateToTestFolder()
        Utils.createFolderWithName(amazeTestingFolder)
        tap.text(amazeTestingFolder)
        
    def tearDown(self):
        #remove the folder
        Utils.navigateToTestFolder()
        tap.long.text(amazeTestingFolder)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")
       
    @testCaseInfo('<Rotate in main fragment>', deviceCount=1)
    def testRotateMainFragment(self):
        log('Step1: Insert test step description')
        
        self.createFolderWithName(folderName)
        self.createFileWithName(fileName)
        
        #rotate screen
        orientation.left()        
        
        #assert file and folder still visible
        assert exists.text(folderName), "folder should be visible"
        assert exists.text(fileName), "file should be visible"
        
        #rotate screen again
        orientation.portrait()
        
        #assert file and folder still visible
        assert exists.text(folderName), "folder should be visible"
        assert exists.text(fileName), "file should be visible"
        
        
    def createFolderWithName(self, folderName):
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item")
        input.text(folderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
    def createFileWithName(self, name):
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(name)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        