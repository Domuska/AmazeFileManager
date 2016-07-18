# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils

class QuickAccessFileTest(UITestCase):

    def setUp(self):
        
        global fileName, quickAccessText, amazeTestingFolder
        
        fileName = TestDataSource.movieFileName
        quickAccessText = "Quick Access"
        amazeTestingFolder = TestDataSource.amazeTestFolderName
        
        launch.activity('com.amaze.filemanager', '.activities.MainActivity')
        
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
         #wait for the video player to start up
         #this is not the way we'd do it in a real situation but here
         #it's fine, we wait for the only video player to show up
         #(in real situation we'd maybe handle a different video player)
         exists.resourceId("com.google.android.apps.photos:id/video_player_controller_fragment_container")
         input.key.back()
         
    def createFileWithName(self, name):
        tap.instanceOf('android.widget.ImageView', index=2)
        tap.resourceId("com.amaze.filemanager:id/menu_item1")
        input.text(name)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
       