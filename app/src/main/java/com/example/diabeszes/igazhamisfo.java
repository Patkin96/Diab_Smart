package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class igazhamisfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igazhamisfo);
    }

    public void back(View view) {
        startActivity(new Intent(igazhamisfo.this, MainActivity.class));
    }

    public void start(View view) {
        startActivity(new Intent(igazhamisfo.this, igazhamis.class));

    }
}