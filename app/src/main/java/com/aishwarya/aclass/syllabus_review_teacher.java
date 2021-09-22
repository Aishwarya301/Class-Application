package com.aishwarya.aclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class syllabus_review_teacher extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] subjects = {"CN", "DBMS", "TOC", "SEPM", "ISEE"};
    String subject;
    Button add;
    Spinner spin1;
    EditText subtopic1,subtopic2,subtopic3,subtopic4,subtopic5,subtopic6;
    String s,unique_name;
    RadioButton unit_no;
    RadioGroup units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_review_teacher);
        spin1 = findViewById(R.id.spinner1);
        spin1.setOnItemSelectedListener(this);
        add = findViewById(R.id.add);
        subtopic1 = findViewById(R.id.subtopic1);
        subtopic2 = findViewById(R.id.subtopic2);
        subtopic3 = findViewById(R.id.subtopic3);
        subtopic4 = findViewById(R.id.subtopic4);
        subtopic5 = findViewById(R.id.subtopic5);
        subtopic6 = findViewById(R.id.subtopic6);
        units = findViewById(R.id.units);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("Syllabus_review");

        ArrayAdapter s1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subjects);
        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(s1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = units.getCheckedRadioButtonId();
                unit_no = (RadioButton)findViewById(selectedId);
                if(selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "No Unit Selected", Toast.LENGTH_SHORT).show();
                }

                        SyllabusReviewSubtopic srs = new SyllabusReviewSubtopic(subject.toString(), unit_no.getText().toString().trim(), subtopic1.getText().toString(), subtopic2.getText().toString(), subtopic3.getText().toString(), subtopic4.getText().toString(), subtopic5.getText().toString(), subtopic6.getText().toString());
                        unique_name = subject.toString() + unit_no.getText().toString().trim();

                        reference.child(unique_name.trim()).setValue(srs).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Subtopic Details Entered Successfully!!", Toast.LENGTH_SHORT).show();
                        }
                    });
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