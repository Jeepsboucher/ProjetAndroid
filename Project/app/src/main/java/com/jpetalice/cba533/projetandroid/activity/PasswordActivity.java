package com.jpetalice.cba533.projetandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;
import com.jpetalice.cba533.projetandroid.view.PasswordView;

public class PasswordActivity extends AppCompatActivity {

    DatabaseHelper database = new DatabaseHelper(this);

    String pageType;
    String password;
    PasswordView passwordDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        password = "";
    }

    private void setListener() {
        Button button = findViewById(R.id.button_deletePassword);
        findViewById(R.id.button_deletePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deletePassword();
                Toast.makeText(getApplicationContext(),"Mot de passe effacé", Toast.LENGTH_SHORT).show();
                GoToHomeActivity();
            }
        });

        findViewById(R.id.button_keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenKeyboard();

            }
        });
        passwordDisplay = findViewById(R.id.test);
        if(pageType == "Password") {
            button.setVisibility(View.GONE);
        }

    }

    private void OpenKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            pageType = extras.getString("pageType", "Password");
        } else {
            pageType = "Password";
        }
        setListener();
        if(!database.havePassword() && pageType == "Password") {
            GoToHomeActivity();
        }
    }

    private void GoToHomeActivity(){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String keyPressed = String.valueOf((char) event.getUnicodeChar());

        //KeyCode pour suppression == 67
        if(keyCode == 67) {
            Integer length = password.length();
            if(length >= 1) {
                password = password.substring(0, length - 1);
            }
        } else if (tryParseInt(keyPressed)) {

            if(password.length() < 4) {
                password += keyPressed;
            }

            if(password.length() == 4) {

                if(pageType == "Password") {
                    boolean correct = database.checkPassword(Integer.parseInt(password));
                    if(correct) {
                        GoToHomeActivity();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mauvais mot de passe", Toast.LENGTH_SHORT).show();
                        password = "";
                    }
                } else {
                    database.addOrUpdatePassword(Integer.parseInt(password));
                    Toast.makeText(getApplicationContext(), "Le nouveau mot de passe est:" + password, Toast.LENGTH_LONG).show();
                    GoToHomeActivity();
                }
            }
        }
        passwordDisplay.setNumberOfInput(password.length());

        return super.onKeyDown(keyCode, event);
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
