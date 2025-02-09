package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class melyikfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melyikfo);
    }

    public void back(View view) {
        startActivity(new Intent(melyikfo.this, MainActivity.class));

    }

    public void start(View view) {
        startActivity(new Intent(melyikfo.this, melyik.class));

    }

    public void info(View view) {
        startActivity(new Intent(melyikfo.this, melyikinfo.class));

    }
}