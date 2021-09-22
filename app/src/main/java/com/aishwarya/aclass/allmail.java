package com.aishwarya.aclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class allmail extends AppCompatActivity {
    private Button CN , ISEE , SDL , SEPM ,DBMS, TOC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allmail);
        CN = findViewById(R.id.CN);
        ISEE = findViewById(R.id.ISEE);
        SEPM = findViewById(R.id.SEPM);
        DBMS = findViewById(R.id.DBMS);
        TOC = findViewById(R.id.TOC);

        CN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allmail.this ,cnmail.class));
            }
        });
        TOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allmail.this ,tocmail.class));
            }
        });
        ISEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allmail.this ,iseemail.class));
            }
        });
        SEPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allmail.this ,sepmmail.class));
            }
        });
        DBMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(allmail.this ,dbmsmail.class));
            }
        });
    }
}