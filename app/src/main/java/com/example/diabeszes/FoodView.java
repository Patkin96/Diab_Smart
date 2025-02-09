package com.example.diabeszes;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodView extends RecyclerView.ViewHolder {
    public ImageView imageMain, imageTL, imageTR, imageBL, imageBR;
    public View filter;

    public FoodView(@NonNull View itemView) {
        super(itemView);

        imageMain = itemView.findViewById(R.id.p_imageView);
        imageTL = itemView.findViewById(R.id.p_imageView2);
        imageTR = itemView.findViewById(R.id.p_imageView3);
        imageBL = itemView.findViewById(R.id.p_imageView4);
        imageBR = itemView.findViewById(R.id.p_imageView5);
        filter = itemView.findViewById(R.id.p_view5);
    }
}
