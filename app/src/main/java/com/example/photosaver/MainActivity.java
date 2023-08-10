package com.example.photosaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView imagesCard;
    CardView ajout;
    CardView downloadCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imagesCard = findViewById(R.id.imageCard);
//
//        imagesCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
//                startActivity(intent);
//            }
//        });
//        ajout = findViewById(R.id.ajouter);
//        ajout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
//                startActivity(intent);
//            }
//        });
      downloadCard = findViewById(R.id.downloadCard);
    downloadCard.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {
           Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
              startActivity(intent);
            }
      });

    }


}