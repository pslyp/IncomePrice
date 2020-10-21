package com.su.p1.incomeprice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.su.p1.incomeprice.DBHelper;
import com.su.p1.incomeprice.R;
import com.su.p1.incomeprice.model.Particular;

public class DescriptionActivity extends AppCompatActivity {

    private TextView amountMoneyTextView, memoTextView;

    private final int AMOUNT_REQUEST = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setTitle("");

        initialize();
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
        memoTextView = findViewById(R.id.memoEditText);
    }

    private boolean saveToDB() {
        String amount = amountMoneyTextView.getText().toString().trim();
        if (amount.length() > 0) {
            Particular particular = new Particular();
            DBHelper mDB = new DBHelper(this);

            double money = Double.parseDouble(amountMoneyTextView.getText().toString().trim());
            String memo = memoTextView.getText().toString().trim();

            particular.setDate();
            particular.setMoney(money);
            particular.setMemo(memo);
            particular.setCategoryID();

            mDB.addMoney(particular);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), R.string.enter_amount, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
