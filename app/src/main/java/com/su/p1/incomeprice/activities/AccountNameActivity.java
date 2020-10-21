package com.su.p1.incomeprice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.su.p1.incomeprice.DBHelper;
import com.su.p1.incomeprice.R;

public class AccountNameActivity extends Activity {

    DBHelper mDB;

    Button ok;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_name);

        initialize();
    }

    private void initialize() {
        mDB = new DBHelper(this);

        ok = (Button) findViewById(R.id.okButton);
        name = (EditText) findViewById(R.id.nameAccEditText);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setCursorVisible(true);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length() == 0) {
                    Toast.makeText(AccountNameActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDB.addAccount(name.getText().toString());

                Intent intent = new Intent(AccountNameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
