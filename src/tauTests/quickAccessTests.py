# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils

class QuickAccessFileTest(UITestCase):

    def setUp(self):
        
        global fileName, quickAccessText, amazeTestingFolder
        
        fileName = TestDataSource.movieFileName
        quickAccessText = "Quick Access"
        amazeTestingFolder = TestDataSource.amazeTestFolderName
        
        Utils.navigateToTestFolder()
        Utils.createFolderWithName(amazeTestingFolder)
        tap.text(amazeTestingFolder)
        
    def tearDown(self):
        #remove the testing folder
        Utils.navigateToTestFolder()
        tap.long.text(amazeTestingFolder)
        tap.resourceId('com.amaze.filemanager:id/delete')
        tap.resourceId('com.amaze.filemanager:id/buttonDefaultPositive')

    @testCaseInfo('<Quick access text>', deviceCount=1)
    def testOpenFileCheckRecents(self):
        
        self.createFileWithName(fileName)
        
        #open the file multiple times to add it to quick access
        self.openFileNavigateBack()
        self.openFileNavigateBack()
        self.openFileNavigateBack()
        self.openFileNavigateBack()
        
        #go to quick access
        Utils.openDrawer()
        tap.text(quickAccessText)
        
        #assert file name is visible here
        assert exists.text(fileName), \
        "file " + fileName + " should be visible"
     
    def openFileNavigateBack(self):
         tap.text(fileName)
         input.key.back()
         
    def createFileWithName(self, name):
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(name)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
       