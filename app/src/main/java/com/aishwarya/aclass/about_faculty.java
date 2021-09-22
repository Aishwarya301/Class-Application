package com.aishwarya.aclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about_faculty extends AppCompatActivity {
    Button about_institute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_faculty);
        about_institute = findViewById(R.id.button2);
        about_institute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),institute.class);
                startActivity(i);
            }
        });
    }
}