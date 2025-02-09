package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
   ImageButton button1;
    String avatar = "0";
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button6);
        image = findViewById(R.id.imageView4);


        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)


        if(currentHour >= 5 && currentHour <= 10)
        {
            image.setBackgroundResource(R.drawable.szoveg);
        }
        if(currentHour > 10 && currentHour <= 18)
        {
            image.setBackgroundResource(R.drawable.szoveg2);

        }
        if(currentHour > 18 )
        {

            image.setBackgroundResource(R.drawable.szoveg3);

        }
        //READ

        File file2 = new File(this.getFilesDir(),"profildata.json");
        FileReader fileReader = null;
        boolean exists = file2.exists();
        if(exists)
        {
            try {
                fileReader = new FileReader(file2);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null){
                    stringBuilder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                // This responce will have Json Format String
                String responce = stringBuilder.toString();

                JSONObject jsonObject2  = new JSONObject(responce);

                avatar = jsonObject2.get("avatar").toString();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else {


            try {
                JSONObject obj = new JSONObject(readJSONFromAsset());
                JSONArray profil3 = obj.getJSONArray("profil");

                JSONObject jsonObject = profil3.getJSONObject(1);
                avatar = jsonObject.getString("avatar");


            } catch (JSONException e) {

                e.printStackTrace();
            }

        }


        if (avatar.contains("1"))
        {
            button1.setBackgroundResource(R.drawable.avatar1);
        }
        if (avatar.contains("2"))
        {
            button1.setBackgroundResource(R.drawable.avatar2);
        }
        if (avatar.contains("3"))
        {
            button1.setBackgroundResource(R.drawable.avatar3);
        }
        if (avatar.contains("4"))
        {
            button1.setBackgroundResource(R.drawable.avatar4);
        }
        if (avatar.contains("5"))
        {
            button1.setBackgroundResource(R.drawable.avatar5);
        }
        if (avatar.contains("6"))
        {
            button1.setBackgroundResource(R.drawable.avatar6);
        }



    }

    public void szerkeszto(View view) {
        startActivity(new Intent(MainActivity.this, szerkeszto.class));
    }


    public void igazhamis(View view) {
        startActivity(new Intent(MainActivity.this, igazhamisfo.class));
    }

    public void melyik(View view) {
        startActivity(new Intent(MainActivity.this, melyikfo.class));
    }

    public void talca(View view) {
        startActivity(new Intent(MainActivity.this, talca.class));
    }

    public void profil(View view) {
        startActivity(new Intent(MainActivity.this, profil.class));
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("diabeszesprofil.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}