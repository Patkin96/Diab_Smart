package com.example.diabeszes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodView> {
    public ArrayList<Food> foods = new ArrayList<>();
    public PlateGameInterface plateGameInterface;
    private Context context;

    @NonNull
    @Override
    public FoodView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_scrollable, parent, false);
        context = parent.getContext();
        return new FoodView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodView holder, final int position) {
        holder.imageMain.setImageResource(context.getResources()
                .getIdentifier(foods.get(position).imageFilename, "drawable", context.getPackageName()));

        holder.filter.setVisibility(View.GONE);

        switch (foods.get(position).give) {
            case 0:
                holder.imageTL.setImageResource(R.drawable.egycsillag);
                break;
            case 1:
                holder.imageTL.setImageResource(R.drawable.ketcsillag);
                break;
            case 2:
                holder.imageTL.setImageResource(R.drawable.haromcsillag);
                break;
        }

        switch (foods.get(position).type) {
            case 0:
                holder.imageTR.setImageResource(R.drawable.csiga);
                break;
            case 1:
                holder.imageTR.setImageResource(R.drawable.mokus);
                break;
            case 2:
                holder.imageTR.setImageResource(R.drawable.leopard);
                break;
        }

        if (!foods.get(position).gluten) {
            holder.imageBL.setVisibility(View.VISIBLE);
            holder.imageBL.setImageResource(R.drawable.gluten);
        } else
            holder.imageBL.setImageResource(R.drawable.glutenmentes);

        if (!foods.get(position).laktoz) {
            holder.imageBR.setVisibility(View.VISIBLE);
            holder.imageBR.setImageResource(R.drawable.laktoz);
        } else
            holder.imageBR.setImageResource(R.drawable.laktozmentes);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                plateGameInterface.OnLongClick(v, foods.get(position).id);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 : foods.size();
    }

    public void setFoods(ArrayList<Food> foods, int timeOfDay) {
        ArrayList<Food> filtered = new ArrayList<>();
        for (Food f: foods) {
            switch (timeOfDay) {
                case 0:
                    if (f.reggeli > 0)
                        filtered.add(f);
                    break;
                case 1:
                    if (f.tizorai > 0)
                        filtered.add(f);
                    break;
                case 2:
                    if (f.ebed > 0)
                        filtered.add(f);
                    break;
                case 3:
                    if (f.uzsonna > 0)
                        filtered.add(f);
                    break;
                case 4:
                    if (f.vacsora > 0)
                        filtered.add(f);
                    break;
                case 5:
                    if (f.potvacsora > 0)
                        filtered.add(f);
                    break;
            }
        }

        this.foods = filtered;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(PlateGameInterface plateGameInterface) {
        this.plateGameInterface = plateGameInterface;
    }
}
