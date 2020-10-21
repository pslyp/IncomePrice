package com.su.p1.incomeprice.model;

import android.widget.ImageView;
import android.widget.TextView;

public class Category {

    private ImageView image;
    private TextView text;
    private byte type;

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

}
