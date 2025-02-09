package com.example.diabeszes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class profil extends AppCompatActivity {

    int szamol = 0;
    int szamol2 = 0;
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    String gluten = "";
    String laktoz = "";
    String glutenj = "0";
    String laktozj = "0";
    String avatar = "0";
    public static String gluten2 = "";
    public static String laktoz2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        button1 = findViewById(R.id.button12);
        button2 = findViewById(R.id.button13);
        button3 = findViewById(R.id.button7);


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
                glutenj = jsonObject2.get("gluten").toString();
                laktozj = jsonObject2.get("laktoz").toString();

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
                glutenj = jsonObject.getString("gluten");
                laktozj = jsonObject.getString("laktoz");


            } catch (JSONException e) {

                e.printStackTrace();
            }

        }

        if(profil2.newavatar != "")
        {
            avatar = profil2.newavatar;
        }

        if(profil2.gluten3 != "")
        {
            glutenj = profil2.gluten3;
        }

        if(profil2.laktoz3 != "")
        {
            laktozj = profil2.laktoz3;
        }

        if (avatar.contains("1"))
            {
                button3.setBackgroundResource(R.drawable.avatar1);
            }
            if (avatar.contains("2"))
            {
                button3.setBackgroundResource(R.drawable.avatar2);
            }
            if (avatar.contains("3"))
            {
                button3.setBackgroundResource(R.drawable.avatar3);
            }
            if (avatar.contains("4"))
            {
                button3.setBackgroundResource(R.drawable.avatar4);
            }
            if (avatar.contains("5"))
            {
                button3.setBackgroundResource(R.drawable.avatar5);
            }
            if (avatar.contains("6"))
            {
                button3.setBackgroundResource(R.drawable.avatar6);
            }

            if (glutenj.contains("FALSE"))
            {
                button1.setBackgroundResource(R.drawable.checkbox1);
                gluten = "FALSE";

            }
            if (glutenj.contains("TRUE"))
            {
                button1.setBackgroundResource(R.drawable.checkbox2);
                gluten = "TRUE";

            }

            if (laktozj.contains("FALSE"))
            {
                button2.setBackgroundResource(R.drawable.checkbox1);
                laktoz = "FALSE";

            }
            if (laktozj.contains("TRUE"))
            {
                button2.setBackgroundResource(R.drawable.checkbox2);
                laktoz = "TRUE";

            }



    }

    public void back(View view) {
        startActivity(new Intent(profil.this, MainActivity.class));
    }

    public void profil2(View view) {
        gluten2 = gluten;
        laktoz2 = laktoz;
        startActivity(new Intent(profil.this, profil2.class));
    }

    public void gluten(View view) {
        szamol += 1;

        if(szamol%2 == 0 )
        {
            button1.setBackgroundResource(R.drawable.checkbox1);
            gluten = "FALSE";
        }
        else
        {
            button1.setBackgroundResource(R.drawable.checkbox2);
            gluten = "TRUE";
        }
    }

    public void laktoz(View view) {
        szamol2 += 1;

        if(szamol2%2 == 0)
        {
            button2.setBackgroundResource(R.drawable.checkbox1);
            laktoz = "FALSE";
        }
        else
        {
            button2.setBackgroundResource(R.drawable.checkbox2);
            laktoz = "TRUE";
        }
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


    public void checkmark(View view) {
        //WRITE

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("avatar", avatar);
            jsonObject.put("gluten", gluten);
            jsonObject.put("laktoz", laktoz);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Convert JsonObject to String Format
        String userString = jsonObject.toString();
        // Define the File Path and its Name
        File file = new File(this.getFilesDir(),"profildata.json");
        FileWriter fileWriter = null;


        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(profil.this, MainActivity.class));

    }
}