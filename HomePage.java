package com.myrestaurant.morsemate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity{
    Button alphaToMorse,morseToAlpha;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        alphaToMorse = findViewById(R.id.home_alphaToMorse);
        morseToAlpha = findViewById(R.id.home_morseToAlpha);
        alphaToMorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AlphaToMorse.class));
            }
        });
        morseToAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MorseToAlpha.class));
            }
        });
    }
}
