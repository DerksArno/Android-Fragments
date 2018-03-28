package com.example.arnod.myfragmentapp;

import android.app.Activity;
import android.app.Fragment;
//import android.support.v4.app.Fragment;LET OP DEZE NIET GEBRUIKEN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by arnod on 28-Feb-18.
 */

public class PortraitActivity extends FragmentActivity { // Waarom niet gewoon Activity en setcontentview?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Geef alle intent extras mee via arguments
        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            DetailFragment details = new DetailFragment();
            details.setArguments(getIntent().getExtras());
            //getFragmentManager().beginTransaction().add(R.id.overviewFragmentSolo, details).commit();
        }
    }
}
