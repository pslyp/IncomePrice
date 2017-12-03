package com.su.p1.incomeprice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.su.p1.incomeprice.adapter.ListAdapter;
import com.su.p1.incomeprice.model.Money;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private dateTime dt;
    private DBHelper mDB;

    private Button add;
    private ListAdapter adapter;
    private ListView mListView;
    private TextView account, dateText;

    private ArrayList<Money> mMoney;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        initialize();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, dt.getYear(), dt.getMonth(), dt.getDay());
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
            case R.id.addDesMenu:
                Intent description = new Intent(MainActivity.this, Description.class);
                startActivity(description);
                finish();
                return true;
            case R.id.selectMenu:
                Intent balance = new Intent(MainActivity.this, Balance.class);
                startActivity(balance);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initialize() {
        dt = new dateTime();
        mDB = new DBHelper(this);
        mMoney = new ArrayList<>();

        account = (TextView) findViewById(R.id.accountTextView);
        add = (Button) findViewById(R.id.addButton);
        mListView = (ListView) findViewById(R.id.listView);
        dateText = (TextView) findViewById(R.id.dateTextView);

        setListView(dt.getDay(), dt.getMonth(), dt.getYear());

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detail = new Intent(MainActivity.this, details.class);
                Money m = mMoney.get(position);
                detail.putExtra("Id", m.getId());
                startActivity(detail);
                finish();
            }
        });


        if (mDB.getAccount().length() == 0) {
            Intent account = new Intent(MainActivity.this, Account.class);
            startActivity(account);
            finish();
        }

        account.setText(mDB.getAccount());

    }

    private void setListView(int day, int month, int year) {
        mMoney = mDB.getList(dt.getDateText(day, month, year));

        adapter = new ListAdapter(this, R.layout.item, mMoney);

        mListView.setAdapter(adapter);
        mListView.setEmptyView(findViewById(R.id.nolistTextView));

        dateText.setText(dt.getDateTextMonth(day, month, year));
    }

}
