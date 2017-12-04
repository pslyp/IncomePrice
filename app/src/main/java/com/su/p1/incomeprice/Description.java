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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.su.p1.incomeprice.model.Money;

public class Description extends AppCompatActivity {

    private dateTime dtDes;
    private DBHelper mDB;

    private Button inExButton;
    private EditText memo;
    private TextView moneyTextArea, dateText, category1, category2, category3, category4, category5, category6, category7, category8, category9;
    private ImageView calendarImage, cI1, cI2, cI3, cI4, cI5, cI6, cI7, cI8, cI9;
    private LinearLayout L1, L2, L3, L4, L5, L6, L7, L8, L9;

    private boolean checkClick;
    private int picture;
    private String title, date, moneyText = "", type = "in";
    private int[] picIn, picEx;
    private ImageView[] cateGImage;
    private TextView[] cateGText;
    private LinearLayout[] linear;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setTitle("");

        initialize();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent it = new Intent(Description.this, MainActivity.class);
        startActivity(it);
        finish();
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
                return new DatePickerDialog(this, dateSetListener, dtDes.getYear(), dtDes.getMonth(), dtDes.getDay());
        }

        return super.onCreateDialog(id);
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay) {
            setDate(selectDay, selectMonth, selectYear);
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
            Money money = new Money();
            mDB = new DBHelper(this);

            if (memo.getText().toString().length() == 0)
                memo.setText("-");

            if (title == null) {
                Toast.makeText(Description.this, "Select category", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }

            money.setDate(date);
            money.setPictureList(picture);
            money.setTitle(title);
            money.setMemo(memo.getText().toString());
            money.setMoney(Double.parseDouble(moneyText));
            money.setType(type);

            mDB.addMoney(money);

            Intent it = new Intent(Description.this, MainActivity.class);
            startActivity(it);
            finish();
        } else
            Toast.makeText(Description.this, "Enter amount", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }


    private void initialize() {
        dtDes = new dateTime();

        inExButton = (Button) findViewById(R.id.in_exButton);
        memo = (EditText) findViewById(R.id.memoEditText);
        calendarImage = (ImageView) findViewById(R.id.calendarImageView);
        dateText = (TextView) findViewById(R.id.dateTextView);
        moneyTextArea = (TextView) findViewById(R.id.amountMoneyTextView);

        setCategory();
        setDate(dtDes.getDay(), dtDes.getMonth(), dtDes.getYear());

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category1.getText().toString();
                selectCategory(L1);
            }
        });

        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category2.getText().toString();
                selectCategory(L2);
            }
        });

        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category3.getText().toString();
                selectCategory(L3);
            }
        });

        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category4.getText().toString();
                selectCategory(L4);
            }
        });

        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category5.getText().toString();
                selectCategory(L5);
            }
        });

        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category6.getText().toString();
                selectCategory(L6);
            }
        });

        L7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category7.getText().toString();
                selectCategory(L7);
            }
        });

        L8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category8.getText().toString();
                selectCategory(L8);
            }
        });

        L9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = category9.getText().toString();
                selectCategory(L9);
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
                    inExButton.setBackgroundResource(R.color.colorRed);
                    checkClick = true;
                    type = "ex";
                    setCategory();
                    selectCategory(null);
                } else {
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
        cI1 = (ImageView) findViewById(R.id.categoryImageView1);
        cI2 = (ImageView) findViewById(R.id.categoryImageView2);
        cI3 = (ImageView) findViewById(R.id.categoryImageView3);
        cI4 = (ImageView) findViewById(R.id.categoryImageView4);
        cI5 = (ImageView) findViewById(R.id.categoryImageView5);
        cI6 = (ImageView) findViewById(R.id.categoryImageView6);
        cI7 = (ImageView) findViewById(R.id.categoryImageView7);
        cI8 = (ImageView) findViewById(R.id.categoryImageView8);
        cI9 = (ImageView) findViewById(R.id.categoryImageView9);

        category1 = (TextView) findViewById(R.id.categoryTextView1);
        category2 = (TextView) findViewById(R.id.categoryTextView2);
        category3 = (TextView) findViewById(R.id.categoryTextView3);
        category4 = (TextView) findViewById(R.id.categoryTextView4);
        category5 = (TextView) findViewById(R.id.categoryTextView5);
        category6 = (TextView) findViewById(R.id.categoryTextView6);
        category7 = (TextView) findViewById(R.id.categoryTextView7);
        category8 = (TextView) findViewById(R.id.categoryTextView8);
        category9 = (TextView) findViewById(R.id.categoryTextView9);

        L1 = (LinearLayout) findViewById(R.id.Linear1);
        L2 = (LinearLayout) findViewById(R.id.Linear2);
        L3 = (LinearLayout) findViewById(R.id.Linear3);
        L4 = (LinearLayout) findViewById(R.id.Linear4);
        L5 = (LinearLayout) findViewById(R.id.Linear5);
        L6 = (LinearLayout) findViewById(R.id.Linear6);
        L7 = (LinearLayout) findViewById(R.id.Linear7);
        L8 = (LinearLayout) findViewById(R.id.Linear8);
        L9 = (LinearLayout) findViewById(R.id.Linear9);

        cateGImage = new ImageView[] {cI1, cI2, cI3, cI4, cI5, cI6, cI7, cI8, cI9};
        cateGText = new TextView[] {category1, category2, category3, category4, category5, category6, category7, category8, category9};
        linear = new LinearLayout[] {L1, L2, L3, L4, L5, L6, L7, L8, L9};
        int[] cIn = {R.string.cIn1, R.string.cIn2, R.string.cIn3};
        int[] cEx = {R.string.cEx1, R.string.cEx2, R.string.cEx3, R.string.cEx4, R.string.cEx5, R.string.cEx6, R.string.cEx7, R.string.cEx8, R.string.cEx9};
        picIn = new int[] {R.drawable.salary, R.drawable.business, R.drawable.extra};
        picEx = new int[] {R.drawable.transport, R.drawable.travel, R.drawable.utilitys, R.drawable.entertainment, R.drawable.shopping, R.drawable.bills, R.drawable.personal, R.drawable.food, R.drawable.other};

        for (int i = 0; i < cateGText.length; ++i) {
            title = null;
            if (type.equals("ex")) {
                if (i < cEx.length) {
                    cateGImage[i].setImageResource(picEx[i]);
                    cateGImage[i].setVisibility(View.VISIBLE);
                    cateGText[i].setText(cEx[i]);
                    cateGText[i].setEnabled(true);
                    continue;
                }
                cateGImage[i].setVisibility(View.INVISIBLE);
                cateGText[i].setText("");
                cateGText[i].setEnabled(false);
            } else {
                if (i < cIn.length) {
                    cateGImage[i].setImageResource(picIn[i]);
                    cateGImage[i].setVisibility(View.VISIBLE);
                    cateGText[i].setText(cIn[i]);
                    cateGText[i].setEnabled(true);
                    continue;
                }
                cateGImage[i].setVisibility(View.INVISIBLE);
                cateGText[i].setText("");
                cateGText[i].setEnabled(false);
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

    private void selectCategory(LinearLayout ll) {
        for (int i = 0; i < linear.length; ++i) {
            if (ll == linear[i]) {
                if(type.equals("in"))
                    picture = picIn[i];
                else
                    picture = picEx[i];
                linear[i].setBackgroundResource(R.color.colorCategorySelect);
            }
            else
                linear[i].setBackgroundResource(R.color.colorCategoryNoSelect);
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

    private void setDate(int day, int month, int year) {
        date = dtDes.getDateText(day, month, year);
        dateText.setText(dtDes.getDateTextMonth(day, month, month));
    }

}
