package com.aishwarya.aclass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_teacher extends AppCompatActivity {
    TextView f_name;
    Button upload_mcq,upload_que,syllabus_review,view_feedback,announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("d_username");
        String email = extras.getString("d_email");
        f_name = findViewById(R.id.faculty_name);
        upload_mcq = findViewById(R.id.but3);
        upload_que = findViewById(R.id.but4);
        syllabus_review = findViewById(R.id.but6);
        view_feedback = findViewById(R.id.but1);
        announcement = findViewById(R.id.but2);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("LoginDetails");



       reference.child(extras.getString("d_email")).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists()){
                   String dbusername = dataSnapshot.child("username").getValue().toString();
                   f_name.setText(dbusername);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

       upload_mcq.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(),upload_mcq.class);
               startActivity(i);
           }
       });

        upload_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),upload_que_teacher.class);
                startActivity(i);
            }
        });

        syllabus_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),syllabus_review_teacher.class);
                startActivity(i);
            }
        });

        view_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),view_feedback_teacher.class);
                startActivity(i);
            }
        });

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),announcement.class);
                startActivity(i);
            }
        });
    }
}
