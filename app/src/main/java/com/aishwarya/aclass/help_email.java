package com.aishwarya.aclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class help_email extends AppCompatActivity {
    EditText etto , etsub,etmsg;
    Button btsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_email);
        etto = findViewById(R.id.et_to);
        etmsg = findViewById(R.id.et_msg);
        etsub = findViewById(R.id.et_sub);
        btsend = findViewById(R.id.bt_send);

        etto.setText("bhojaishwarya101@gmail.com");

        btsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{etto.getText().toString()});
                it.putExtra(Intent.EXTRA_SUBJECT, etsub.getText().toString());
                it.putExtra(Intent.EXTRA_TEXT, etmsg.getText());
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it, "Choose Mail App"));

            }
        });
    }
}