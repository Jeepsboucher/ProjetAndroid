package com.jpetalice.cba533.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Recipe> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataSet = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_recipe);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper database = new DatabaseHelper(this);
        Recipe recipeAlice = new Recipe("Recette alice", "bonne poutine grasse");

        database.addRecipe(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);
        dataSet.add(recipeAlice);

        adapter = new Adapter(dataSet);
        recyclerView.setAdapter(adapter);


    }
}
