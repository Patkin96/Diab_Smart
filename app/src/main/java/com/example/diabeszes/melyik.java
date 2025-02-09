package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import android.os.Handler;
import android.widget.Toast;

public class melyik extends AppCompatActivity {

    Random rand = new Random();
    String elso = "";
    String masodik = "";
    ImageView elsokep;
    ImageView masodikkep;
    int kacsa1 = 0;
    int kacsa2 = 0;
    ImageButton kacsaegy;
    ImageButton kacsaketto;
    ImageView csillag1;
    ImageView csillag2;
    ImageView allat1;
    ImageView allat2;
    ImageView gluten1;
    ImageView gluten2;
    ImageView laktoz1;
    ImageView laktoz2;
    String cs1 = "";
    String cs2 = "";
    String a1 = "";
    String a2 = "";
    String g1 = "";
    String g2 = "";
    String l1 = "";
    String l2 = "";
    String egy = "";
    String ketto = "";
    String first = "";
    String second = "";
    String profilgluten = "";
    String profillaktoz = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melyik);

        csillag1= findViewById(R.id.csillag);
        allat1 = findViewById(R.id.allat);
        gluten1 = findViewById(R.id.gluten);
        laktoz1 = findViewById(R.id.laktoz);

        csillag2 = findViewById(R.id.csillag2);
        allat2 = findViewById(R.id.allat2);
        gluten2 = findViewById(R.id.gluten2);
        laktoz2 = findViewById(R.id.laktoz2);

        csillag1.setVisibility(View.INVISIBLE);
        csillag2.setVisibility(View.INVISIBLE);
        allat1.setVisibility(View.INVISIBLE);
        allat2.setVisibility(View.INVISIBLE);
        gluten1.setVisibility(View.INVISIBLE);
        gluten2.setVisibility(View.INVISIBLE);
        laktoz1.setVisibility(View.INVISIBLE);
        laktoz2.setVisibility(View.INVISIBLE);




        kacsaegy = findViewById(R.id.button1);
        kacsaketto = findViewById(R.id.button2);
        kacsaegy.setVisibility(View.VISIBLE);
        kacsaketto.setVisibility(View.VISIBLE);
        kacsaegy.setEnabled(true);
        kacsaketto.setEnabled(true);
        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray melyik=obj.getJSONArray("melyik");


            int size = melyik.length();

            int randomNum = rand.nextInt((size - 2) + 1) + 1;

            JSONObject jsonObject=melyik.getJSONObject(randomNum);
            elso =jsonObject.getString("K");
            masodik =jsonObject.getString("N");
            elsokep = findViewById(R.id.imageView43);
            masodikkep = findViewById(R.id.imageView49);

            String kepname1 = "melyik" + elso;
            String kepname2 = "melyik" + masodik;

            int randomNum2 = rand.nextInt( 2);

            if(randomNum2 == 0) {
                first = elso;
                second = masodik;
                elsokep.setBackgroundResource(getResources().getIdentifier(kepname1, "drawable", getPackageName()));
                masodikkep.setBackgroundResource(getResources().getIdentifier(kepname2, "drawable", getPackageName()));
                kacsa1 = 1;
                kacsa2 = 0;
            }
            else
            {
                first = masodik;
                second = elso;
                elsokep.setBackgroundResource(getResources().getIdentifier(kepname2, "drawable", getPackageName()));
                masodikkep.setBackgroundResource(getResources().getIdentifier(kepname1, "drawable", getPackageName()));
                kacsa2 = 1;
                kacsa1= 0;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        startActivity(new Intent(melyik.this, MainActivity.class));

    }

    public void kacsa2(View view) {
        if(kacsa2 == 1)
        {
            kiskorok();

            //alap
            kacsaegy = findViewById(R.id.button1);
            kacsaketto = findViewById(R.id.button2);
            kacsaegy.setVisibility(View.INVISIBLE);
            kacsaketto.setVisibility(View.VISIBLE);
            kacsaegy.setEnabled(false);
            kacsaketto.setEnabled(false);

            final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.correct);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                    performOnEnd3();

                }




            });
            onPrepared(mPlayer);

        }
        else
        {
            final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.incorrect);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();

                }




            });
            onPrepared(mPlayer);
        }
    }

    public void kacsa1(View view) {
        if(kacsa1== 1)
        {
                    kiskorok();

                    kacsaegy = findViewById(R.id.button1);
                    kacsaketto = findViewById(R.id.button2);
                kacsaegy.setVisibility(View.VISIBLE);
                kacsaketto.setVisibility(View.INVISIBLE);
                kacsaegy.setEnabled(false);
            kacsaketto.setEnabled(false);


            final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.correct);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                    performOnEnd3();

                }




            });
            onPrepared(mPlayer);

        }
        else
        {
            final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.incorrect);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();

                }




            });
            onPrepared(mPlayer);
        }
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("diabeszesmelyik.json");
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

    public String readJSONFromAsset2() {
        String json = null;
        try {
            InputStream is = getAssets().open("diabeszesmelyikkicsi.json");
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

    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    private void performOnEnd3() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                kacsaegy = findViewById(R.id.button1);
                kacsaketto = findViewById(R.id.button2);
                kacsaegy.setVisibility(View.VISIBLE);
                kacsaketto.setVisibility(View.VISIBLE);
                kacsaegy.setEnabled(true);
                kacsaketto.setEnabled(true);

                csillag1= findViewById(R.id.csillag);
                allat1 = findViewById(R.id.allat);
                gluten1 = findViewById(R.id.gluten);
                laktoz1 = findViewById(R.id.laktoz);

                csillag2 = findViewById(R.id.csillag2);
                allat2 = findViewById(R.id.allat2);
                gluten2 = findViewById(R.id.gluten2);
                laktoz2 = findViewById(R.id.laktoz2);

                csillag1.setVisibility(View.INVISIBLE);
                csillag2.setVisibility(View.INVISIBLE);
                allat1.setVisibility(View.INVISIBLE);
                allat2.setVisibility(View.INVISIBLE);
                gluten1.setVisibility(View.INVISIBLE);
                gluten2.setVisibility(View.INVISIBLE);
                laktoz1.setVisibility(View.INVISIBLE);
                laktoz2.setVisibility(View.INVISIBLE);

                try {
                    JSONObject obj = new JSONObject(readJSONFromAsset());
                    JSONArray melyik=obj.getJSONArray("melyik");


                    int size = melyik.length();

                    int randomNum = rand.nextInt((size - 2) + 1) + 1;

                    JSONObject jsonObject=melyik.getJSONObject(randomNum);
                    elso =jsonObject.getString("K");
                    masodik =jsonObject.getString("N");
                    elsokep = findViewById(R.id.imageView43);
                    masodikkep = findViewById(R.id.imageView49);

                    String kepname1 = "melyik" + elso;
                    String kepname2 = "melyik" + masodik;

                    int randomNum2 = rand.nextInt( 2);

                    if(randomNum2 == 0) {
                        first = elso;
                        second = masodik;
                        elsokep.setBackgroundResource(getResources().getIdentifier(kepname1, "drawable", getPackageName()));
                        masodikkep.setBackgroundResource(getResources().getIdentifier(kepname2, "drawable", getPackageName()));
                        kacsa1 = 1;
                        kacsa2 = 0;
                    }
                    else
                    {
                        first = masodik;
                        second = elso;
                        elsokep.setBackgroundResource(getResources().getIdentifier(kepname2, "drawable", getPackageName()));
                        masodikkep.setBackgroundResource(getResources().getIdentifier(kepname1, "drawable", getPackageName()));
                        kacsa2 = 1;
                        kacsa1= 0;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 5000);



    }

    private void kiskorok()
    {

        //profil olvasása


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

                JSONObject jsonObject3  = new JSONObject(responce);

                profilgluten = jsonObject3.get("gluten").toString();
                profillaktoz = jsonObject3.get("laktoz").toString();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else {

            profilgluten = "FALSE";
            profillaktoz = "FALSE";


        }


        //kis karikák állítása
        csillag1= findViewById(R.id.csillag);
        allat1 = findViewById(R.id.allat);
        gluten1 = findViewById(R.id.gluten);
        laktoz1 = findViewById(R.id.laktoz);

        csillag2 = findViewById(R.id.csillag2);
        allat2 = findViewById(R.id.allat2);
        gluten2 = findViewById(R.id.gluten2);
        laktoz2 = findViewById(R.id.laktoz2);





        try {
            JSONObject obj = new JSONObject(readJSONFromAsset2());
            JSONArray melyikkicsi=obj.getJSONArray("melyikkicsi");


            int index1 = Integer.parseInt(first) -1;
            JSONObject jsonObject1=melyikkicsi.getJSONObject(index1);
            egy =jsonObject1.getString("E");
            int index2 = Integer.parseInt(second) -1;


            JSONObject jsonObject2=melyikkicsi.getJSONObject(index2);
            ketto =jsonObject2.getString("E");
            if(first.equals(egy))
            {
                //egycsillag
                cs1 =jsonObject1.getString("A");
                csillag1.setVisibility(View.VISIBLE);
                if(cs1.equals("1"))
                {
                    csillag1.setBackgroundResource(getResources().getIdentifier("egycsillag", "drawable", getPackageName()));

                }
                if(cs1.equals("2"))
                {
                    csillag1.setBackgroundResource(getResources().getIdentifier("ketcsillag", "drawable", getPackageName()));

                }
                if(cs1.equals("3"))
                {
                    csillag1.setBackgroundResource(getResources().getIdentifier("haromcsillag", "drawable", getPackageName()));

                }
                cs1 = "";

                //allategy
                a1 =jsonObject1.getString("F");
                allat1.setVisibility(View.VISIBLE);
                if(a1.equals("1"))
                {
                    allat1.setBackgroundResource(getResources().getIdentifier("csiga", "drawable", getPackageName()));

                }
                if(a1.equals("2"))
                {
                    allat1.setBackgroundResource(getResources().getIdentifier("mokus", "drawable", getPackageName()));

                }
                if(a1.equals("3"))
                {
                    allat1.setBackgroundResource(getResources().getIdentifier("leopard", "drawable", getPackageName()));

                }
                a1 = "";

                //gluten1
                g1 =jsonObject1.getString("G");
                if(profilgluten.equals("FALSE"))
                {
                    gluten1.setVisibility(View.INVISIBLE);

                }
                else
                {
                    gluten1.setVisibility(View.VISIBLE);

                }
                if(g1.equals("0"))
                {
                    gluten1.setBackgroundResource(getResources().getIdentifier("glutenmentes", "drawable", getPackageName()));

                }
                if(g1.equals("1"))
                {
                    gluten1.setBackgroundResource(getResources().getIdentifier("gluten", "drawable", getPackageName()));

                }
                g1 = "";

                //laktozegy
                l1 =jsonObject1.getString("L");

                if(profillaktoz.equals("FALSE"))
                {
                    laktoz1.setVisibility(View.INVISIBLE);
                }
                else
                {
                    laktoz1.setVisibility(View.VISIBLE);
                }

                if(l1.equals("0"))
                {
                    laktoz1.setBackgroundResource(getResources().getIdentifier("laktozmentes", "drawable", getPackageName()));

                }
                if(l1.equals("1"))
                {
                    laktoz1.setBackgroundResource(getResources().getIdentifier("laktoz", "drawable", getPackageName()));

                }
                l1 = "";


            }
            if (second.equals(ketto))
            {
                //ketcsillag
                cs2 =jsonObject2.getString("A");
                csillag2.setVisibility(View.VISIBLE);

                if(cs2.equals("1"))
                {
                    csillag2.setBackgroundResource(getResources().getIdentifier("egycsillag", "drawable", getPackageName()));

                }
                if(cs2.equals("2"))
                {
                    csillag2.setBackgroundResource(getResources().getIdentifier("ketcsillag", "drawable", getPackageName()));

                }
                if(cs2.equals("3"))
                {
                    csillag2.setBackgroundResource(getResources().getIdentifier("haromcsillag", "drawable", getPackageName()));

                }
                cs2 = "";
                //ketallat
                a2 =jsonObject2.getString("F");
                allat2.setVisibility(View.VISIBLE);
                if(a2.equals("1"))
                {
                    allat2.setBackgroundResource(getResources().getIdentifier("csiga", "drawable", getPackageName()));

                }
                if(a2.equals("2"))
                {
                    allat2.setBackgroundResource(getResources().getIdentifier("mokus", "drawable", getPackageName()));

                }
                if(a2.equals("3"))
                {
                    allat2.setBackgroundResource(getResources().getIdentifier("leopard", "drawable", getPackageName()));

                }

                a2 = "";


                //gluten2
                g2 =jsonObject2.getString("G");

                if(profilgluten.equals("FALSE"))
                {
                    gluten2.setVisibility(View.INVISIBLE);

                }
                else{
                    gluten2.setVisibility(View.VISIBLE);

                }
                if(g2.equals("0"))
                {
                    gluten2.setBackgroundResource(getResources().getIdentifier("glutenmentes", "drawable", getPackageName()));

                }
                if(g2.equals("1"))
                {
                    gluten2.setBackgroundResource(getResources().getIdentifier("gluten", "drawable", getPackageName()));

                }
                g2 = "";

                //laktozketto
                l2 =jsonObject2.getString("L");

                if(profillaktoz.equals("FALSE"))
                {
                    laktoz2.setVisibility(View.INVISIBLE);

                }
                else{
                    laktoz2.setVisibility(View.VISIBLE);

                }
                if(l2.equals("0"))
                {
                    laktoz2.setBackgroundResource(getResources().getIdentifier("laktozmentes", "drawable", getPackageName()));

                }
                if(l2.equals("1"))
                {
                    laktoz2.setBackgroundResource(getResources().getIdentifier("laktoz", "drawable", getPackageName()));

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //////////////////////////////////////////
    }

}