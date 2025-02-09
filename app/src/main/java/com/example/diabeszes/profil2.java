package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class profil2 extends AppCompatActivity {

    int selected = 0;
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    public static String gluten3 = "";
    public static String laktoz3 = "";
    public static String newavatar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil2);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button16);
        button3 = findViewById(R.id.button17);
        button4 = findViewById(R.id.button15);
        button5 = findViewById(R.id.button19);
        button6 = findViewById(R.id.button18);

    }

    public void back(View view) {
        startActivity(new Intent(profil2.this, profil.class));
        selected = 0;
        newavatar = "";
    }

    public void avatar1(View view) {
        selected = 1;
        button1.setBackgroundResource(R.drawable.avatar1m);
        button2.setBackgroundResource(R.drawable.avatar2);
        button3.setBackgroundResource(R.drawable.avatar3);
        button4.setBackgroundResource(R.drawable.avatar4);
        button5.setBackgroundResource(R.drawable.avatar5);
        button6.setBackgroundResource(R.drawable.avatar6);


    }

    public void avatar2(View view) {
        selected = 2;

        button1.setBackgroundResource(R.drawable.avatar1);
        button2.setBackgroundResource(R.drawable.avatar2m);
        button3.setBackgroundResource(R.drawable.avatar3);
        button4.setBackgroundResource(R.drawable.avatar4);
        button5.setBackgroundResource(R.drawable.avatar5);
        button6.setBackgroundResource(R.drawable.avatar6);
    }

    public void avatar3(View view) {
        selected = 3;

        button1.setBackgroundResource(R.drawable.avatar1);
        button2.setBackgroundResource(R.drawable.avatar2);
        button3.setBackgroundResource(R.drawable.avatar3m);
        button4.setBackgroundResource(R.drawable.avatar4);
        button5.setBackgroundResource(R.drawable.avatar5);
        button6.setBackgroundResource(R.drawable.avatar6);
    }

    public void avatar4(View view) {
        selected = 4;

        button1.setBackgroundResource(R.drawable.avatar1);
        button2.setBackgroundResource(R.drawable.avatar2);
        button3.setBackgroundResource(R.drawable.avatar3);
        button4.setBackgroundResource(R.drawable.avatar4m);
        button5.setBackgroundResource(R.drawable.avatar5);
        button6.setBackgroundResource(R.drawable.avatar6);
    }

    public void avatar5(View view) {
        selected = 5;

        button1.setBackgroundResource(R.drawable.avatar1);
        button2.setBackgroundResource(R.drawable.avatar2);
        button3.setBackgroundResource(R.drawable.avatar3);
        button4.setBackgroundResource(R.drawable.avatar4);
        button5.setBackgroundResource(R.drawable.avatar5m);
        button6.setBackgroundResource(R.drawable.avatar6);
    }

    public void avatar6(View view) {
        selected = 6;

        button1.setBackgroundResource(R.drawable.avatar1);
        button2.setBackgroundResource(R.drawable.avatar2);
        button3.setBackgroundResource(R.drawable.avatar3);
        button4.setBackgroundResource(R.drawable.avatar4);
        button5.setBackgroundResource(R.drawable.avatar5);
        button6.setBackgroundResource(R.drawable.avatar6m);
    }

    public void checkmark(View view) {
        newavatar = String.valueOf(selected);
        gluten3 = profil.gluten2;
        laktoz3 = profil.laktoz2;
        startActivity(new Intent(profil2.this, profil.class));

    }
}