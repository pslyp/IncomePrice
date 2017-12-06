package com.su.p1.incomeprice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Account extends Activity {

    DBHelper mDB;

    Button ok;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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
                    Toast.makeText(Account.this, "Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDB.addAccount(name.getText().toString());

                Intent intent = new Intent(Account.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
