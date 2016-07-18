# -*- coding: utf-8 -*-

from Utilities import TestDataSource, Utils
class BookMarkTests(UITestCase):
    
    def setUp(self):
        global generalTestFolderName
        generalTestFolderName = TestDataSource.generalTestFolderName
        
        reactor.add("Permission Reactor", self.allowPermissions, text="Allow")
        launch.activity('com.amaze.filemanager', '.activities.MainActivity', verify=False)
        
    def allowPermissions(self):
        tap.resourceId("com.android.packageinstaller:id/permission_allow_button")

    @testCaseInfo('<Test setting a bookmark>', deviceCount=1)
    def testBookmarking(self):
        
        #add file to bookmarks
        Utils.addFileToBookMarks(generalTestFolderName)
        
        #assert the bookmark is visible
        Utils.openDrawer()
        assert exists.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer"), \
        "bookmark " + generalTestFolderName + " is not visible"
        
        #remove the bookmark
        tap.long.text(generalTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultNegative")
        
        #assert the bookmark is gone
        log("assert bookmark is gone")
        
        #itemFound = False
        #get row items from both nav drawer and main list 
        #rowTexts = get.items.resourceId("com.amaze.filemanager:id/firstline_nav_drawer")
        #for row in rowTexts:
         #   if row.Text            
            #if exists.text(generalTestFolderName):
                #see if the underlying list's overflow button is inside that element
                #if not exists.resourceId("com.amaze.filemanager:id/properties", area = row):
                  #  itemFound = True
            
        #assert exists.no.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer"), \
        #"bookmark " + generalTestFolderName + " should not be visible"
        
        #item = get.item.text(generalTestFolderName, resourceId="com.amaze.filemanager:id/firstline_nav_drawer")
        wait(2000)
        #this does not work because both of the row items in nav drawer
        #and in main fragment use same row layout as basis
        item = get.item.resourceId("com.amaze.filemanager:id/firstline_nav_drawer", text=generalTestFolderName)
        #if item is not None:
        log('text:' + item.Text)
        log('text:' + item.ResourceId)
        #log('item: ' + item.Attributes)
        if generalTestFolderName in item.Text:
            fail("bookmark should not be visible")
        
        