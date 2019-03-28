package com.jpetalice.cba533.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Database database = new Database(this);

        Recipe recipeAlice = new Recipe("Recette alice", "bonne poutine grasse");
        database.addRecipe(recipeAlice);
    }
}
