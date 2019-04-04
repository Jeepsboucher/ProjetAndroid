package com.jpetalice.cba533.projetandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

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
        database.deleteFromDatabase();

        adapter = new Adapter(dataSet);
        recyclerView.setAdapter(adapter);
        TableLayout tbl = findViewById(R.id.tbl_recipe);
        List<Recipe> recipes = database.getRecipes();
        for (Recipe recipe : recipes) {
            // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            // Creation textViews
            final TextView tv_name = new TextView(this);
            tv_name.setText(recipe.getName());
            tv_name.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            final TextView tv_descr = new TextView(this);
            tv_descr.setText(recipe.getDescr());
            tv_descr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.addView(tv_name);
            tableRow.addView(tv_descr);

            tbl.addView(tableRow);
        }
    }
}
