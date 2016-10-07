# -*- coding: utf-8 -*-
from datetime import datetime, timedelta
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
        packages.clearData('com.amaze.filemanager')
        
        #FOLLOWING ROWS ONLY FOR TEST LOGGING PURPOSES, NOT PART OF TEST RUN
        #with help from
        #http://stackoverflow.com/questions/3096953/difference-between-two-time-intervals-in-python
        timeNow = datetime.now()
        hourNow = str(timeNow.hour)
        minuteNow = str(timeNow.minute)
        secondNow = str(timeNow.second)
        
        measurementPath = r"C:\Users\\Tomi\testAutomation\measurements\amaze\tau\\"
        #times with only 1 number cause problems when doing strptime, add 0
        if timeNow.hour < 10:
            hourNow = "0" + hourNow
        if timeNow.minute < 10:
            minuteNow = "0" + minuteNow
        if timeNow.second < 10:
            secondNow = "0" + secondNow
        
        #format test execution ending time
        endTime = hourNow + ":" + minuteNow + ":" + secondNow
        #read the start time from temp file
        tempFile = open(measurementPath + "temp.txt", "r")
        #strip extra newlines from the end of the strings
        startHour = tempFile.readline().rstrip()
        startMinute = tempFile.readline().rstrip()
        startSecond = tempFile.readline().rstrip()
        startTime = startHour + ":" + startMinute + ":" + startSecond
        log(str(startTime))
        log(str(endTime))
        
        FMT = '%H:%M:%S'
        #calculate the time it took to run the tests
        tDelta = datetime.strptime(endTime, FMT) - datetime.strptime(startTime, FMT)
        #handle the situation if day has changed between tests
        if tDelta.days < 0:
            tDelta = timedelta(days = 0,
                        seconds = tDelta.seconds, microseconds = tDelta.microseconds)
        log(tDelta.total_seconds())
        
        #write the test execution time to .csv file
        testName = "2016_10_7-12_14" + ".csv"
        filename = "tau_tests-"
        filename += testName
        log("opening .csv")
        csvFile = open(measurementPath + filename, "a")
        log("writing to csv")
        
        csvFile.write("\n" + str(tDelta.total_seconds()))
        csvFile.close()
        
        
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
        