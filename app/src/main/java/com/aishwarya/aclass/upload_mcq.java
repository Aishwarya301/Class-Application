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

public class upload_mcq extends AppCompatActivity {

    Button upload;
    EditText mcq_no,link;
    TextView title;
    String unique_link;
    RadioButton s_name;
    RadioGroup subject_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_mcq);

        upload = findViewById(R.id.upload_button);
        mcq_no = findViewById(R.id.mcq_no);
        link = findViewById(R.id.link);
        subject_name = findViewById(R.id.subject_name);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("MCQ");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = subject_name.getCheckedRadioButtonId();
                s_name = (RadioButton)findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"No Subject Selected", Toast.LENGTH_SHORT).show();
                }

               MCQInsertValues miv =new MCQInsertValues(s_name.getText().toString().trim(),mcq_no.getText().toString(),link.getText().toString());
               unique_link = s_name.getText().toString()+mcq_no.getText().toString();
                reference.child(unique_link.trim()).setValue(miv).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"MCQ Details Successfully Entered",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}