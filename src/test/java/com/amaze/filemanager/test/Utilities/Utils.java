package com.amaze.filemanager.test.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Utils {

    private static String generalTestingFolder = "Testing";

    public static void navigateToTestingFolder(AndroidDriver driver){

        //have to swipe down pathbar since some times it is not visible
        swipeDownInPathBar(driver);

        driver.findElement(By.id("com.amaze.filemanager:id/home")).click();
        clickInListWithName(driver, generalTestingFolder);

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

    public static void clickInListWithName(AndroidDriver driver, String elementName){

        //apparently this spell is the way to do it, according to
        // https://github.com/appium/java-client/issues/421
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" +
                ".instance(0)).scrollIntoView(new UiSelector()." +
                "textContains(\"" + elementName + "\").instance(0))").click();
    }

    //dis dont work yet, point is to click the allow permissions button
    public static void clickAllowAccess(AndroidDriver driver){
        driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
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

    private static void openFabMenu(AndroidDriver driver){
        driver.findElementById("com.amaze.filemanager:id/menu")
                .findElement(By.className("android.widget.ImageButton"))
                .click();
    }



}
