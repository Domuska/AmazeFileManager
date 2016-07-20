package com.amaze.filemanager.test.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Utils {

    private static String generalTestingFolder = "Testing";
    private static String overflowMenuDescription = "More options";
    private static String addToBookmark = "Add to Bookmark";
    private static String storageText = "Storage";

    public static void navigateToTestingFolder(AndroidDriver driver){

        openDrawer(driver);
        driver.findElementByName(storageText).click();
        searchInVisibleListWithName(driver, generalTestingFolder).click();
    }

    /**
     * Utility method for swiping down in path bar to display the top part of toolbar
     */
    public static void swipeDownInPathBar(AndroidDriver driver) {
        WebElement pathBar = driver.findElementById("com.amaze.filemanager:id/pathbar");
        Dimension dimension = pathBar.getSize();
        Point middlePoint = new Point(dimension.getWidth()/2, dimension.getHeight()/2);
        Point targetPoint = new Point(middlePoint.getX(), middlePoint.getY() + dimension.getHeight());

        driver.swipe(middlePoint.getX(), middlePoint.getY(),
                targetPoint.getX(), targetPoint.getY(), 300);

    }

    public static WebElement searchInVisibleListWithName(AndroidDriver driver, String elementName){

        //apparently this spell is the way to do it, according to
        // https://github.com/appium/java-client/issues/421
        return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" +
                ".instance(0)).scrollIntoView(new UiSelector()" +
                ".textContains(\"" + elementName + "\").instance(0))");

    }


    public static void createFileWithName(AndroidDriver driver, String fileName){
        openFabMenu(driver);

        //add the file
        driver.findElementById("com.amaze.filemanager:id/menu_item1").click();
        driver.findElementById("android:id/input").sendKeys(fileName);
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();
    }

    public static void createFolderWithName(AndroidDriver driver, String folderName){
        openFabMenu(driver);

        //add the folder
        driver.findElementById("com.amaze.filemanager:id/menu_item").click();
        driver.findElementById("android:id/input").sendKeys(folderName);
        driver.findElementById("com.amaze.filemanager:id/buttonDefaultPositive").click();
    }

    public static void swipeToLeftScreen(AndroidDriver driver){

        WebElement mainPager = driver.findElementById("com.amaze.filemanager:id/pager");

        Dimension dimension = mainPager.getSize();
        Point startPoint = new Point(dimension.getWidth()/3, dimension.getHeight()/2);

        Double targetPointX = dimension.getWidth()/1.5;
        Point targetPoint = new Point(targetPointX.intValue(), dimension.getHeight()/2);

        driver.swipe(startPoint.getX(), startPoint.getY(),
                targetPoint.getX(), targetPoint.getY(), 300);

    }

    public static void swipeToRightScreen(AndroidDriver driver){

        WebElement mainPager = driver.findElementById("com.amaze.filemanager:id/pager");
        Dimension dimension = mainPager.getSize();
        Double startX = dimension.getWidth()/1.5;
        Point startPoint = new Point(startX.intValue(), dimension.getHeight()/2);

        //to see an error caused by swiping use the commented command instead
        // Point targetPoint = new Point(dimension.getWidth(), dimension.getHeight()/2);
        Point targetPoint = new Point(dimension.getWidth()/3, dimension.getHeight()/2);

        driver.swipe(startPoint.getX(), startPoint.getY(),
                targetPoint.getX(), targetPoint.getY(), 300);
    }

    public static void openDrawer(AndroidDriver driver){
        swipeDownInPathBar(driver);
        driver.findElementByAccessibilityId("Navigate up").click();
    }

    public static void openOverflowMenu(AndroidDriver driver){
        driver.findElementByAccessibilityId(overflowMenuDescription).click();
    }

    public static void addFileToBookMarks(AndroidDriver driver, String folderName){
        searchInVisibleListWithName(driver, folderName);
//        TouchAction longPress = new TouchAction(driver);
//        longPress.longPress(folderToBeBookmarked, 2000).release().perform();

        List<WebElement> elements = driver.findElementsById("com.amaze.filemanager:id/second");
//        driver.findElementById("com.amaze.filemanager:id/second");

        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).findElement(By.id("com.amaze.filemanager:id/firstline")).getText().equals(folderName)){
                elements.get(i).findElement(By.id("com.amaze.filemanager:id/properties")).click();
                break;
            }
        }

        driver.findElementByName(addToBookmark).click();

    }

    private static void openFabMenu(AndroidDriver driver){
        driver.findElementById("com.amaze.filemanager:id/menu")
                .findElement(By.className("android.widget.ImageButton"))
                .click();
    }

}
