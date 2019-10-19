package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentinfo extends AppCompatActivity {
    public TextView name,Mis,bio,add,mobile,mail,qual,project,skills,lang;
    private DatabaseReference mUsersDatabase;
    RadioGroup group1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    // String user_ID;
    String user_id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);
       final String user_ID = getIntent().getStringExtra("user_id");
       user_id1 = user_ID;


        name = findViewById(R.id.request_fullname);
        Mis = findViewById(R.id.request_mis);
        bio = findViewById(R.id.request_bio);
        add = findViewById(R.id.request_address);
        mobile = findViewById(R.id.request_mobile);
        mail = findViewById(R.id.request_mail);
        qual = findViewById(R.id.request_qualification);
        project = findViewById(R.id.request_project);
        skills = findViewById(R.id.request_skills);
        lang = findViewById(R.id.request_language);
        group1 = findViewById(R.id.radio_);


       group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               Log.d("123abc","lkjmnhj" );

               databaseReference = FirebaseDatabase.getInstance().getReference();

               View radioButton = group1.findViewById(i);
               int index = group1.indexOfChild(radioButton);
               switch (index){
                   case 0:
                           databaseReference.child("Users").child(user_id1).child("Flag").setValue("1");
                       Log.d("123abc","placed" );
                       break;
                   case 1:
                           databaseReference.child("Users").child(user_id1).child("Flag").setValue("0");
                       Log.d("123abc","not placed" );
                       break;
               }
           }
       });

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_ID);
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String fullname = dataSnapshot.child("username").getValue().toString();
                String mis = dataSnapshot.child("usermis").getValue().toString();
                String Bio = dataSnapshot.child("bio").getValue().toString();
                String Add = dataSnapshot.child("address").getValue().toString();
                String Mobile = dataSnapshot.child("userphone").getValue().toString();
                String Mail = dataSnapshot.child("usermail").getValue().toString();
                String Qual = dataSnapshot.child("marks").getValue().toString();
                String Project = dataSnapshot.child("projects").getValue().toString();
                String Skills = dataSnapshot.child("skills").getValue().toString();
                String Lang = dataSnapshot.child("lang").getValue().toString();
                Mis.setText(mis);
                name.setText(fullname);
                bio.setText(Bio);
                add.setText(Add);
                mobile.setText(Mobile);
                mail.setText(Mail);
                qual.setText(Qual);
                project.setText(Project);
                skills.setText(Skills);
                lang.setText(Lang);
                if(dataSnapshot.child("Flag").getValue().toString().equals("0"))
                {

                    Log.i("shubham","notplaced");
                    ((RadioButton)findViewById(R.id.not_placed)).setChecked(true);
                }
                else
                {
                    Log.i("shubham","placed");
                    ((RadioButton)findViewById(R.id.placed)).setChecked(true);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void btn_verify(View view) {
        Log.d("case1","in verify");
        Intent intent= new Intent(studentinfo.this,documents.class);
        String user1 = user_id1;
       intent.putExtra("user_ID",user_id1);
       Log.d("USER_ID" , "in Student" + user_id1);
       startActivity(intent);
        Log.d("case1","out verify");
    }

    public void onRadioButtonClicked(View view){

    }

}

