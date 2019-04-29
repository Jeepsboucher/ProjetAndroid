package com.jpetalice.cba533.projetandroid.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.jpetalice.cba533.projetandroid.Adapter;
import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper database = new DatabaseHelper(this);

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recipe> dataSet;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.password:
                goToPassword();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToPassword() {
        Intent password = new Intent(this, PasswordActivity.class);
        password.putExtra("pageType", "AddOrUpdatePassword");
        startActivity(password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SetListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoadData();
    }

    private void SetListeners(){
        FloatingActionButton btnAdd = findViewById(R.id.button_add_recipe);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotToAddNewRecipe();

            }
        });
    }

    private void GotToAddNewRecipe(){
        Intent addRecipe = new Intent(this, AddRecipeActivity.class);
        startActivity(addRecipe);
    }

    private void LoadData(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_recipe);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataSet = new ArrayList<>();
        dataSet = database.getRecipes();
        adapter = new Adapter(dataSet);
        recyclerView.setAdapter(adapter);
    }
}
