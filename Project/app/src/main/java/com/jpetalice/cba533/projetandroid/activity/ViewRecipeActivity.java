package com.jpetalice.cba533.projetandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;

public class ViewRecipeActivity extends AppCompatActivity {
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        SetData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            recipe = extras.getParcelable("recipe");
        }
    }

    private void SetData(){
        ImageView image = findViewById(R.id.imgRecipe);
        TextView name = findViewById(R.id.txtName);
        TextView descr = findViewById(R.id.txtDescr);

        image.setImageBitmap(recipe.getPhoto());
        name.setText(recipe.getName());
        descr.setText(recipe.getDescr());

    }
}
