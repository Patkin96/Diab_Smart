package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class igazhamis extends AppCompatActivity {
    TextView text;
    Random rand = new Random();
    String kerdes = "";
    String valasz = "";
    String text2 = "";
    int charcter = 0;
    int goodszamol = 0;
    int badszamol = 0;
    ImageButton igaz;
    ImageButton hamis;
    String zene = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igazhamis);
        igaz = findViewById(R.id.button9);
        hamis = findViewById(R.id.button10);

        igaz.setVisibility(View.INVISIBLE);
        hamis.setVisibility(View.INVISIBLE);

        igaz.setEnabled(false);
        hamis.setEnabled(false);

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray igazhamis=obj.getJSONArray("igazhamis");


            int size = igazhamis.length();

            int randomNum = rand.nextInt((size - 2) + 1) + 1;

            JSONObject jsonObject=igazhamis.getJSONObject(randomNum);
            kerdes =jsonObject.getString("K");
            valasz =jsonObject.getString("V");


            final int length = kerdes.length();

             zene = "szoveg" + String.valueOf(randomNum) ;
            playMp3(zene);

           // Toast.makeText(getApplicationContext(),String.valueOf(duration),Toast.LENGTH_LONG).show();

            text = (TextView)findViewById(R.id.textView);
            text.setVisibility(View.VISIBLE);
            new CountDownTimer(50000, 100){
                public void onTick(long millisUntilFinished){
                    if(charcter != length) {
                        text2 += kerdes.charAt(charcter);
                        charcter++;
                        text.setText(text2);
                    }
                    else
                    {
                        text2 = "";
                        charcter = 0;

                            igaz = findViewById(R.id.button9);
                            hamis = findViewById(R.id.button10);

                            igaz.setVisibility(View.VISIBLE);
                            hamis.setVisibility(View.VISIBLE);

                            igaz.setEnabled(true);
                            hamis.setEnabled(true);

                        cancel();
                    }

                }
                public  void onFinish(){
                }
            }.start();



            //  text.setText(kerdes);






        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("diabeszes.json");
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
    public void igaz(View view) {
        igaz = findViewById(R.id.button9);
        hamis = findViewById(R.id.button10);

        igaz.setVisibility(View.INVISIBLE);
        hamis.setVisibility(View.INVISIBLE);

        igaz.setEnabled(false);
        hamis.setEnabled(false);



        if(valasz.contains("TRUE")) {
            goodszamol += 1;
            if (goodszamol == 10)
            {
                goodszamol = 0;
                int randomNum2 = rand.nextInt((3 - 1) + 1) + 1;
                if(randomNum2 == 1)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound11_szep);
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
                if(randomNum2 == 2)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound12_tovabb);
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
                if(randomNum2==3)
                {

                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound13_ugyes);
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

            }
            else {
                //final MediaPlayer mp = MediaPlayer.create(this, R.raw.correct);
                //mp.start();
                String file = "R.raw.correct";
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



        }
        else
        {

            badszamol += 1;
            if (badszamol == 3)
            {
                badszamol = 0;
                int randomNum2 = rand.nextInt((3 - 1) + 1) + 1;
                if(randomNum2 == 1)
                {

                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound9_nem);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }
                if(randomNum2 == 2)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound10_nezdmeg);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }
                if(randomNum2==3)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound14_ujra);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }

            }
            else {

                final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.incorrect);
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                        performOnEnd2();


                    }




                });
                onPrepared(mPlayer);            }

        }
    }
    public void hamis(View view) {

        igaz = findViewById(R.id.button9);
        hamis = findViewById(R.id.button10);

        igaz.setVisibility(View.INVISIBLE);
        hamis.setVisibility(View.INVISIBLE);

        igaz.setEnabled(false);
        hamis.setEnabled(false);
        if(valasz.contains("FALSE")) {
            // text.setText(kerdes);
            goodszamol += 1;
            if (goodszamol == 10)
            {
                goodszamol = 0;
                int randomNum2 = rand.nextInt((3 - 1) + 1) + 1;
                if(randomNum2 == 1)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound11_szep);
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
                if(randomNum2 == 2)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound12_tovabb);
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
                if(randomNum2==3)
                {

                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound13_ugyes);
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

            }
            else {
                //final MediaPlayer mp = MediaPlayer.create(this, R.raw.correct);
                //mp.start();
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



        }
        else
        {

            badszamol += 1;
            if (badszamol == 3)
            {
                badszamol = 0;
                int randomNum2 = rand.nextInt((3 - 1) + 1) + 1;
                if(randomNum2 == 1)
                {

                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound9_nem);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }
                if(randomNum2 == 2)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound10_nezdmeg);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }
                if(randomNum2==3)
                {
                    final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.sound14_ujra);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                            performOnEnd2();

                        }




                    });
                    onPrepared(mPlayer);
                }

            }
            else {
                // final MediaPlayer mp = MediaPlayer.create(this, R.raw.incorrect);
                // mp.start();
                String file = "R.raw.incorrect";
                final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.incorrect);
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                        performOnEnd2();

                    }




                });
                onPrepared(mPlayer);
            }

        }

    }

    public void back(View view) {
        if(zene!="") {
            stopMp3(zene);
        }
        startActivity(new Intent(igazhamis.this, MainActivity.class));
    }


    private void playMp3(String nameOfFile){
        final MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
               mp.stop();
                mp.release();

                performOnEnd();

            }


        });


        onPrepared(mPlayer);

    }

    private void stopMp3(String nameOfFile) {
        final MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
        mPlayer.stop();
        mPlayer.release();

    }




    private void performOnEnd() {

            igaz = findViewById(R.id.button9);
            hamis = findViewById(R.id.button10);
            text = (TextView)findViewById(R.id.textView);
        text.setVisibility(View.VISIBLE);

            igaz.setVisibility(View.VISIBLE);
            hamis.setVisibility(View.VISIBLE);

            igaz.setEnabled(true);
            hamis.setEnabled(true);



    }

    private void performOnEnd3() {
        igaz = findViewById(R.id.button9);
        hamis = findViewById(R.id.button10);
        text = (TextView)findViewById(R.id.textView);

        igaz.setVisibility(View.INVISIBLE);
        hamis.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        igaz.setEnabled(false);
        hamis.setEnabled(false);

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray igazhamis=obj.getJSONArray("igazhamis");

            int size = igazhamis.length();
            int randomNum = rand.nextInt((size - 2) + 1) + 1;

            JSONObject jsonObject=igazhamis.getJSONObject(randomNum);
            kerdes =jsonObject.getString("K");
            valasz =jsonObject.getString("V");



            final int length = kerdes.length();
            zene = "szoveg" + String.valueOf(randomNum) ;
            playMp3(zene);
            text = (TextView)findViewById(R.id.textView);
            text.setVisibility(View.VISIBLE);
            new CountDownTimer(50000, 100){
                public void onTick(long millisUntilFinished){
                    if(charcter != length) {
                        text2 += kerdes.charAt(charcter);
                        charcter++;
                        text.setText(text2);
                    }
                    else
                    {
                        text2 = "";
                        charcter = 0;

                            igaz = findViewById(R.id.button9);
                            hamis = findViewById(R.id.button10);

                            igaz.setVisibility(View.VISIBLE);
                            hamis.setVisibility(View.VISIBLE);
                            text.setVisibility(View.VISIBLE);

                            igaz.setEnabled(true);
                            hamis.setEnabled(true);


                        cancel();
                    }

                }
                public  void onFinish(){
                }
            }.start();

            // text.setText(kerdes);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void performOnEnd2() {
        igaz = findViewById(R.id.button9);
        hamis = findViewById(R.id.button10);

        igaz.setVisibility(View.VISIBLE);
        hamis.setVisibility(View.VISIBLE);

        igaz.setEnabled(true);
        hamis.setEnabled(true);

    }
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

}