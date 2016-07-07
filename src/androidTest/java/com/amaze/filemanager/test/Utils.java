package com.amaze.filemanager.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;

import com.amaze.filemanager.*;
import com.amaze.filemanager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;

public class Utils {

    public static void createFolderWithName(String name){
        openFabMenu();

        //create the folder
        onView(withId(com.amaze.filemanager.R.id.menu_item)).perform(click());
        onView(withId(android.R.id.input)).perform(typeText(name));
        onView(withId(com.amaze.filemanager.R.id.buttonDefaultPositive)).perform(click());
    }

    public static void createFileWithName(String name){

        openFabMenu();

        //add the file
        onView(withId(com.amaze.filemanager.R.id.menu_item1)).perform(click());
        onView(withId(android.R.id.input)).perform(typeText(name));
        onView(withId(com.amaze.filemanager.R.id.buttonDefaultPositive)).perform(click());
    }


    public static void openFabMenu() {
        //push the fab - the elements have no identifiers so have to use the withParent()
        onView(allOf(withParent(withId(com.amaze.filemanager.R.id.menu)), withClassName(endsWith("ImageView")), isDisplayed()))
                .perform(click());
    }

    public static void openDrawer(){
        onView(withId(com.amaze.filemanager.R.id.drawer_layout)).perform(DrawerActions.open());
    }

    public static void swipeToRightScreen(){
        onView(allOf(withId(com.amaze.filemanager.R.id.pager), isDisplayed()))
                .perform(swipeLeft());
    }

    public static void swipeToLeftScreen(){
        onView(allOf(withId(R.id.pager), isDisplayed()))
                .perform(swipeRight());
    }

    public static void navigateToTestFolder(String generalTestFolderName) {

//        onView(withText(storageText)).perform(click());

        onView(withId(R.id.home)).perform(click());

        //at some point home was set to be in the OS file structure, not in sdcard
//        onView(allOf(withId(R.id.listView), isDisplayed()))
//                .perform(RecyclerViewActions.actionOnItem(
//                        hasDescendant(withText("sdcard")), click()
//                ));


        //have to use the isDisplayed because there's actually two lists in the app open at this point
        //go to Testing folder in sdcard
        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(generalTestFolderName)), click()
                ));

    }
}
