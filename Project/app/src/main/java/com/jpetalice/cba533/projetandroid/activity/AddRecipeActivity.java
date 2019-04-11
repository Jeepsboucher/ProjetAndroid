package com.jpetalice.cba533.projetandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

public class AddRecipeActivity extends AppCompatActivity {

    DatabaseHelper database = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        SetListeners();
    }

    private void SetListeners(){
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String txtName = ((EditText) findViewById(R.id.txtName)).getText().toString();
                String txtDescr = ((EditText) findViewById(R.id.txtDescr)).getText().toString();
                AddRecipe(txtName, txtDescr);
            }
        });
    }

    private void AddRecipe(String name, String descr){
        Recipe newRecipe = new Recipe(name, descr);
        database.addRecipe(newRecipe);

        GoToMainPage();
    }

    private void GoToMainPage(){
        Intent main = new Intent(this, HomeActivity.class);
        startActivity(main);
    }
}
