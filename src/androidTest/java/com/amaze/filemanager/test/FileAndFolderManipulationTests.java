package com.amaze.filemanager.test;

import com.amaze.filemanager.R;
import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.fragments.Main;
import com.amaze.filemanager.services.CopyService;
import com.amaze.filemanager.services.asynctasks.CopyFileCheck;
import com.amaze.filemanager.test.Utilities.TestDataSource;
import com.amaze.filemanager.test.Utilities.Utils;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FileAndFolderManipulationTests extends BaseTestClass{

    private String fileName, folderName1, folderName2, copyText;
    private IntentServiceIdlingResource intentServiceIdlingResource;


    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initStrings(){
        fileName = TestDataSource.textFileName;
        folderName1 = TestDataSource.folderNames[0];
        folderName2 = TestDataSource.folderNames[1];
        copyText = myActivityRule.getActivity().getString(R.string.copy);
    }

    @Before
    public void setUpIdlingResource(){
        Context context = InstrumentationRegistry.getTargetContext();
        intentServiceIdlingResource = new IntentServiceIdlingResource(context);
        Espresso.registerIdlingResources(intentServiceIdlingResource);
    }

    @After
    public void tearDown(){
        Espresso.unregisterIdlingResources(intentServiceIdlingResource);
    }

    @Test
    public void testCreateNewFileAndDeleteIt(){

        Utils.createFileWithName(fileName);
        //assert it is visible
        onView(withText(fileName)).check(matches(isDisplayed()));

        //delete the file
        onView(withText(fileName)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //assert it is deleted
        onView(withText(fileName)).check(doesNotExist());
    }

    @Test
    public void testCreateNewFolderAndDeleteIt(){

        Utils.createFolderWithName(folderName1);
        //assert folder is visible
        onView(withText(folderName1)).check(matches(isDisplayed()));

        //delete the folder
        onView(withText(folderName1)).perform(longClick());
        onView(withId(R.id.delete)).perform(click());
        onView(withId(R.id.buttonDefaultPositive)).perform(click());

        //assert the folder is deleted
        onView(withText(folderName1)).check(doesNotExist());

    }

    @Test
    public void testCopyFileToAnotherFolder(){

        Utils.createFolderWithName(folderName1);
        Utils.createFolderWithName(folderName2);

        //add file to the folder
        onView(withText(folderName1)).perform(click());
        Utils.createFileWithName(fileName);

        //copy the file
        onView(allOf(withId(R.id.properties), isDisplayed())).perform(click());
        onView(withText(copyText)).perform(click());

        Espresso.pressBack();

        //paste the file to another folder
        onView(withText(folderName2)).perform(click());
        onView(withId(R.id.paste)).perform(click());


        //assert file is visible
        onView(withText(fileName)).check(matches(isDisplayed()));
    }

    //helper class for handling the slow file copy task,
    // credit to http://blog.sqisland.com/2015/04/espresso-custom-idling-resource.html
    public class IntentServiceIdlingResource implements IdlingResource {
        private final Context context;
        private ResourceCallback resourceCallback;

        public IntentServiceIdlingResource(Context context) {
            this.context = context;
        }

        @Override
        public String getName() {
            return IntentServiceIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            boolean idle = !isIntentServiceRunning();
            if (idle && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }

        private boolean isIntentServiceRunning() {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo info : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (CopyService.class.getName().equals(info.service.getClassName())) {
                    return true;
                }
            }
            return false;
        }
    }

}
