package com.amaze.filemanager.test;

import android.support.test.rule.ActivityTestRule;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DrawerRotationTests {

    private String quickAccessText, settingsText;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        quickAccessText = myActivityRule.getActivity().getString(R.string.quick);
        settingsText = myActivityRule.getActivity().getString(R.string.setting);
    }

    @Test
    public void testDrawerOpenRotateScreen(){
        //open drawer
        //assert some elements are visible
        //rotate screen
        //assert same views are still visible
        //rotate screen again
        //assert views are still visible
    }
}
