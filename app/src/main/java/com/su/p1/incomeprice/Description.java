package com.su.p1.incomeprice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.su.p1.incomeprice.model.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Description extends AppCompatActivity {

    private Button inExButton;
    private Calendar calendar;
    private ImageView calendarImage;
    private TextView moneyTextArea, memo, dateText;

    private ArrayList<List> aList;
    private boolean checkClick;
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

        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                String moneyText = data.getStringExtra("mText");
                moneyTextArea.setText(moneyText);
            }
        }
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

        return null;
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
            year = selectYear;
            month = selectMonth;
            day = selectDay;

            dateText.setText(new StringBuilder().append(day).append(getMonth(month)).append(year));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        aList.add(new List(R.drawable.pizzacompany, "ค่าข้าว", "dsad", 5000));
        Intent it = new Intent(Description.this, MainActivity.class);
        startActivity(it);
        finish();

        return super.onOptionsItemSelected(item);
    }

    public void initialize() {
        aList = new ArrayList<>();

        inExButton = (Button) findViewById(R.id.in_exButton);
        calendarImage = (ImageView) findViewById(R.id.calendarImageView);
        dateText = (TextView) findViewById(R.id.dateTextView);
        memo = (TextView) findViewById(R.id.memoEditText);
        moneyTextArea = (TextView) findViewById(R.id.amountMoneyTextView);

        calendar = Calendar.getInstance();
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy");
        String date = simple.format(calendar.getTime());

        dateText.setText(date);

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
                startActivityForResult(intent, 12);
            }
        });

        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memo.setCursorVisible(true);
            }
        });

        inExButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkClick) {
                    moneyTextArea.setTextColor(getResources().getColor(R.color.colorExpenditure));
                    inExButton.setText("Expenditure");
                    inExButton.setBackgroundResource(R.color.colorExpenditure);
                    checkClick = true;
                }
                else {
                    moneyTextArea.setTextColor(getResources().getColor(R.color.colorIncome));
                    inExButton.setText("Income");
                    inExButton.setBackgroundResource(R.color.colorIncome);
                    checkClick = false;
                }
            }
        });
    }

    public String getMonth(int month) {
        String[] monthEngList = {" Jan ", " Fab ", " Mar ", " Apr ", " May ", " Jun ", " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec "};

        return monthEngList[month];
    }

}
