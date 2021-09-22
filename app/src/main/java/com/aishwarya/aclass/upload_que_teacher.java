package com.aishwarya.aclass;

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

public class upload_que_teacher extends AppCompatActivity {

    Button upload;
    EditText que_no,question;
    String unique_que;
    RadioButton s_name;
    RadioGroup subject_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_que_teacher);
        upload = findViewById(R.id.upload_button);
        que_no = findViewById(R.id.que_no);
        question = findViewById(R.id.question);
        subject_name = findViewById(R.id.subject_name);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("Question");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = subject_name.getCheckedRadioButtonId();
                s_name = (RadioButton)findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"No Subject Selected", Toast.LENGTH_SHORT).show();
                }

                QuestionInsertValues qiv =new QuestionInsertValues(s_name.getText().toString().trim(),que_no.getText().toString(),question.getText().toString());
                unique_que = s_name.getText().toString()+que_no.getText().toString();
                reference.child(unique_que.trim()).setValue(qiv).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Question Details Successfully Entered",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}