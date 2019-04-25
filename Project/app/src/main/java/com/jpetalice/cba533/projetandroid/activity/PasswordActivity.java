package com.jpetalice.cba533.projetandroid.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jpetalice.cba533.projetandroid.R;

public class PasswordActivity extends AppCompatActivity {

    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        showKeyboard();
        password = "";
    }

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
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
            Toast.makeText(getApplicationContext(),String.valueOf(keyPressed), Toast.LENGTH_SHORT).show();
            if(password.length() < 4) {
                password += keyPressed;
            }

            if(password.length() == 4) {
            }
        }
        Toast.makeText(getApplicationContext(),password, Toast.LENGTH_SHORT).show();

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
