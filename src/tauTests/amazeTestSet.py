# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils

class FileAndFolderManipulationTests(UITestCase):
    
    def setUp(self):
        global newFolderName
        global testingFolderName
        
        testingFolderName = TestDataSource.generalTestFolderName
        newFolderName = TestDataSource.folderName1
        launch.activity('com.amaze.filemanager', '.activities.MainActivity')
        
        
    #def tearDown(self):
        #packages.clearData('com.amaze.filemanager')
        
        
    @testCaseInfo('<Add a new folder>', deviceCount=1)
    def testCreateNewFolderAndDeleteIt(self):
        log('we should print stuff')
        #tap.text(testingFolderName, direction='vertical')
        #Utils.openDrawer()
        Utils.navigateToTestFolder()
        Utils.createFolderWithName(newFolderName)
        tap.text(newFolderName)
        
        