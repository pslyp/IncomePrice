package com.su.p1.incomeprice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Description extends AppCompatActivity {

    private ImageView calendarImage;
    private TextView moneyTextArea, memo, dayText;

    private int day, month, year;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                String moneyText = data.getStringExtra("mText");
                moneyTextArea.setText(moneyText);
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID :
                return new DatePickerDialog(this, dateSetListener, year, month, day);
        }

        return null;

        //return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
            year = selectYear;
            month = selectMonth;
            day = selectDay;

            SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");
            String date = simple.format(new StringBuilder().append(day).append(month).append(year));

            dayText.setText(date);

            Date d = new Date(day, month, year);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent intent = new Intent(Description.this, );
        finish();

        return super.onOptionsItemSelected(item);
    }

    public void initialize() {
        calendarImage = (ImageView) findViewById(R.id.calendarImageView);
        dayText = (TextView) findViewById(R.id.dateTextView);
        memo = (TextView) findViewById(R.id.memoEditText);
        moneyTextArea = (TextView) findViewById(R.id.amountMoneyTextView);

        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");
        String date = simple.format(calendar.getTime());

        dayText.setText(date);

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        moneyTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Description.this, inoutMoney.class);
                startActivityForResult(intent, 123);
            }
        });

        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memo.setFocusable(true);
                memo.setClickable(true);
            }
        });
    }

}
