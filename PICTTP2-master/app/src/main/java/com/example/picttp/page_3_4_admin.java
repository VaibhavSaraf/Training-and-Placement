package com.example.picttp;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class page_3_4_admin extends AppCompatActivity {

    private Button add_news,send;
    private EditText title,subject,description;
    private String title_,subject_,description_;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_4_admin);
        setupuiviews();


        add_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validate())
                {
                    addnews();
                    Toast.makeText(page_3_4_admin.this ,"News added successfully" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(page_3_4_admin.this , page_3_admin.class);
                    startActivity(intent);

                }

            }
        });


    }
    private void setupuiviews()
    {
        title = (EditText)findViewById(R.id.notice_related);
        subject = (EditText)findViewById(R.id.sub);
        description = (EditText)findViewById(R.id.request_des);
        add_news = (Button)findViewById(R.id.add_news);
    }
    private boolean Validate(){
        boolean result = false;

        title_ = title.getText().toString();
        subject_ = subject.getText().toString();
        description_ = description.getText().toString();

        if(title_.isEmpty() || subject_.isEmpty() || description_.isEmpty())
        {
            Toast.makeText(this,"Enter All Details!!" , Toast.LENGTH_SHORT).show();
        }
        else
            result = true;
        return result;
    }
    private void addnews(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        String name1 = databaseReference.push().getKey();
        send_news Send_news = new send_news(title_,subject_,description_);
        databaseReference.child("News").child(name1).setValue(Send_news);


    }
}
