package com.su.p1.incomeprice.model;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Category {

    private LinearLayout layout;
    private ImageView image;
    private TextView text;

    public Category(LinearLayout layout, ImageView image, TextView text) {
        this.layout = layout;
        this.image = image;
        this.text = text;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setImageResource(int resId) {
        image.setImageResource(resId);
    }

    public void setText(int resId) {
        text.setText(resId);
    }

}
