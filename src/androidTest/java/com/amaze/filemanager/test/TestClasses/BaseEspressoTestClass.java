package com.amaze.filemanager.test.TestClasses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;

import com.amaze.filemanager.activities.MainActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.setFailureHandler;

public class BaseEspressoTestClass {

    private final String TAG = "BaseEspressoTestClass";

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public final void setUpBaseEspressoTestClass(){
        //set up custom failure handler to take screen shot on failure
        setFailureHandler(new CustomFailureHandler(InstrumentationRegistry.getTargetContext()));
    }

    @After
    public final void tearDownBaseEspressoTestClass(){
        setFailureHandler(new DefaultFailureHandler(InstrumentationRegistry.getTargetContext()));
    }

    public class CustomFailureHandler implements FailureHandler {
        private final FailureHandler delegate;

        public CustomFailureHandler(Context targetContext){
            delegate = new DefaultFailureHandler(targetContext);
        }

        @Override
        public void handle(Throwable error, Matcher<View> viewMatcher) {
            try{
                delegate.handle(error, viewMatcher);
            }
            catch(NoMatchingViewException e) {
                takeScreenshot("Failure_espresso_" + System.currentTimeMillis(), myActivityRule.getActivity());
                delegate.handle(error, viewMatcher);
            }
        }
    }

    //credit for the implementation of this method to
    // http://instantdevices.com/tech/tips-and-tricks-taking-screenshots-with-espresso-or-espresso-v2-0
    private void takeScreenshot(String name, Activity activity) {
        String path =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Testing/screenshots/" + name + ".png";

        View scrView = activity.getWindow().getDecorView().getRootView();
        scrView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(scrView.getDrawingCache());
        scrView.setDrawingCacheEnabled(false);


        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "fileNotFoundException");
        }
        catch (IOException e) {
            // exception
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (Exception e) {
                Log.d(TAG, "Exception in file writing:");
                e.printStackTrace();
            }
        }
    }

}
