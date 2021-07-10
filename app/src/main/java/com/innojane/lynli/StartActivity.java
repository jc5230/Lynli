package com.innojane.lynli;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // get the common element for the transition in this activity
        View logoImageView = findViewById(R.id.app_logo);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent loginIntent = new Intent(StartActivity.this, LoginActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(StartActivity.this,
                                logoImageView,
                                getString(R.string.activity_trans_logo));

                StartActivity.this.startActivity(loginIntent,options.toBundle());
            }
        }, SPLASH_DISPLAY_LENGTH);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StartActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH * 2);
    }
}