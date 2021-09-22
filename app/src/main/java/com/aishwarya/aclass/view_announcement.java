package com.aishwarya.aclass;

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

import static com.aishwarya.aclass.R.id.s_t;

public class view_announcement extends AppCompatActivity {

    Button view;
    EditText no;
    TextView s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcement);
        view  = findViewById(R.id.view_t);
        no = findViewById(R.id.no_t);
        s = findViewById(R.id.s_t);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Announcement");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message=getIntent().getStringExtra("message");
                        s.setText(message);
                        String code = no.getText().toString();
                        reference.child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    String stmt = dataSnapshot.child("statement").getValue().toString();
                                    s.setText(stmt);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"No Accouncement",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
    }
}