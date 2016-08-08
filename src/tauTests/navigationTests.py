# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils
import unittest

class NavigationTests(UITestCase):
    
    def setUp(self):
        global recentFilesText
        global videosText
        global gridViewText, listViewText, generalTestFolderName
        
        recentFilesText = "Recent Files"
        videosText = "Videos"
        gridViewText = "Grid View"
        listViewText = "List View"
        generalTestFolderName = TestDataSource.generalTestFolderName
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        #launch app with verify=False so when app launches it won't fail if
        #the app asks for permissions
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")
        
    def tearDown(self):
        packages.clearData('com.amaze.filemanager')

    @testCaseInfo('<swipe between two folders>', deviceCount=1)
    def testSwipingBetweenTwoFolders(self):
        
        #make sure we're on the left screen
        Utils.swipeToLeftScreen()
        
        #navigate this pager to videos
        Utils.openDrawer()
        tap.text(videosText, area="com.amaze.filemanager:id/menu_drawer")
        
        #swipe to other pager
        Utils.swipeToRightScreen()
        
        #navigate to recent files
        Utils.openDrawer()
        tap.text(recentFilesText, area="com.amaze.filemanager:id/menu_drawer")
        
        #make sure we can swipe between the folders
        Utils.swipeToLeftScreen()
        pathBar = get.item.resourceId("com.amaze.filemanager:id/fullpath")
        
        if videosText not in pathBar.Text:
            fail("text" + videosText + " not in path bar")
            
        Utils.swipeToRightScreen()
        pathBar = get.item.resourceId("com.amaze.filemanager:id/fullpath")
        
        if recentFilesText not in pathBar.Text:
            fail("text" + recentFilesText + " not in path bar")
            
    @testCaseInfo('<test grid view>', deviceCount=1)
    def testGridVIew(self):
        
        Utils.swipeToRightScreen()
        
        #assert .../Testing is visible
        self.assertGeneralTestingFolderVisible()
        
        #switch to grid layout
        Utils.openOverFlowMenu()
        tap.text(gridViewText)
        self.assertGeneralTestingFolderVisible()
        
        #switch back to list layout
        Utils.openOverFlowMenu()
        tap.text(listViewText)
        self.assertGeneralTestingFolderVisible()
        
        
        
    def assertGeneralTestingFolderVisible(self):
        verify.text(generalTestFolderName, direction="vertical", scroll = True)
        #assert find.text(generalTestFolderName, direction="vertical"), \
        #"file " + generalTestFolderName + " was not found"
        
        
        
        