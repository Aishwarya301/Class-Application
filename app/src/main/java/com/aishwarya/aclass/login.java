package com.aishwarya.aclass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private TextView sign;
    String mail,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password = findViewById(R.id.password);
        login=findViewById(R.id.login);
        sign=findViewById(R.id.text_sign);

        final DatabaseReference reference;

        reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString().trim();
                pass = password.getText().toString().trim();

                /*if (uname.equals("student") && pass.equals("aish"))
                {
                    Intent i = new Intent(getApplicationContext(),login_student.class);
                    startActivity(i);
                }
                if (uname.equals("teacher") && pass.equals("aish"))
                {
                    Intent i = new Intent(getApplicationContext(),login_teacher.class);
                    startActivity(i);
                }*/

                    reference.child(mail).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                String dbpassword = dataSnapshot.child("password").getValue().toString();
                                String dbchoice = dataSnapshot.child("choice").getValue().toString();
                                String dbusername = dataSnapshot.child("username").getValue().toString();
                                String dbemail = dataSnapshot.child("email").getValue().toString();
                                if (pass.equals(dbpassword)) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    switch (dbchoice)
                                    {
                                        case "student": {
                                            Intent i1 = new Intent(getApplicationContext(),login_student.class);
                                            i1.putExtra("d_email",dbemail);
                                            i1.putExtra("d_username",dbusername);
                                            startActivity(i1);
                                            break;
                                        }
                                        case "faculty":{
                                            Intent i2 = new Intent(getApplicationContext(),login_teacher.class);
                                            i2.putExtra("d_email",dbemail);
                                            i2.putExtra("d_username", dbusername);
                                            startActivity(i2);
                                            break;
                                        }
                                        default:
                                            Toast.makeText(getApplicationContext(), "You should be either Student or Faculty Member", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "No Account Exists", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),signup_page.class);
                startActivity(i);
            }
        });

    }
}
