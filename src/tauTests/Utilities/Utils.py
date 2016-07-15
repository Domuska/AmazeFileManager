#import TestDataSource

global testingFolderName
testingFolderName = "Testing"

def createFolderWithName(folderName):
	openFabMenu()
	tap.resourceId("com.amaze.filemanager:id/menu_item")
	input.text(folderName)
	tap.resourceId("com.amaze.filemanager:id/buttonDefaultPositive")
	
    
def navigateToTestFolder():
    openDrawer()
    tap.text("Storage")
    tap.text(testingFolderName, direction='vertical')
    
def openDrawer():
    swipe.location((0, 0.5)).to.location((0.99, 0.5))
    
def openFabMenu():
    tap.instanceOf('android.widget.ImageView', area='com.amaze.filemanager:id/menu')
	