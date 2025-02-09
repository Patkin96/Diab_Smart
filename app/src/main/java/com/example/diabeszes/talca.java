package com.example.diabeszes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class talca extends AppCompatActivity
        implements View.OnClickListener, View.OnDragListener, PlateGameInterface {

    private LinearLayout llTop;
    private HorizontalScrollView svTop;
    private TextView title;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private FoodAdapter adapter;

    private Background databaseHelper;
    private String mealTitle;
    private double maxCarbs, recCarbs, curCarbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talca);

        svTop = findViewById(R.id.p_scrollViewM);
        svTop.setOnDragListener(this);
        svTop.setOnClickListener(this);

        llTop = findViewById(R.id.p_linearLayout2);
        llTop.setOnDragListener(this);
        llTop.setOnClickListener(this);

        title = findViewById(R.id.p_textView);
        progressBar = findViewById(R.id.p_progressBar);
        progressBar.setScaleY(3f);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        int meal;

        if (hour < 9)
            meal = 0;
        else if (hour < 11)
            meal = 1;
        else if (hour < 15)
            meal = 2;
        else if (hour < 18)
            meal = 3;
        else if (hour < 21)
            meal = 4;
        else
            meal = 5;

        databaseHelper = new Background(this);
        databaseHelper.currentMeal = meal;
        loadDataFor(databaseHelper.currentMeal);

        findViewById(R.id.p_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(talca.this, MainActivity.class));
            }
        });

        recyclerView = findViewById(R.id.p_recyclerView);
        recyclerView.setOnDragListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new FoodAdapter();
        adapter.setFoods(databaseHelper.getFoods(), databaseHelper.currentMeal);
        adapter.setOnLongClickListener(this);
        recyclerView.setAdapter(adapter);

        updateViews();
    }

    private void loadDataFor(int index) {
        Meal meal = databaseHelper.getMeal(index);
        mealTitle = meal.getName();
        maxCarbs = (int) meal.getMaxCarbs();
        recCarbs = (int) meal.getRecCarbs();
        curCarbs = (int) databaseHelper.currentCarbs;
    }

    private void updateViews() {
        loadDataFor(databaseHelper.currentMeal);
        llTop.removeAllViews();
        title.setText(mealTitle);
        progressBar.setMax((int) maxCarbs);
        progressBar.setProgress((int) curCarbs);
        progressBar.setSecondaryProgress((int) recCarbs);
        updateButtons();

        adapter.setFoods(databaseHelper.getFoods(), databaseHelper.currentMeal);

        switch (databaseHelper.currentMeal) {
            case 0:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_reggeli);
                break;
            case 1:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_tizorai);
                break;
            case 2:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_ebed);
                break;
            case 3:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_uzsonna);
                break;
            case 4:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_vacsora);
                break;
            case 5:
                findViewById(R.id.p_imageView15).setBackgroundResource(R.drawable.bg_game3_potvacsora);
                break;
        }
    }

    private void incrementPlate(int id, boolean full) {
        databaseHelper.incCurrentCarbs(id, full);

        if (databaseHelper.currentCarbs <= (int) recCarbs) {
            progressBar.setSecondaryProgress((int) recCarbs);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                progressBar.setProgress((int) databaseHelper.currentCarbs, true);
            else
                progressBar.setProgress((int) databaseHelper.currentCarbs);
        } else {
            progressBar.setSecondaryProgress((int) databaseHelper.currentCarbs);
            progressBar.setProgress(0);
        }
    }

    private void decrementPlate(int id, boolean full) {
        databaseHelper.decCurrentCarbs(id, full);

        if (databaseHelper.currentCarbs <= (int) recCarbs) {
            progressBar.setSecondaryProgress((int) recCarbs);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                progressBar.setProgress((int) databaseHelper.currentCarbs, true);
            else
                progressBar.setProgress((int) databaseHelper.currentCarbs);
        } else {
            progressBar.setSecondaryProgress((int) databaseHelper.currentCarbs);
            progressBar.setProgress(0);
        }
    }

    private void updateButtons() {
        if (databaseHelper.currentMeal == 5)
            findViewById(R.id.p_imageButtonF).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.p_imageButtonF).setVisibility(View.VISIBLE);

        if (databaseHelper.currentMeal == 0)
            findViewById(R.id.p_imageButtonB).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.p_imageButtonB).setVisibility(View.VISIBLE);
    }

    private double getCalories(int index) {
        return databaseHelper.getCalorieOfIndex(index);
    }

    private void popupDialog(final int index, final View dest, final View item) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popupdialog);
        dialog.setCancelable(false);

        TextView title1, title2, calories1, calories2;
        ImageView imageView1, imageView2;
        View half, full;
        title1 = dialog.findViewById(R.id.p_textView2);
        title1.setText("Fél adag");
        title2 = dialog.findViewById(R.id.p_textView3);
        title2.setText("Teljes adag");

        calories1 = dialog.findViewById(R.id.p_textView4);
        calories1.setText("Szénhidrát: "+String.valueOf(getCalories(index)/2));

        calories2 = dialog.findViewById(R.id.p_textView5);
        calories2.setText("Szénhidrát: "+String.valueOf(getCalories(index)));

        imageView1 = dialog.findViewById(R.id.p_imageView6);
        imageView1.setImageResource(databaseHelper.getFoodImageResource(index));
        imageView2 = dialog.findViewById(R.id.p_imageView7);
        imageView2.setImageResource(databaseHelper.getFoodImageResource(index));

        half = dialog.findViewById(R.id.p_view3);
        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementPlate(index, false);
                dialog.cancel();
                item.findViewById(R.id.p_view5).setVisibility(View.VISIBLE);
                LinearLayout destination = (LinearLayout) dest;
                destination.addView(item);
            }
        });
        full = dialog.findViewById(R.id.p_view4);
        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementPlate(index, true);
                dialog.cancel();
                item.findViewById(R.id.p_view5).setVisibility(View.INVISIBLE);
                LinearLayout destination = (LinearLayout) dest;
                destination.addView(item);
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == llTop.getId()) {
        } else if (v.getId() == findViewById(R.id.p_imageButtonF).getId()) {
            databaseHelper.currentMealUp();
            updateViews();
        } else if (v.getId() == findViewById(R.id.p_imageButtonB).getId()) {
            databaseHelper.currentMealDown();
            updateViews();
        }
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
            case DragEvent.ACTION_DRAG_ENTERED:
            case DragEvent.ACTION_DRAG_EXITED:
            case DragEvent.ACTION_DRAG_ENDED:
                view.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                String dragData = (String) item.getText();

                View v = (View) event.getLocalState();
                ViewGroup source = (ViewGroup) v.getParent();

                if ((view == llTop && source == llTop) || (view == svTop && source == svTop))
                    return false;
                else {
                    view.invalidate();
                    source.removeView(v);
                    if (view == llTop || view == svTop) {
                        popupDialog(Integer.parseInt(dragData), llTop, v);
                    } else {
                        if (source == llTop || source == svTop) {
                            decrementPlate(Integer.parseInt(dragData), v.findViewById(R.id.p_view5).getVisibility() == View.INVISIBLE);
                        }
                    }
                }

                return true;
            default:
                return false;
        }
    }

    @Override
    public void OnLongClick(View v, int id) {
        String clipText = ""+id;
        ClipData.Item item = new ClipData.Item(clipText);
        ClipData data = new ClipData(clipText, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            v.startDragAndDrop(data, dragShadowBuilder, v, 0);
        else
            v.startDrag(data, dragShadowBuilder, v, 0);
    }
}