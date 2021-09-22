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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class syllabus_review_student extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] subjects = {"CN", "DBMS", "TOC", "SEPM", "ISEE"};
    String subject;
    Button submit,view_subtopic;
    Spinner spin1;
    TextView subtopic1,subtopic2,subtopic3,subtopic4,subtopic5,subtopic6,t;
    SeekBar seek1,seek2,seek3,seek4,seek5,seek6;
    String unique_name;
    RadioButton unit_no;
    RadioGroup units;
    int sp1,sp2,sp3,sp4,sp5,sp6;
    int p1,p2,p3,p4,p5,p6;
    int tot1,tot2,tot3,tot4,tot5,tot6;
    int s1,s2,s3,s4,s5,s6,studentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_review_student);
        spin1 = findViewById(R.id.spinner1);
        spin1.setOnItemSelectedListener(this);
        units = findViewById(R.id.units);
        subtopic1 = findViewById(R.id.subtopic1);
        subtopic2 = findViewById(R.id.subtopic2);
        subtopic3 = findViewById(R.id.subtopic3);
        subtopic4 = findViewById(R.id.subtopic4);
        subtopic5 = findViewById(R.id.subtopic5);
        subtopic6 = findViewById(R.id.subtopic6);
        seek1 = findViewById(R.id.seekBar1);
        seek2 = findViewById(R.id.seekBar2);
        seek3 = findViewById(R.id.seekBar3);
        seek4 = findViewById(R.id.seekBar4);
        seek5 = findViewById(R.id.seekBar5);
        seek6 = findViewById(R.id.seekBar6);
        submit = findViewById(R.id.submit);
        view_subtopic = findViewById(R.id.view_subtopic);

        final DatabaseReference reference,reference2,reference3 = null;
        reference = FirebaseDatabase.getInstance().getReference().child("Syllabus_review");
        reference2 = FirebaseDatabase.getInstance().getReference().child("Review_Percent");

               ArrayAdapter s = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subjects);
        s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(s);

        view_subtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = units.getCheckedRadioButtonId();
                unit_no = (RadioButton)findViewById(selectedId);
                if(selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "No Unit Selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    unique_name = subject.toString()+ unit_no.getText().toString().trim();
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
                                //studentCount = (int) dataSnapshot.child("studentCount").getValue();
                                //studentCount = studentCount+1;
                                /*s1 = (int) dataSnapshot.child("spercent1").getValue();
                                s2 = (int) dataSnapshot.child("spercent2").getValue();
                                s3 = (int) dataSnapshot.child("spercent3").getValue();
                                s4 = (int) dataSnapshot.child("spercent4").getValue();
                                s5 = (int) dataSnapshot.child("spercent5").getValue();
                                s6 = (int) dataSnapshot.child("spercent6").getValue();*/
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

        /*reference3.child(unique_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    int p1 = (int) dataSnapshot.child("spercent1").getValue();
                    t.setText(p1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        //unique_name = subject.toString()+ unit_no.getText().toString().trim();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = units.getCheckedRadioButtonId();
                unit_no = (RadioButton)findViewById(selectedId);

                reference2.child(unique_name).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            //studentCount = (int) dataSnapshot.child("studentCount").getValue();
                            //studentCount = studentCount+1;
                            s1 = Integer.valueOf( dataSnapshot.child("spercent1").getValue().toString());
                            s2 = Integer.valueOf( dataSnapshot.child("spercent2").getValue().toString());
                            s3 = Integer.valueOf( dataSnapshot.child("spercent3").getValue().toString());
                            s4 = Integer.valueOf( dataSnapshot.child("spercent3").getValue().toString());
                            s5 = Integer.valueOf( dataSnapshot.child("spercent3").getValue().toString());
                            s6 = Integer.valueOf( dataSnapshot.child("spercent3").getValue().toString());
                            studentCount = Integer.valueOf( dataSnapshot.child("studentCount").getValue().toString());
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


                seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp1 = s1+i;
                        //tot1 = (sp1/100)*100;
                        //Toast.makeText(getApplicationContext(),"seekbar progress: "+i, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
                    }
                });

                seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp2 = s2+i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp3 = s3+i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp4 = s4+i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                seek5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp5 = s5+i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                seek6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        sp6 = s6+i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                if(selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "No Unit Selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    ReviewPercent rp = new ReviewPercent(subject.toString(),unit_no.getText().toString().trim(),sp1,sp2,sp3,sp4,sp5,sp6,studentCount);
                    unique_name = subject.toString() + unit_no.getText().toString().trim();
                    reference2.child(unique_name.trim()).setValue(rp).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Feedback Entered Successfully!!", Toast.LENGTH_SHORT).show();
                            studentCount = studentCount + 1;
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        subject = subjects[position];
        //Toast.makeText(getApplicationContext(),subjects[position] , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}