package com.amaze.filemanager.test;

import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;

//import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FileAndFolderManipulationTests {

    private String fileName, storageText;

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
        new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        fileName = "my_recipes.txt";
        storageText = myActivityRule.getActivity().
                getApplicationContext().getResources().getString(R.string.storage);
//        R.string.storage;
    }


    @Test
    public void createNewFile(){

//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText(storageText)).perform(click());
    }

}
