package com.su.p1.incomeprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.su.p1.incomeprice.adapter.ListAdapter;
import com.su.p1.incomeprice.model.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<List> aList;
    ListAdapter adapter;

    Button add;
    ListView mListView;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aList = new ArrayList<>();

        add = (Button) findViewById(R.id.addButton);
        mListView = (ListView) findViewById(R.id.listView);
        dateText = (TextView) findViewById(R.id.dateTextView);

        //aList.add(new List(R.drawable.pizzacompany, "ค่าข้าว", "กินกับเพื่อนที่บิ๊กซี", 500));

        adapter = new ListAdapter(this, R.layout.item, aList);

        mListView.setAdapter(adapter);
        mListView.setEmptyView(findViewById(R.id.nolistTextView));
        mListView.setEmptyView(findViewById(R.id.addButton));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");
        String date = simple.format(calendar.getTime());

        dateText.setText(date + " (Today)");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, Description.class);
                startActivity(mIntent);
            }
        });
    }
}
