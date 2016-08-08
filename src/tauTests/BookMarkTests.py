# -*- coding: utf-8 -*-

from Utilities import TestDataSource, Utils
class BookMarkTests(UITestCase):
    
    def setUp(self):
        global generalTestFolderName
        generalTestFolderName = TestDataSource.generalTestFolderName
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
    def tearDown(self):
        packages.clearData('com.amaze.filemanager')
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")

    @testCaseInfo('<Test setting a bookmark>', deviceCount=1)
    def testBookmarking(self):
        
        #add file to bookmarks
        Utils.addFileToBookMarks(generalTestFolderName)
        
        #assert the bookmark is visible
        Utils.openDrawer()
        #assert exists.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer"), \
        #"bookmark " + generalTestFolderName + " is not visible"
        verify.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer")
        
        #remove the bookmark
        tap.long.text(generalTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultNegative")
        
        #assert the bookmark is gone
        #this assertion sadly does not work since the text is visible behind the nav drawer and
        #Tau will pick it up
        verify.no.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer")
        
        
        