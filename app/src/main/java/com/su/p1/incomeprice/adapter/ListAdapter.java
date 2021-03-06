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
import com.su.p1.incomeprice.model.Amount;
import com.su.p1.incomeprice.model.Money;

import java.util.ArrayList;

/**
 * Created by ~ { P_Slyp } ~ on 11/23/2017.
 */

public class ListAdapter extends ArrayAdapter<Money> {

    private Context mContext;
    private ArrayList<Money> mMoney;
    private int mLayout;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Money> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mMoney = objects;
        this.mLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(mLayout, null);

        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        //TextView date = (TextView) view.findViewById(R.id.dateTextView);
        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        TextView memo = (TextView) view.findViewById(R.id.memoTextView);
        TextView money = (TextView) view.findViewById(R.id.moneyTextView);
        TextView unit = (TextView) view.findViewById(R.id.unitTextView);

        Amount am = new Amount();
        Money list = mMoney.get(position);

        im.setImageResource(list.getPictureList());
        //date.setText(list.getDate());
        title.setText(list.getTitle());
        memo.setText("Memo " + list.getMemo());
        money.setText(am.getInEx(list.getMoney()));

        if(list.getType().equals("in")) {
            money.setTextColor(mContext.getResources().getColor(R.color.colorIncome));
            unit.setTextColor(mContext.getResources().getColor(R.color.colorIncome));
        }
        else {
            money.setTextColor(mContext.getResources().getColor(R.color.colorExpenditure));
            unit.setTextColor(mContext.getResources().getColor(R.color.colorExpenditure));
        }

        return view;
    }
}
