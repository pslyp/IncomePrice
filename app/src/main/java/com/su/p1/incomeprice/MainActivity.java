package com.su.p1.incomeprice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.su.p1.incomeprice.adapter.ListAdapter;
import com.su.p1.incomeprice.model.List;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<List> aList = new ArrayList<>();

        aList.add(new List(R.drawable.pizzacompany, "ค่าข้าว", "กินกับเพื่อนที่บิ๊กซี", 500));

        ListAdapter adapter = new ListAdapter(this, R.layout.item, aList);

        ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);
    }
}
