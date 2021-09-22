package com.aishwarya.aclass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class upload_que extends AppCompatActivity {
    private TextView question;
    private  Button click,view_que,upload_que;
    RadioButton s_name;
    EditText que_no;
    RadioGroup subject_name;
    private ImageView ans_upload;
    String  unique_que;
    private final int capture_image = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_que);
        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Question");
        question = findViewById(R.id.question);
        click = findViewById(R.id.click);
        view_que = findViewById(R.id.view_que);
        upload_que = findViewById(R.id.upload_que);
        ans_upload = findViewById(R.id.image);
        subject_name = findViewById(R.id.subject_name);
        que_no = findViewById(R.id.que_no);

        view_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = subject_name.getCheckedRadioButtonId();
                s_name = (RadioButton)findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"No Subject Selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    unique_que = s_name.getText().toString().trim()+que_no.getText().toString().trim();
                    reference.child(unique_que).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                String dbquestion = dataSnapshot.child("question").getValue().toString();
                                question.setText(dbquestion);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,capture_image);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == capture_image){
            if (data!= null && data.getExtras()!= null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ans_upload.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
