# -*- coding: utf-8 -*-
from Utilities import TestDataSource, Utils
class DrawerRotationTests(UITestCase):

    def setUp(self):
        global quickAccessText, settingsText
        quickAccessText = "Quick Access"
        settingsText = "Settings"
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
    def tearDown(self):
        packages.clearData('com.amaze.filemanager')
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")

    @testCaseInfo('<rotate screen in nav drawer>', deviceCount=1)
    def testDrawerOpenRotateScreen(self):
        
        wait(500)
        #Open drawer
        Utils.openDrawer()
        
        #assert some elements are visible
        self.assertElementsDisplayed()
        
        #rotate screen
        orientation.left()
        
        #assert some elements are visible
        self.assertElementsDisplayed()
        
        #rotate screen
        orientation.portrait()
        
        #assert some elements are visible
        self.assertElementsDisplayed()
        
    def assertElementsDisplayed(self):
        
        navList = get.item.resourceId("com.amaze.filemanager:id/menu_drawer")
        navDrawer = get.item.resourceId("com.amaze.filemanager:id/left_drawer")        
        quickFound = find.text(quickAccessText, area = navList, direction="vertical")
        settingsFound = find.text(settingsText, area = navDrawer, direction="vertical")
        
        #assert quickFound, quickAccessText + " should be visible"
        #assert settingsFound, settingsText + " should be visible"
        verify.text(quickAccessText)
        verify.text(settingsText)
    