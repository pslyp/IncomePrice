package com.su.p1.incomeprice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import com.su.p1.incomeprice.model.Amount;

public class Balance extends AppCompatActivity {

    Amount Am;
    dateTime dtBa;
    DBHelper mDB;
    TextView income, expenditure, balance;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        initialize();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent main = new Intent(Balance.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.balance, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, dtBa.getYear(), dtBa.getMonth(), dtBa.getDay());
        }

        return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
           setBaText(selectDay, selectMonth, selectYear);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog(DATE_DIALOG_ID);

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        Am = new Amount();
        dtBa = new dateTime();
        mDB = new DBHelper(this);
        income = (TextView) findViewById(R.id.inTextView);
        expenditure = (TextView) findViewById(R.id.exTextView);
        balance = (TextView) findViewById(R.id.balanceTextView);

        setBaText(dtBa.getDay(), dtBa.getMonth(), dtBa.getYear());
    }

    private void setBaText(int day, int month, int year) {
        Am = mDB.getInEx(dtBa.getDateText(day, month, year));
        income.setText(Am.getInEx(Am.getIncome()));
        expenditure.setText(Am.getInEx(Am.getExpenditure()));
        balance.setText(Am.getInEx(Am.getIncome() - Am.getExpenditure()));
    }
}
