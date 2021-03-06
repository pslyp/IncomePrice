package com.su.p1.incomeprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class inoutMoney extends AppCompatActivity {

    private TextView moneyArea, zero, one, two, three, four, five, six, seven, eight, nine, dot, delete;

    private boolean cDot;
    private String moneyText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inout_money);
        setTitle("");

        initialize();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent it = new Intent(inoutMoney.this, Description.class);
        startActivity(it);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("mText", moneyArea.getText());
        setResult(RESULT_OK, intent);
        finish();

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        zero = (TextView) findViewById(R.id.button0);
        one = (TextView) findViewById(R.id.button1);
        two = (TextView) findViewById(R.id.button2);
        three = (TextView) findViewById(R.id.button3);
        four = (TextView) findViewById(R.id.button4);
        five = (TextView) findViewById(R.id.button5);
        six = (TextView) findViewById(R.id.button6);
        seven = (TextView) findViewById(R.id.button7);
        eight = (TextView) findViewById(R.id.button8);
        nine = (TextView) findViewById(R.id.button9);
        dot = (TextView) findViewById(R.id.buttonDot);
        delete = (TextView) findViewById(R.id.buttonDelete);
        moneyArea = (TextView) findViewById(R.id.moneyTextView);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("9");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("-");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText(".");
            }
        });
    }

    private void setText(String number) {
        if (moneyArea.getText().toString().length() <= 8) {
            if (number.equals("-")) {
                if (moneyText.length() <= 1) {
                    moneyText = "";
                    moneyArea.setText(moneyText);
                    return;
                }
                if (moneyText.charAt(moneyText.length() - 1) == '.')
                    cDot = false;
                moneyText = moneyText.substring(0, moneyText.length() - 1);
            }
            else {
                if (number.equals(".")) {
                    if ((cDot && moneyText.length() >= 0) || moneyText.length() == 0)
                        return;
                    cDot = true;
                }
                if (number.equals("0") && moneyText.length() == 0)
                    return;

                moneyText += number;
            }
        moneyArea.setText(moneyText);
        }
    }
}
