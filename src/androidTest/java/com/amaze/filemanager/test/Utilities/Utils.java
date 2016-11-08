package com.amaze.filemanager.test.Utilities;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.amaze.filemanager.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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

    private static String addToBookmark = "Add to Bookmark";
    private static String storageText = "Storage";

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

    public static void navigateToTestFolder(String testFolderName) {

        openDrawer();
//        onData(allOf(hasToString(storageText), is(instanceOf(EntryItem.class))))
//                .inAdapterView(withId(R.id.menu_drawer))
//                .check(matches(isDisplayed()))
//                .perform(click());
        onView(withText(storageText)).perform(click());

        //have to use the isDisplayed because there's actually two lists in the app open at this point
        //go to Testing folder in sdcard
        onView(allOf(withId(com.amaze.filemanager.R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(testFolderName)), click()
                ));

    }

    //for checking data is not in the listview adapter
    //https://google.github.io/android-testing-support-library/docs/espresso/advanced/index.html
    public static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static void openOverflowMenu(){
        openActionBarOverflowOrOptionsMenu(
                InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    public static void addFileToBookMarks(String folderName) {
        onView(allOf(withId(R.id.listView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(folderName)),
                        clickOverflowAction.clickOverflowButton(R.id.properties)
                ));
        onView(withText(addToBookmark)).perform(click());
    }

    //for clicking the overflow button in the main fragment rows
    private static class clickOverflowAction{

        public static ViewAction clickOverflowButton(final int id){
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on overflow button";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }
    }
}
