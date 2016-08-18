package com.amaze.filemanager.test.TestClasses;

import com.amaze.filemanager.test.Utilities.Utils;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

import static junit.framework.Assert.fail;

public class DrawerRotationTests extends BaseAppiumTest{

    private String quickAccessText = "Quick Access";
    private String settingsText = "Settings";

    @Test
    public void testDrawerOpenRotateScreen(){

        try{
            Utils.openDrawer(driver);

            //assert couple elements are visible
            assertViewsAreVisible();

            //rotate screen
            driver.rotate(ScreenOrientation.LANDSCAPE);

            //assert the elements are still visible
            assertViewsAreVisible();

            //rotate again
            driver.rotate(ScreenOrientation.PORTRAIT);

            //assert the views are still visible
            assertViewsAreVisible();

        }
        catch(Exception e){
            takeScreenshot("failure_" + System.currentTimeMillis());
            throw e;
        }
    }

    private void assertViewsAreVisible() {
        Utils.searchInVisibleListWithName(driver, quickAccessText);
        if(Utils.findElementsByName(driver, settingsText).isEmpty()){
            fail("The element named " + settingsText  + " should be visible");
        }
    }


}
