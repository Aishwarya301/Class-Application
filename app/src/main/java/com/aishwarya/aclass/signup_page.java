package com.aishwarya.aclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_page extends AppCompatActivity {
    private TextView login_text;
    Button signup;
    EditText email,username,password,id;
    RadioGroup choice;
    RadioButton choice_radiobutton;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        login_text = findViewById(R.id.text_login);
        email = findViewById(R.id.email);
        username  = findViewById(R.id.username);
        password = findViewById(R.id.password);
        id = findViewById(R.id.id);
        choice = findViewById(R.id.choice);
        signup = findViewById(R.id.signup_main);

        reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");
                reference.child("Username").push().setValue(username.getText().toString());
                reference.push().child("Password").setValue(password.getText().toString());*/

                int selectedId = choice.getCheckedRadioButtonId();
                choice_radiobutton = (RadioButton)findViewById(selectedId);

                SignupInsertValues siv =new SignupInsertValues(email.getText().toString(),username.getText().toString(),password.getText().toString(),id.getText().toString(),choice_radiobutton.getText().toString().trim());
                reference.child(email.getText().toString()).setValue(siv).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Details Successfully Entered",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
    }
}
