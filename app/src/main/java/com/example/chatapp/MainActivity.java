package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button singUp, singIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singIn = findViewById(R.id.in);
        singUp = findViewById(R.id.up);
        singIn.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, RegActivity.class);
            startActivity(intent);
        });
    }



}