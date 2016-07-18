# -*- coding: utf-8 -*-

from Utilities import TestDataSource, Utils
class EditBookmarkTests(UITestCase):

    def setUp(self):
        global newTestFolderName, generalTestFolderName
        
        newTestFolderName = TestDataSource.newTestFolderName
        generalTestFolderName = TestDataSource.generalTestFolderName
        
    def tearDown(self):
        tap.long.text(newTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultNegative")

    @testCaseInfo('<Test editing a bookmark>', deviceCount=1)
    def testEditBookmark(self):
        
        log('Step1: Insert test step description')
        # Insert code to perform the first test step
        
        Utils.addFileToBookMarks(generalTestFolderName)
        Utils.openDrawer()
        
        #rename the test folder
        tap.long.text(generalTestFolderName)
        #tap.resourceId("com.amaze.filemanager:id/editText4")
        tap.long.text(generalTestFolderName)
        
        for x in xrange(1,11):
            input.key.delete()

        input.text(newTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #assert the new name is visible
        assert exists.text(newTestFolderName),\
        "bookmark with name " + newTestFolderName + " should be visible"
        