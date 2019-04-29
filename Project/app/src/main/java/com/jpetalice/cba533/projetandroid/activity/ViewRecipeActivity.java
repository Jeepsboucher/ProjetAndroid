package com.jpetalice.cba533.projetandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

public class ViewRecipeActivity extends AppCompatActivity {
    DatabaseHelper database = new DatabaseHelper(this);
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        SetListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            recipe = database.getRecipe(getIntent().getIntExtra("RecipeId", 0));
        }
        SetData();
    }

    private void SetListeners(){
        Button btnGoBack = findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMainPage();
            }
        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotToUpdateRecipe();
            }
        });

        FloatingActionButton btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe);
        btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAlertDialog();
            }
        });
    }

    private void DeleteAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Êtes-vous sûr de vouloir supprimer cette recette?")
                .setCancelable(true)
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        database.deleteRecipe(recipe.getId());
                        GoToMainPage();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void SetData(){
        ImageView image = findViewById(R.id.imgRecipe);
        TextView name = findViewById(R.id.txtName);
        TextView descr = findViewById(R.id.txtDescr);

        if (recipe != null){
            image.setImageBitmap(recipe.getPhoto());
            name.setText(recipe.getName());
            descr.setText(recipe.getDescr());
        }
    }

    private void GotToUpdateRecipe(){
        Intent updateRecipe = new Intent(this, AddRecipeActivity.class);
        updateRecipe.putExtra("RecipeId", recipe.getId());
        startActivity(updateRecipe);
    }

    private void GoToMainPage(){
        Intent main = new Intent(this, HomeActivity.class);
        startActivity(main);
    }
}
