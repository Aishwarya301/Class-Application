package com.aishwarya.aclass;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_feedback_teacher extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    String[] subjects = {"CN", "DBMS", "TOC", "SEPM", "ISEE"};
    String subject;
    Button submit,view_feedback;
    Spinner spin1;
    TextView subtopic1,subtopic2,subtopic3,subtopic4,subtopic5,subtopic6,t;
    SeekBar seek1,seek2,seek3,seek4,seek5,seek6;
    String unique_name;
    RadioButton unit_no;
    RadioGroup units;
    TextView t1,t2,t3,t4,t5,t6;
    int sp1,sp2,sp3,sp4,sp5,sp6;
    int p1,p2,p3,p4,p5,p6;
    int tot1,tot2,tot3,tot4,tot5,tot6;
    int s1,s2,s3,s4,s5,s6,studentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback_teacher);
        spin1 = findViewById(R.id.spinner1);
        spin1.setOnItemSelectedListener(this);
        units = findViewById(R.id.units);
        subtopic1 = findViewById(R.id.subtopic1);
        subtopic2 = findViewById(R.id.subtopic2);
        subtopic3 = findViewById(R.id.subtopic3);
        subtopic4 = findViewById(R.id.subtopic4);
        subtopic5 = findViewById(R.id.subtopic5);
        subtopic6 = findViewById(R.id.subtopic6);
        view_feedback = findViewById(R.id.view_feedback);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);

        final DatabaseReference reference,reference2,reference3 = null;
        reference = FirebaseDatabase.getInstance().getReference().child("Syllabus_review");
        reference2 = FirebaseDatabase.getInstance().getReference().child("Review_Percent");

        ArrayAdapter s = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subjects);
        s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(s);

        view_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = units.getCheckedRadioButtonId();
                unit_no = (RadioButton)findViewById(selectedId);
                if(selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "No Unit Selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    unique_name = subject+ unit_no.getText().toString().trim();
                    reference.child(unique_name).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {
                                String st1 = dataSnapshot.child("subtopic1").getValue().toString();
                                subtopic1.setText(st1);
                                String st2 = dataSnapshot.child("subtopic2").getValue().toString();
                                subtopic2.setText(st2);
                                String st3 = dataSnapshot.child("subtopic3").getValue().toString();
                                subtopic3.setText(st3);
                                String st4 = dataSnapshot.child("subtopic4").getValue().toString();
                                subtopic4.setText(st4);
                                String st5 = dataSnapshot.child("subtopic5").getValue().toString();
                                subtopic5.setText(st5);
                                String st6 = dataSnapshot.child("subtopic6").getValue().toString();
                                subtopic6.setText(st6);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    reference2.child(unique_name).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {
                                //studentCount = Integer.valueOf( dataSnapshot.child("studetCount").getValue().toString());
                                //studentCount = studentCount+1;
                                s1 = Integer.valueOf( dataSnapshot.child("spercent1").getValue().toString());
                                int st1 = (s1*100)/300;
                                t1.setText(String.valueOf(st1));
                                s2 = Integer.valueOf( dataSnapshot.child("spercent2").getValue().toString());
                                int st2 = (s2*100)/300;
                                t2.setText(String.valueOf(st2));
                                s3 = Integer.valueOf( dataSnapshot.child("spercent3").getValue().toString());
                                int st3 = (s3*100)/300;
                                t3.setText(String.valueOf(st3));
                                s4 = Integer.valueOf( dataSnapshot.child("spercent4").getValue().toString());
                                int st4 = (s4*100)/300;
                                t4.setText(String.valueOf(st4));
                                s5 = Integer.valueOf( dataSnapshot.child("spercent5").getValue().toString());
                                int st5 = (s5*100)/300;
                                t5.setText(String.valueOf(st5));
                                s6 = Integer.valueOf( dataSnapshot.child("spercent6").getValue().toString());
                                int st6 = (s6*100)/300;
                                t6.setText(String.valueOf(st6));
                                //studentCount = Integer.valueOf( dataSnapshot.child("studentCount").getValue().toString());
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Table not exists", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //unique_name = subject.toString()+ unit_no.getText().toString().trim();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject = subjects[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}