# -*- coding: utf-8 -*-

from Utilities import TestDataSource, Utils
class BookMarkTests(UITestCase):

    @testCaseInfo('<Test setting a bookmark>', deviceCount=1)
    def testBookmarking(self):
        """ Insert brief description of the test case

            1. Insert test step description here
        """
        log('Step1: Insert test step description')
        # Insert code to perform the first test step
        
        Utils.addFileToBookMarks("Testing")
        
        