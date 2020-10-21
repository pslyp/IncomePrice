package com.su.p1.incomeprice;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.su.p1.incomeprice.model.Category;

public class CategoryManager {

    private LinearLayout[] layout;
    private ImageView[] imageViews;
    private TextView[] textViews;

    private final int[] inImgs = new int[]{R.drawable.cimg_in_1, R.drawable.cimg_in_2, R.drawable.cimg_in_3};
    private final int[] exImgs = new int[]{R.drawable.cimg_ex_1, R.drawable.cimg_ex_2, R.drawable.cimg_ex_3, R.drawable.cimg_ex_4,
            R.drawable.cimg_ex_5, R.drawable.cimg_ex_6, R.drawable.cimg_ex_7, R.drawable.cimg_ex_8, R.drawable.cimg_ex_9};
    private final int[] inTexts = new int[]{R.string.cStrIn1, R.string.cStrIn2, R.string.cStrIn3};
    private final int[] exTexts = new int[]{R.string.cStrEx1, R.string.cStrEx2, R.string.cStrEx3, R.string.cStrEx4,
            R.string.cStrEx5, R.string.cStrEx6, R.string.cStrEx7, R.string.cStrEx8, R.string.cStrEx9};

    private Category[] c;

    private int type = 1;

    public CategoryManager(LinearLayout[] layout, ImageView[] imageViews, TextView[] textViews) {
        this.layout = layout;
        this.imageViews = imageViews;
        this.textViews = textViews;
    }

    public CategoryManager init() {
        c = new Category[layout.length];
        for (int i=0; i<layout.length; i++) {
            c[i] = new Category(layout[i], imageViews[i], textViews[i]);
        }
        setObject(1);
        return this;
    }

    private void setObject(int type) {
        for (int i=0; i<layout.length; i++) {
            if (type == 1) {
                if (i < exImgs.length) {
                    c[i].setImageResource(exImgs[i]);
                    c[i].setText(exTexts[i]);
                    c[i].getLayout().setVisibility(View.VISIBLE);
                    c[i].getLayout().setEnabled(true);
                    c[i].getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select((LinearLayout) view);
                        }
                    });
                    continue;
                }
            } else {
                if (i < inImgs.length) {
                    c[i].setImageResource(inImgs[i]);
                    c[i].setText(inTexts[i]);
                    c[i].getLayout().setEnabled(true);
                    c[i].getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select((LinearLayout) view);
                        }
                    });
                    continue;
                }
            }
            c[i].getLayout().setVisibility(View.INVISIBLE);
            c[i].getLayout().setEnabled(false);
        }
    }

    public void reload() {
        setObject(type == 1 ? (type = 2) : (type = 1));
    }

    public void select(LinearLayout view) {
        for (LinearLayout linearLayout : layout) {
            if (view == linearLayout) {
                view.setBackgroundResource(R.color.colorCategorySelect);
                continue;
            }
            linearLayout.setBackgroundResource(R.color.colorCategoryNoSelect);
        }
    }

}
