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
    private static String storageText = "Storage";
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
        openDrawer(solo);
//        solo.clickOnText(storageText);
        solo.waitForDialogToOpen();
//        solo.waitForText(storageText);
        solo.clickOnText(storageText);
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
        solo.drag(startX, endX, startY, endY, 5);
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

        solo.drag(startX, endX, startY, endY, 5);

    }

    public static void openDrawer(Solo solo){
        solo.clickOnActionBarHomeButton();
//        Point deviceSize = new Point();
//        solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);
//
//        int screenWidth = deviceSize.x;
//        int screenHeight = deviceSize.y;
//        int fromX = 0;
//        int toX = screenWidth / 2;
//        int fromY = screenHeight / 2;
//        int toY = fromY;
//
//        solo.drag(fromX, toX, fromY, toY, 4);
    }

    public static void openOverflowMenu(Solo solo){
        /*not the best way to do this, but since commands like
        solo.clickOnMenuItem(); or solo.sendKey(Solo.MENU);
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
        solo.searchText(name, true);
        solo.waitForText(name);

        //go through all imageButtons to get the right row's properties button, a bit hacky
        List<ImageButton> views = solo.getCurrentViews(ImageButton.class);
        for(ImageButton button : views){
            if(button.getId() == R.id.properties) {
                View parent = (View)button.getParent();
                if(((TextView)parent.findViewById(R.id.firstline))
                        .getText().toString().equals(name)) {

                    solo.clickOnView(button);
                }
            }
        }
        solo.clickOnText(addToBookmark);
    }

    //this way to do searching is dirty-ish, but it seems there is no way to search
    //in a specific list with Robotium. This way also would not work if the nav drawer list
    //was long enough. Could be made smarter but as a whole, bad idea.
    public static boolean isElementFoundInDrawer(Solo solo, String elementName) {
        boolean elementFound = false;
        int j = 0;

        do{
            //search if visible Views have the text we're searching for
            List<View> drawerElements = solo.getViews(solo.getView(R.id.menu_drawer));
            for(View view : drawerElements){
                if(view.getId() == R.id.firstline){
                    if(((TextView)view).getText().toString().equals(elementName))
                        elementFound = true;
                }
            }
            solo.scrollDownList(0);
            j++;
        }while(!elementFound && j < 4);

        return elementFound;
    }

}
