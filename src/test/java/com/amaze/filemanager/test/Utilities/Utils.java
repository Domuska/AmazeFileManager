package com.amaze.filemanager.test.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Utils {

    private static String generalTestingFolder = "Testing";

    public static void navigateToTestingFolder(AndroidDriver driver){
        //have to swipe up this pathBar since some times it is not visible
        WebElement pathBar = driver.findElementById("com.amaze.filemanager:id/pathbar");
        Dimension dimension = pathBar.getSize();
        Point middlePoint = new Point(dimension.getWidth()/2, dimension.getHeight()/2);
        Point targetPoint = new Point(middlePoint.getX(), middlePoint.getY() + dimension.getHeight());

        driver.swipe(middlePoint.getX(), middlePoint.getY(),
                targetPoint.getX(), targetPoint.getY(), 300);

        driver.findElement(By.id("com.amaze.filemanager:id/home")).click();
        clickInListWithName(driver, generalTestingFolder);

    }

    public static void clickInListWithName(AndroidDriver driver, String elementName){

        //apparently this spell is the way to do it, according to
        // https://github.com/appium/java-client/issues/421
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" +
                ".instance(0)).scrollIntoView(new UiSelector()." +
                "textContains(\"" + generalTestingFolder + "\").instance(0))").click();
    }

    //dis dont work yet
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

    private static void openFabMenu(AndroidDriver driver){
        driver.findElementById("com.amaze.filemanager:id/menu")
                .findElement(By.className("android.widget.ImageButton"))
                .click();
    }

}
