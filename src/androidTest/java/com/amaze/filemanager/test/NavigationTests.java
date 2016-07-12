package com.amaze.filemanager.test;

import android.graphics.Point;
import android.view.Display;

import com.amaze.filemanager.R;
import com.amaze.filemanager.test.Utilities.Utils;

public class NavigationTests extends BaseRobotiumTest{


    private String recentFilesText, videosText;
    private String gridViewText, listViewText;

    public void setUp() throws Exception{
        super.setUp();
        recentFilesText = getInstrumentation().getTargetContext().getString(R.string.recent);
        videosText = getInstrumentation().getTargetContext().getString(R.string.videos);

    }

    public void testSwipingBetweenFolders(){
        Utils.swipeToLeftScreen(solo);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        size.x;
        size.y;
    }
}
