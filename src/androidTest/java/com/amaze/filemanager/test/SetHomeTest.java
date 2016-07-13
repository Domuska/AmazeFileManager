package com.amaze.filemanager.test;

import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;

import com.amaze.filemanager.database.TabHandler;

import org.junit.After;

public class SetHomeTest {

    @After
    public final void tearDown(){

        //clear database and preferences to remove the set home
        PreferenceManager.
                getDefaultSharedPreferences(
                        InstrumentationRegistry.getContext())
                .edit().clear().commit();

        TabHandler.clearDatabase(InstrumentationRegistry.getContext());
    }


}
