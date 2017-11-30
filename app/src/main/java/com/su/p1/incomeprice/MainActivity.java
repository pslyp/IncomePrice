package com.su.p1.incomeprice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.su.p1.incomeprice.adapter.ListAdapter;
import com.su.p1.incomeprice.model.List;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public Calendar calendar;
    private DBHelper mDB;

    private Button add;
    private ListAdapter adapter;
    private ListView mListView;
    private TextView dateText;

    private ArrayList<List> mList;
    private int day, month, year;
    private String date;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(this, dateSetListener, year, month, day);
        }

        return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
            setListView(selectDay, selectMonth, selectYear);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.calendarMenu:
                showDialog(DATE_DIALOG_ID);
                return true;
            case R.id.addMenu:
                Intent mIntent = new Intent(MainActivity.this, Description.class);
                startActivity(mIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initialize() {
        mList = new ArrayList<>();

        add = (Button) findViewById(R.id.addButton);
        mListView = (ListView) findViewById(R.id.listView);
        dateText = (TextView) findViewById(R.id.dateTextView);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date = getDate(day, month, year);
        setListView(day, month, year);
    }

    private void setListView(int day, int month, int year) {
        date = getDate(day, month, year);

        mDB = new DBHelper(this);
        mList = mDB.getList(date);

        adapter = new ListAdapter(this, R.layout.item, mList);

        mListView.setAdapter(adapter);
        mListView.setEmptyView(findViewById(R.id.nolistTextView));

        dateText.setText(getDateText(day, month, year));
    }

    public String getDateText(int day, int month, int year) {
        String[] monthEngList = {" ", " Jan ", " Fab ", " Mar ", " Apr ", " May ", " Jun ", " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec "};

        return (String.valueOf(day) + String.valueOf(monthEngList[month + 1]) + String.valueOf(year));
    }

    public String getDate(int day, int month, int year) {
        return (String.valueOf(day) + String.valueOf(month + 1) + String.valueOf(year));
    }

}
