package com.jpetalice.cba533.projetandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import com.jpetalice.cba533.projetandroid.utils.DatabaseHelper;

public class AddRecipeActivity extends AppCompatActivity {
    DatabaseHelper database = new DatabaseHelper(this);
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        SetListeners();

        mediaPlayer = MediaPlayer.create(this, R.raw.notif);

        ImageView imageview = findViewById(R.id.imgRecipe);
        imageview.setImageResource(R.drawable.plate);
    }

    private void SetListeners(){
        Button btnAddPhoto = findViewById(R.id.btnAddPhoto);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OpenPhotoDialog();
            }
        });

        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GoToMainPage();
            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String txtName = ((EditText) findViewById(R.id.txtName)).getText().toString();
                String txtDescr = ((EditText) findViewById(R.id.txtDescr)).getText().toString();

                if (!txtName.equals("") && !txtDescr.equals("")) {

                    ImageView imageView = findViewById(R.id.imgRecipe);
                    Bitmap photo = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                    AddRecipe(txtName, txtDescr, photo);
                }
                else
                    MakeToast("Vous devez ajouter un nom et une description");
            }
        });
    }

    private void OpenPhotoDialog(){

        new AlertDialog.Builder(this)
            .setTitle("Ajouter une photo")
            .setPositiveButton("Appareil photo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePicture.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePicture, 0);
                    }
                }
            })
            .setNegativeButton("Galerie", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
            })
            .setNeutralButton("Annuler", null)
            .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        ImageView imageview = findViewById(R.id.imgRecipe);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageview.setImageBitmap(imageBitmap);

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }

    private void AddRecipe(String name, String descr, Bitmap photo){
        mediaPlayer.start();
        Recipe newRecipe = new Recipe(name, descr, photo);
        database.addRecipe(newRecipe);
        GoToMainPage();
    }

    private void GoToMainPage(){
        Intent main = new Intent(this, HomeActivity.class);
        startActivity(main);
    }

    private void MakeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
