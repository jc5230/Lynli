package com.innojane.lynli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textUsernameLayout;
    private TextInputLayout textPasswordInput;
    private Button loginButton;

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

    private void performLogin() {
        textUsernameLayout.setEnabled(false);
        textPasswordInput.setEnabled(false);
        loginButton.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}