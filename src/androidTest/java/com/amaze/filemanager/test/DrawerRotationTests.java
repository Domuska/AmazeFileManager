package com.amaze.filemanager.test;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.Utils;
import com.robotium.solo.Solo;

public class DrawerRotationTests extends BaseRobotiumTest{

    private String quickAccessText, settingsText;

    public void setUp() throws Exception{
        super.setUp();
        quickAccessText = solo.getCurrentActivity().getString(R.string.quick);
        settingsText = solo.getCurrentActivity().getString(R.string.setting);
    }

    public void testDrawerOpenRotateScreen(){

        Utils.openDrawer(solo);

        //assert some views are visible
        assertElementsDisplayed();

        //rotate
        solo.setActivityOrientation(Solo.LANDSCAPE);

        assertElementsDisplayed();

        //rotate
        solo.setActivityOrientation(Solo.PORTRAIT);

        assertElementsDisplayed();

    }

    private void assertElementsDisplayed(){
        Utils.isElementFoundInDrawer(solo, quickAccessText);
        Utils.isElementFoundInDrawer(solo, settingsText);
    }
}
