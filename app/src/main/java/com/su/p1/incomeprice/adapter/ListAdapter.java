package com.su.p1.incomeprice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.su.p1.incomeprice.R;
import com.su.p1.incomeprice.model.List;

import java.util.ArrayList;

/**
 * Created by ~ { P_Slyp } ~ on 11/23/2017.
 */

public class ListAdapter extends ArrayAdapter<List> {

    private Context mContext;
    private ArrayList<List> mList;
    private int mLayout;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<List> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mList = objects;
        this.mLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(mLayout, null);

        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        TextView memo = (TextView) view.findViewById(R.id.memoTextView);
        TextView money = (TextView) view.findViewById(R.id.moneyTextView);

        List list = mList.get(position);

        im.setImageResource(list.getPictureList());
        title.setText(list.getTitle());
        memo.setText(list.getMemo());
        money.setText(String.valueOf(list.getMoney()));

        return view;
    }
}
