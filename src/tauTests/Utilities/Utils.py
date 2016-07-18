#import TestDataSource

global testingFolderName
testingFolderName = "Testing"

def createFolderWithName(folderName):
	openFabMenu()
	tap.resourceId("com.amaze.filemanager:id/menu_item")
	input.text(folderName)
	tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
	
def createFileWithName(fileName):
	openFabMenu()
	tap.resourceId("com.amaze.filemanager:id/menu_item1")
	input.text(fileName)
	tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
    
def navigateToTestFolder():
    openDrawer()
    tap.text("Storage")
    tap.text(testingFolderName, direction='vertical')
    
def openDrawer():
    swipe.location((0, 0.5)).to.location((0.99, 0.5))
    
def openFabMenu():
    tap.instanceOf('android.widget.ImageView', area='com.amaze.filemanager:id/menu')
	
def swipeDownInPathBar():
	swipe.resourceId("com.amaze.filemanager:id/pathbar").down(distance=0.1)
	
def swipeToLeftScreen():
	flick.resourceId("com.amaze.filemanager:id/pager").right()
	
def swipeToRightScreen():
	flick.resourceId("com.amaze.filemanager:id/pager").left()
	
def openOverFlowMenu():
	tap.description("More options")
	
def addFileToBookMarks(folderName):
	
	#find the testing text
	find.text(folderName, direction = "vertical")
	rowItems = get.items.resourceId("com.amaze.filemanager:id/second")
	#tap the overflow menu
	for i in rowItems:
		if exists.text(folderName, area = i):
			settingsRow = i
			tap.resourceId("com.amaze.filemanager:id/properties", area = settingsRow)
			
	