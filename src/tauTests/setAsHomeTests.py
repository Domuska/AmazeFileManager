# -*-acoding: utf-8 -*-
from Utilities import TestDataSource, Utils

class SetHomeTest(UITestCase):
    
    def setUp(self):
        
        global generalTestFolderName, setAsHomeText, quickAccessText
        
        generalTestFolderName = TestDataSource.generalTestFolderName
        setAsHomeText = "Set As Home"
        quickAccessText = "Quick Access"
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        #launch app with verify=False so when app launches it won't fail if
        #the app asks for permissions
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
        Utils.swipeToRightScreen()
        Utils.navigateToTestFolder()
        
    def tearDown(self):
        input.key.home()
        packages.clearData('com.amaze.filemanager')
        
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")
        #tap.text("Allow")

    @testCaseInfo('<Test setting folder as home>', deviceCount=1)
    def testSetHome(self):
        
        log('Step1: Insert test step description')
        
        #set this folder as home
        Utils.swipeDownInPathBar()
        Utils.openOverFlowMenu()
        tap.text(setAsHomeText)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
        
        #navigate to somewhere else
        Utils.openDrawer()
        tap.text(quickAccessText)
        
        #go back home
        tap.resourceId("com.amaze.filemanager:id/home")
        
        #assert we're at the .../Testing folder
        pathBar = get.item.resourceId("com.amaze.filemanager:id/fullpath")
        
        if generalTestFolderName not in pathBar.Text:
            fail("folder " + generalTestingFolder + " not in path bar")
        else:
            log("Test passed")
        