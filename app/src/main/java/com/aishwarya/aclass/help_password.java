package com.aishwarya.aclass;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class help_password extends AppCompatActivity {
    EditText email,old_pass,new_pass ;
    Button change_pass;
    String email_str,old_pass_str,new_pass_str;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_password);
        email = findViewById(R.id.email);
        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        change_pass = findViewById(R.id.button);

        reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_str = email.getText().toString().trim();
                old_pass_str = old_pass.getText().toString().trim();
                new_pass_str = new_pass.getText().toString().trim();

                reference.child(email_str).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String dbpass = dataSnapshot.child("password").getValue().toString();
                            if (old_pass_str.equals(dbpass)){
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("password",new_pass_str);
                                Toast.makeText(getApplicationContext(),"Password Updated Successfully",Toast.LENGTH_SHORT).show();
                                reference.child(email_str).updateChildren(hashMap);
                                okay();
                            }
                           else if (!old_pass_str.equals(dbpass)){
                                Toast.makeText(getApplicationContext(),"Wrong Old Password",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Account Exists",Toast.LENGTH_SHORT).show();
                        }

                    }
                    public void okay()
                    {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}