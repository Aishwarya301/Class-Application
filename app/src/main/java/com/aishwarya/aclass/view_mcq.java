package com.aishwarya.aclass;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_mcq extends AppCompatActivity {

    TextView display_link;
    Button get_link;
    EditText mcq_no;
    String unique_link;
    RadioButton s_name;
    RadioGroup subject_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mcq);
        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("MCQ");
        display_link = findViewById(R.id.link);
        get_link = findViewById(R.id.get_link_button);
        subject_name = findViewById(R.id.subject_name);
        mcq_no = findViewById(R.id.mcq_no);

        get_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = subject_name.getCheckedRadioButtonId();
                s_name = (RadioButton)findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"No Subject Selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    unique_link = s_name.getText().toString().trim()+mcq_no.getText().toString().trim();
                    reference.child(unique_link).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                String dblink = dataSnapshot.child("link").getValue().toString();
                                display_link.setText(dblink);
                                Intent intent=new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(dblink));
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

    }
}