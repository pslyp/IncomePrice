package com.su.p1.incomeprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class nameAccount extends AppCompatActivity {

    DBHelper mDB;

    Button ok;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_account);

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
                //mDB.addAccount(name.getText().toString());

                Intent intent = new Intent(nameAccount.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
