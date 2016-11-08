# -*- coding: utf-8 -*-
from datetime import datetime, time
from Utilities import TestDataSource, Utils
class BookMarkTests(UITestCase):
    
    def setUp(self):
        #FOLLOWING ROWS ONLY FOR TEST LOGGING PURPOSES, NOT PART OF TEST
        log("write starting time to .csv for measurement purposes")
        timeNow = datetime.now()
        hourNow = str(timeNow.hour)
        minuteNow = str(timeNow.minute)
        secondNow = str(timeNow.second)
        
        if timeNow.hour < 10:
            hourNow = "0" + hourNow
        if timeNow.minute < 10:
            minuteNow = "0" + minuteNow
        if timeNow.second < 10:
            secondNow = "0" + secondNow
        
        #read time to temp file, used in the last test to calculate test execution time
        tempFile = open(r"C:\Users\\Tomi\testAutomation\measurements\amaze\tau\temp.txt", "w")
        tempFile.write(hourNow + "\n")
        tempFile.write(minuteNow + "\n")
        tempFile.write(secondNow)
        tempFile.close()
        #TEST CODE STARTS FROM HERE
        
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
        #a workaround for the fact that Tau will find the text even from behind nav drawer
        find.text("Alarms")
        
        #assert the bookmark is visible
        Utils.openDrawer()
        #assert exists.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer"), \
        #"bookmark " + generalTestFolderName + " is not visible"
        verify.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer")
        
        #remove the bookmark
        tap.long.text(generalTestFolderName)
        tap.resourceId("com.amaze.filemanager:id/buttonDefaultNegative")
        
        #assert the bookmark is gone
        #view has been scrolled away from the "testing" folder so this actually works
        verify.no.text(generalTestFolderName, area="com.amaze.filemanager:id/menu_drawer")
        
        
        