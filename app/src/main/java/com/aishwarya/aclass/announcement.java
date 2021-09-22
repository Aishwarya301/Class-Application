package com.aishwarya.aclass;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class announcement extends AppCompatActivity {

    Button add,search,delete;
    EditText no,statement;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        add = findViewById(R.id.add);
        search = findViewById(R.id.search);
        delete = findViewById(R.id.delete);
        no = findViewById(R.id.no);
        statement = findViewById(R.id.statement);
        img = findViewById(R.id.img);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("Announcement");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anouncement ann =new Anouncement(no.getText().toString().trim(),statement.getText().toString());
                reference.child(no.getText().toString()).setValue(ann).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Announcement Details Successfully Entered",Toast.LENGTH_SHORT).show();

                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                            NotificationChannel channel = new NotificationChannel("My Notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager = getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }
                        addNotification();
                    }
                });
            }
        });

        //

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(no.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String stmt = dataSnapshot.child("statement").getValue().toString();
                            statement.setText(stmt);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(no.getText().toString()).removeValue();
                //DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Announcement").child("no").removeValue();
                //dr.removeValue();
                Toast.makeText(getApplicationContext(),"Announcement Record Deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notification");
        builder.setContentTitle("Class App "+no.getText().toString()+" Notification");
        builder.setContentText(statement.getText().toString());
        builder.setSmallIcon(R.drawable.ic_menu_slideshow);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());
    }
}