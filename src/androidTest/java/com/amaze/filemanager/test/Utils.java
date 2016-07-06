package com.amaze.filemanager.test;

import android.support.test.espresso.contrib.DrawerActions;

import com.amaze.filemanager.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
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
}
