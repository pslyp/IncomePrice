package com.su.p1.incomeprice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.su.p1.incomeprice.DBHelper;
import com.su.p1.incomeprice.R;
import com.su.p1.incomeprice.model.Particular;

public class DetailsActivity extends AppCompatActivity {

    private DBHelper mDB;
    private Particular m;

    private Button delete;
    private TextView title, memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle("");

        initialize();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent main = new Intent(DetailsActivity.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    private void initialize() {
        m = new Particular();
        mDB = new DBHelper(this);

        delete = (Button) findViewById(R.id.deleteButton);
        title = (TextView) findViewById(R.id.titleTextView);
        memo = (TextView) findViewById(R.id.memoTextView);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("Id", 0);

        m = mDB.getMoney(String.valueOf(id));

        title.setText(m.getTitle());
        memo.setText(m.getMemo());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDB.deleteMoney(String.valueOf(id));
                Intent main = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
}
