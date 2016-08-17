# -*- coding: utf-8 -*-

from Utilities import TestDataSource, Utils
class EditBookmarkTests(UITestCase):

    def setUp(self):
        global newTestFolderName, generalTestFolderName, storageText
        
        newTestFolderName = TestDataSource.newTestFolderName
        generalTestFolderName = TestDataSource.generalTestFolderName
        storageText = "Storage"
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        #launch app with verify=False so when app launches it won't fail if
        #the app asks for permissions
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        Utils.openDrawer()
        tap.text(storageText)
        
    def tearDown(self):
        tap.long.text(newTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultNegative")
        #packages.clearData('com.amaze.filemanager')
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")

    @testCaseInfo('<Test editing a bookmark>', deviceCount=1)
    def testEditBookmark(self):
        
        log('Step1: Insert test step description')
        # Insert code to perform the first test step
        
        Utils.addFileToBookMarks(generalTestFolderName)
        Utils.openDrawer()
        
        #rename the test folder
        tap.long.text(generalTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/editText4")
            
        #clear.text(generalFolderName)
        input.text.clear(generalTestFolderName)
        input.text(newTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert the new name is visible
        verify.text(newTestFolderName)
        