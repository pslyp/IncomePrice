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
import android.widget.Toast;

import com.su.p1.incomeprice.model.List;

import java.util.Calendar;

public class Description extends AppCompatActivity {

    private DBHelper mDB;
    private MainActivity MA = new MainActivity();

    private Button inExButton;
    private TextView moneyTextArea, memo, dateText, category1, category2, category3, category4, category5, category6, category7, category8, category9;
    private ImageView calendarImage;

    private boolean checkClick;
    private int day, month, year;
    private String title, date, moneyText = "", type = "in";
    private TextView[] category;

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
                moneyText = data.getStringExtra("mText");
                moneyTextArea.setText(moneyText);
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                year = MA.calendar.get(Calendar.YEAR);
                month = MA.calendar.get(Calendar.MONTH);
                day = MA.calendar.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(this, dateSetListener, year, month, day);
        }

        return super.onCreateDialog(id);
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
            date = MA.getDate(selectDay, selectMonth, selectYear);
            dateText.setText(MA.getDateText(selectDay, selectMonth, selectYear));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (moneyText.length() > 0) {
            List list = new List();
            mDB = new DBHelper(this);

            String m = memo.getText().toString();
            if (m.length() == 0)
                m = "-";

            list.setDate(date);
            list.setPictureList(R.drawable.pizzacompany);
            list.setTitle(title);
            list.setMemo("Memo : " + m);
            list.setMoney(Double.parseDouble(moneyText));
            list.setType(type);

            mDB.addList(list);

            Intent it = new Intent(Description.this, MainActivity.class);
            startActivity(it);
            finish();
        } else
            Toast.makeText(Description.this, "Enter amount", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }


    private void initialize() {
        inExButton = (Button) findViewById(R.id.in_exButton);
        calendarImage = (ImageView) findViewById(R.id.calendarImageView);
        dateText = (TextView) findViewById(R.id.dateTextView);
        memo = (TextView) findViewById(R.id.memoEditText);
        moneyTextArea = (TextView) findViewById(R.id.amountMoneyTextView);

        setCategory();

        MA.calendar = Calendar.getInstance();
        year = MA.calendar.get(Calendar.YEAR);
        month = MA.calendar.get(Calendar.MONTH);
        day = MA.calendar.get(Calendar.DAY_OF_MONTH);
        date = MA.getDate(day, month, year);
        dateText.setText(MA.getDateText(day, month, year));

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category1.getText().toString();
                selectCategory(category1);
            }
        });

        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category2.getText().toString();
                selectCategory(category2);
            }
        });

        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category3.getText().toString();
                selectCategory(category3);
            }
        });

        category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category4.getText().toString();
                selectCategory(category4);
            }
        });

        category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category5.getText().toString();
                selectCategory(category5);
            }
        });

        category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category6.getText().toString();
                selectCategory(category6);
            }
        });

        category7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category7.getText().toString();
                selectCategory(category7);
            }
        });

        category8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category8.getText().toString();
                selectCategory(category8);
            }
        });

        category9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category9.getText().toString();
                selectCategory(category9);
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
                if (!checkClick) {
                    moneyTextArea.setTextColor(getResources().getColor(R.color.colorExpenditure));
                    inExButton.setText("Expenditure");
                    inExButton.setBackgroundResource(R.color.colorExpenditure);
                    checkClick = true;
                    type = "ex";
                    setCategory();
                    selectCategory(null);
                }
                else {
                    moneyTextArea.setTextColor(getResources().getColor(R.color.colorIncome));
                    inExButton.setText("Income");
                    inExButton.setBackgroundResource(R.color.colorIncome);
                    checkClick = false;
                    type = "in";
                    setCategory();
                    selectCategory(null);
                }
            }
        });
    }

    private void setCategory() {
        category1 = (TextView) findViewById(R.id.categoryTextView1);
        category2 = (TextView) findViewById(R.id.categoryTextView2);
        category3 = (TextView) findViewById(R.id.categoryTextView3);
        category4 = (TextView) findViewById(R.id.categoryTextView4);
        category5 = (TextView) findViewById(R.id.categoryTextView5);
        category6 = (TextView) findViewById(R.id.categoryTextView6);
        category7 = (TextView) findViewById(R.id.categoryTextView7);
        category8 = (TextView) findViewById(R.id.categoryTextView8);
        category9 = (TextView) findViewById(R.id.categoryTextView9);

        category = new TextView[]{category1, category2, category3, category4, category5, category6, category7, category8, category9};
        int[] cIn = {R.string.cIn1, R.string.cIn2, R.string.cIn3};
        int[] cEx = {R.string.cEx1, R.string.cEx2, R.string.cEx3, R.string.cEx4, R.string.cEx5, R.string.cEx6, R.string.cEx7, R.string.cEx8, R.string.cEx9};

        for(int i=0; i<category.length; ++i) {
            if(type.equals("ex")) {
                if(i<cEx.length) {
                    category[i].setText(cEx[i]);
                    category[i].setEnabled(true);
                    continue;
                }
                category[i].setText("");
                category[i].setEnabled(false);
            }
            else {
                if(i<cIn.length) {
                    category[i].setText(cIn[i]);
                    category[i].setEnabled(true);
                    continue;
                }
                category[i].setText("");
                category[i].setEnabled(false);
            }
        }

        /*
        if (type.equals("ex")) {
            category1.setText(R.string.cEx1);
            category2.setText(R.string.cEx2);
            category3.setText(R.string.cEx3);
            category4.setText(R.string.cEx4);
            category5.setText(R.string.cEx5);
            category6.setText(R.string.cEx6);
            category7.setText(R.string.cEx7);
            category8.setText(R.string.cEx8);
            category9.setText(R.string.cEx9);

            category1.setEnabled(true);
            category2.setEnabled(true);
            category3.setEnabled(true);
            category4.setEnabled(true);
            category5.setEnabled(true);
            category6.setEnabled(true);
            category7.setEnabled(true);
            category8.setEnabled(true);
            category9.setEnabled(true);
        }
        else {
            category1.setText(R.string.cIn1);
            category2.setText(R.string.cIn2);
            category3.setText(R.string.cIn3);
            category4.setText("");
            category5.setText("");
            category6.setText("");
            category7.setText("");
            category8.setText("");
            category9.setText("");

            category1.setEnabled(true);
            category2.setEnabled(true);
            category3.setEnabled(true);
            category4.setEnabled(false);
            category5.setEnabled(false);
            category6.setEnabled(false);
            category7.setEnabled(false);
            category8.setEnabled(false);
            category9.setEnabled(false);
        }
        */
    }

    private void selectCategory(TextView cate) {
        for(int i=0; i<category.length; ++i) {
            if(cate == category[i])
                category[i].setBackgroundResource(R.color.colorCategorySelect);
            else
                category[i].setBackgroundResource(R.color.colorCategoryNoSelect);
        }

        /*
        category1.setBackgroundResource(R.color.colorCategoryNoSelect);
        category2.setBackgroundResource(R.color.colorCategoryNoSelect);
        category3.setBackgroundResource(R.color.colorCategoryNoSelect);
        category4.setBackgroundResource(R.color.colorCategoryNoSelect);
        category5.setBackgroundResource(R.color.colorCategoryNoSelect);
        category6.setBackgroundResource(R.color.colorCategoryNoSelect);
        category7.setBackgroundResource(R.color.colorCategoryNoSelect);
        category8.setBackgroundResource(R.color.colorCategoryNoSelect);
        category9.setBackgroundResource(R.color.colorCategoryNoSelect);
        */
    }

}
