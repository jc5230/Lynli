package com.innojane.lynli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textUsernameLayout;
    private TextInputLayout textPasswordInput;
    private Button loginButton;
    View logoImageView;
    View loginFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsernameLayout = findViewById(R.id.textUsernameLayout);
        textUsernameLayout
                .getEditText()
                .addTextChangedListener(createTextWatcher(textUsernameLayout));
        
        textPasswordInput = findViewById(R.id.textPasswordInput);
        textPasswordInput
                .getEditText()
                .addTextChangedListener(createTextWatcher(textPasswordInput));
        
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.onLoginClicked();
            }
        });

        // get the common element for the transition in this activity - from LoginActivity to HomeActivity
        logoImageView = findViewById(R.id.app_logo_login);
        loginFrame = findViewById(R.id.login_frame);
    }

    private TextWatcher createTextWatcher(TextInputLayout textLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
                // not needed
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                textLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // not needed
            }
        };
    }

    private void onLoginClicked() {
        String username = textUsernameLayout.getEditText().getText().toString();
        String password = textPasswordInput.getEditText().getText().toString();
        /*
        if (username.isEmpty()) {
            textUsernameLayout.setError("Username must not be empty");
        } else if (password.isEmpty()) {
            textPasswordInput.setError("Password must not be empty");
        }*/

        performLogin();
    }

    int loginDelayTime = 1000;

    private void performLogin() {
        textUsernameLayout.setEnabled(false);
        textPasswordInput.setEnabled(false);
        loginButton.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(() -> {

            // logo - slide up & fade out
            logoAnimation(logoImageView);
            // login frame - slide down & fade out
            frameAnimation(loginFrame);

            // for making views invisible
            logoImageView.setVisibility(View.GONE);
            loginFrame.setVisibility(View.GONE);

        }, loginDelayTime);

        handler.postDelayed(() -> {
            // start HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

            // fade in HomeActivity
            overridePendingTransition(R.anim.fade_in, R.anim.stay);
        }, loginDelayTime + 500);

        handler.postDelayed(() -> {
            // finish LoginActivity
            finish();
        }, loginDelayTime + 1000);

    }

    private void logoAnimation(View logoImageView) {
        // transition animation - slide up & fade out
        Animation aniSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        Animation aniFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new OvershootInterpolator());
        animationSet.setDuration(700);
        animationSet.addAnimation(aniSlideUp);
        animationSet.addAnimation(aniFadeOut);
        logoImageView.startAnimation(animationSet);
    }

    private void frameAnimation(View loginFrame) {
        Animation aniSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        Animation aniFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new OvershootInterpolator());
        animationSet.setDuration(700);
        animationSet.addAnimation(aniSlideDown);
        animationSet.addAnimation(aniFadeOut);
        loginFrame.startAnimation(animationSet);
    }

}