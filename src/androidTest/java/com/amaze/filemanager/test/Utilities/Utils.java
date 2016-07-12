package com.amaze.filemanager.test.Utilities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amaze.filemanager.R;
import com.robotium.solo.Solo;

public class Utils {

    private static String generalTestFolderName = TestDataSource.generalTestFolderName;

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


}
