package com.jpetalice.cba533.projetandroid.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;
import com.jpetalice.cba533.projetandroid.Adapter;
import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper database = new DatabaseHelper(this);

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
                Shake(v);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GotToAddNewRecipe();
                    }
                }, 500);

            }
        });
    }

    void Shake(View v){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        v.startAnimation(shake);
    }

    private void GotToAddNewRecipe(){
        Intent addRecipe = new Intent(this, AddRecipeActivity.class);
        startActivity(addRecipe);
    }

    private void LoadData(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView_recipe);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Recipe> listRecipes;
        listRecipes = database.getRecipes();

        recyclerView.setAdapter(new Adapter(listRecipes, new Adapter.OnItemClickListener() {
            @Override public void onItemClick(Recipe recipe) {
                OpenRecipe(recipe);
            }
        }));
    }

    private void OpenRecipe(Recipe recipe){
        Intent viewRecipe = new Intent(this, ViewRecipeActivity.class);
        viewRecipe.putExtra("RecipeId", recipe.getId());
        startActivity(viewRecipe);
    }
}
