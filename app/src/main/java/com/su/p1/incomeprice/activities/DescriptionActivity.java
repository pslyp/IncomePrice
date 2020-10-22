package com.su.p1.incomeprice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.su.p1.incomeprice.CategoryManager;
import com.su.p1.incomeprice.DBHelper;
import com.su.p1.incomeprice.R;
import com.su.p1.incomeprice.dateTime;
import com.su.p1.incomeprice.model.Category;
import com.su.p1.incomeprice.model.Particular;

import java.util.Calendar;

public class DescriptionActivity extends AppCompatActivity {

    private LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private TextView text1, text2, text3, text4, text5, text6, text7, text8, text9;
    private TextView amountMoneyTextView, memoTextView;
    private Button inExButton;

    private CategoryManager cm;

    private boolean expenditure = true;
    private final int AMOUNT_REQUEST = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setTitle("");

        initialize();
        setCategory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.okAction) {
            if (saveToDB()) {
                startActivity(new Intent(DescriptionActivity.this, MainActivity.class));
                finish();
            }
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AMOUNT_REQUEST && resultCode == RESULT_OK) {
            String text = data.getStringExtra("mText");
            amountMoneyTextView.setText(text);
        }
    }

    private void initialize() {
        amountMoneyTextView = findViewById(R.id.amountMoneyTextView);
        amountMoneyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DescriptionActivity.this, AmountMoneyActivity.class);
                startActivityForResult(intent, AMOUNT_REQUEST);
            }
        });

        inExButton = findViewById(R.id.in_exButton);
        inExButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenditure) {
                    inExButton.setText("Expenditure");
                    inExButton.setBackgroundResource(R.color.colorExpenditure);
                } else {
                    inExButton.setText("Income");
                    inExButton.setBackgroundResource(R.color.colorIncome);
                }
                expenditure = !expenditure;
                cm.reload();
            }
        });

        l1 = findViewById(R.id.linearLayout1);
        l2 = findViewById(R.id.linearLayout2);
        l3 = findViewById(R.id.linearLayout3);
        l4 = findViewById(R.id.linearLayout4);
        l5 = findViewById(R.id.linearLayout5);
        l6 = findViewById(R.id.linearLayout6);
        l7 = findViewById(R.id.linearLayout7);
        l8 = findViewById(R.id.linearLayout8);
        l9 = findViewById(R.id.linearLayout9);

        img1 = findViewById(R.id.categoryImageView1);
        img2 = findViewById(R.id.categoryImageView2);
        img3 = findViewById(R.id.categoryImageView3);
        img4 = findViewById(R.id.categoryImageView4);
        img5 = findViewById(R.id.categoryImageView5);
        img6 = findViewById(R.id.categoryImageView6);
        img7 = findViewById(R.id.categoryImageView7);
        img8 = findViewById(R.id.categoryImageView8);
        img9 = findViewById(R.id.categoryImageView9);

        text1 = findViewById(R.id.categoryTextView1);
        text2 = findViewById(R.id.categoryTextView2);
        text3 = findViewById(R.id.categoryTextView3);
        text4 = findViewById(R.id.categoryTextView4);
        text5 = findViewById(R.id.categoryTextView5);
        text6 = findViewById(R.id.categoryTextView6);
        text7 = findViewById(R.id.categoryTextView7);
        text8 = findViewById(R.id.categoryTextView8);
        text9 = findViewById(R.id.categoryTextView9);

        memoTextView = findViewById(R.id.memoEditText);
    }

    private void setCategory() {
        cm = new CategoryManager(new LinearLayout[]{l1, l2, l3, l4, l5, l6, l7, l8, l9},
                new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9},
                new TextView[]{text1, text2, text3, text4, text5, text6, text7, text8, text9}).init();

    }

    private boolean saveToDB() {
        String amount = amountMoneyTextView.getText().toString().trim();
        if (amount.length() > 0) {
            Particular particular = new Particular();
            DBHelper mDB = new DBHelper(this);

            double money = Double.parseDouble(amountMoneyTextView.getText().toString().trim());
            String memo = memoTextView.getText().toString().trim();

            Log.e("MM", money+"");

            Calendar c = Calendar.getInstance();
            dateTime dt = new dateTime();

            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            particular.setDate(dt.getDateText(day, month, year));
            particular.setMoney(money);
            particular.setMemo(memo);
            particular.setCategoryID(cm.selected());

            mDB.addMoney(particular);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), R.string.enter_amount, Toast.LENGTH_SHORT).show();
            Log.e("ID", cm.selected());
            return false;
        }
    }

}
