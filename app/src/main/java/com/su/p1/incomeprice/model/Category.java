package com.su.p1.incomeprice.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Category {

    public interface OnClickListener {
        void onClick(View view, String id);
    }

    private LinearLayout layout;
    private ImageView image;
    private TextView text;

    private OnClickListener listener;
    private String id;

    public Category(LinearLayout layout, ImageView image, TextView text) {
        this.layout = layout;
        this.image = image;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVisibility(int visibility) {
        layout.setVisibility(visibility);
    }

    public void setEnabled(boolean enable) {
        layout.setEnabled(enable);
    }

    public void setOnClickListener(final OnClickListener listener) {
        this.listener = listener;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, id);
            }
        });
    }

    public void setImageResource(int resId) {
        image.setImageResource(resId);
    }

    public void setText(int resId) {
        text.setText(resId);
    }

}
