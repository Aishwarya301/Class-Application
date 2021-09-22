package com.aishwarya.aclass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_student extends AppCompatActivity {
    private Button view_question,view_mcq,syllabus_review,email_queries,view_ann;
    TextView s_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("d_username");
        s_name = findViewById(R.id.student_name);
        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");
        view_question = findViewById(R.id.but2);
        view_mcq = findViewById(R.id.but1);
        syllabus_review = findViewById(R.id.but3);
        email_queries = findViewById(R.id.but6);
        view_ann = findViewById(R.id.but7);

        //s_name.setText(username);

        reference.child(extras.getString("d_email")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String dbusername = dataSnapshot.child("username").getValue().toString();
                    s_name.setText(dbusername);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        view_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),upload_que.class);
                startActivity(i);
            }
        });

        view_mcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),view_mcq.class);
                startActivity(i);
            }
        });

       syllabus_review.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i1 = new Intent(getApplicationContext(),syllabus_review_student.class);
               startActivity(i1);
           }
       });

       email_queries.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(),allmail.class);
               startActivity(i);
           }
       });

        view_ann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),view_announcement.class);
                startActivity(i);
            }
        });
    }
}
