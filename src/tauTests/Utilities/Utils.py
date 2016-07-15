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
	#swipe.resourceId("com.amaze.filemanager:id/generictext").right(0.9)
	flick.resourceId("com.amaze.filemanager:id/pager").right()
	
def swipeToRightScreen():
	#swipe.resourceId("com.amaze.filemanager:id/properties").left(0.9)
	flick.resourceId("com.amaze.filemanager:id/pager").left()
	