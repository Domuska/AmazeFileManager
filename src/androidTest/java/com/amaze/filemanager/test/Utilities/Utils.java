package com.amaze.filemanager.test.Utilities;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amaze.filemanager.R;
import com.robotium.solo.Solo;

import org.w3c.dom.Text;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Utils {

    private static String generalTestFolderName = TestDataSource.generalTestFolderName;
    private static String addToBookmark = "Add to Bookmark";

    public static void createFolderWithName(Solo solo, String folderName) {
        openFabMenu(solo);

        //create the folder
        solo.clickOnView(solo.getView(R.id.menu_item));
        solo.typeText( (EditText)solo.getView(android.R.id.input), folderName);
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));
    }

    public static void createFileWithName(Solo solo, String fileName){
        openFabMenu(solo);

        //create the file
        solo.clickOnView(solo.getView(R.id.menu_item1));
        solo.typeText( (EditText)solo.getView(android.R.id.input), fileName);
        solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));
    }

    private static void openFabMenu(Solo solo){

        //a very inefficient way to search the FAB, but it has no unique identifiers
        //cant find the view based on its' siblings like in Appium or Espresso
        ViewGroup menuView = (ViewGroup)solo.getView(R.id.menu);

        for(int i = 0; i < menuView.getChildCount(); i++){
            View view = menuView.getChildAt(i);
            if(view instanceof ImageView && view.getId() == View.NO_ID) {
                solo.clickOnView(view);
                break;
            }
        }

    }

    public static void navigateToTestFolder(Solo solo) {
        solo.clickOnView(solo.getView(R.id.home));
        solo.clickOnText(generalTestFolderName);
    }


    public static void swipeToLeftScreen(Solo solo){

        Display display = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        float startX = size.x/3;
        float startY = size.y/2;
        Double point = size.x/1.5;
        float endX = point.floatValue();
        float endY = startY;
        solo.drag(startX, endX, startY, endY, 3);

//        PointF startPoint = new PointF(startX, startY);
//        PointF endPoint = new PointF(endX, endY);
//
//        solo.swipe(startPoint, startPoint, endPoint, endPoint);

    }

    public static void swipeToRightScreen(Solo solo){
        Display display = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Double point = size.x/1.5;
        float startX = point.floatValue();
        float startY = size.y/2;
        float endX = size.x/3;
        float endY = startY;

        solo.drag(startX, endX, startY, endY, 3);

    }

    public static void openDrawer(Solo solo){
        Point deviceSize = new Point();
        solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);

        int screenWidth = deviceSize.x;
        int screenHeight = deviceSize.y;
        int fromX = 0;
        int toX = screenWidth / 2;
        int fromY = screenHeight / 2;
        int toY = fromY;

        solo.drag(fromX, toX, fromY, toY, 4);
    }

    public static void openOverflowMenu(Solo solo){
        /*not the best way to do this, but since commands like
        solo.clickOnMenuItem();
        or
        solo.sendKey(Solo.MENU);
        don't work, this seems to be the only option
        */
        List<View> views = solo.getViews();
        for(View view : views){
            if(view.getContentDescription() != null &&
                    view.getContentDescription().toString().equals("More options")) {
                solo.clickOnView(view);
            }
        }
    }


    //this method will fail silently if the correct folder is not found, which is not nice
    public static void addFileToBookMarks(Solo solo, String name){


        solo.searchText(generalTestFolderName, true);
        solo.waitForText(generalTestFolderName);
        List<View> views = solo.getViews();

        //go through all the views to get the correct row
        for(View view : views){
            if(view instanceof RelativeLayout && view.getId() == R.id.second){
                //find the properties element inside the correct row and click it
                TextView folderName = (TextView)view.findViewById(R.id.firstline);
                if(folderName.getText().equals(name)) {
                    solo.clickOnView(view.findViewById(R.id.properties));
                    solo.clickOnText(addToBookmark);
                }
            }
        }
    }

}
