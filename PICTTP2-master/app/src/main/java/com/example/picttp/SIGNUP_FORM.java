package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SIGNUP_FORM extends AppCompatActivity {


    private EditText username,userphone,usermis,usermail,userpassword,usercpassword,bio,marks,projects,skills,lang,add;
    private FirebaseAuth firebaseauth;
    private Button login;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name="",phone="",mis="",mail="",password="",cpassword="",Bio="",Marks="",Projects="",Skills="",Lang="",Addr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("SignUp");
        setupuiviews();
        firebaseauth = FirebaseAuth.getInstance();
    ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                {
                    String useremail = usermail.getText().toString().trim();
                    String userpass = userpassword.getText().toString().trim();
                    Log.d("123","xyz1234");

                    firebaseauth.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                senduserdata();
                                Toast.makeText(SIGNUP_FORM.this, "ALL DETAILS ARE ENTERED", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SIGNUP_FORM.this,aadhar.class));
                            }

                            else
                                Toast.makeText(SIGNUP_FORM.this,"Registration Failed",Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }

    private void setupuiviews()
    {
        username = (EditText)findViewById(R.id.etname);
        userphone = (EditText)findViewById(R.id.etphone);
        usermis = (EditText)findViewById(R.id.etmis);
        usermail = (EditText)findViewById(R.id.etemail);
        userpassword = (EditText)findViewById(R.id.etpassword);
        usercpassword = (EditText)findViewById(R.id.etcpassword);
        login = (Button)findViewById(R.id.etlogin);
        bio = (EditText)findViewById(R.id.etbio);
        marks = (EditText)findViewById(R.id.etmarks);
        projects = (EditText)findViewById(R.id.etpro);
        skills = (EditText)findViewById(R.id.etskills);
        lang = (EditText)findViewById(R.id.etlang);
        add = (EditText)findViewById(R.id.etaddr);



    }

    private boolean validate()
    {
        boolean result = false;

         name = username.getText().toString();
         phone = userphone.getText().toString();
         mis = usermis.getText().toString();
         mail = usermail.getText().toString();
         password = userpassword.getText().toString();
         cpassword = usercpassword.getText().toString();
         Bio = bio.getText().toString();
         Marks = marks.getText().toString();
         Projects = projects.getText().toString();
         Skills = skills.getText().toString();
         Lang = lang.getText().toString();
         Addr = add.getText().toString();

        if(name.isEmpty() || phone.isEmpty() || mis.isEmpty() || mail.isEmpty() || password.isEmpty() || cpassword.isEmpty()
        || Bio.isEmpty() || Marks.isEmpty() || Projects.isEmpty() || Skills.isEmpty() || Lang.isEmpty() || Addr.isEmpty())
        {
            Toast.makeText(this, "Enter all Details",Toast.LENGTH_SHORT).show();
        }
        else {
            if (!password.equals(cpassword)) {
                Log.d("msg111", "validate: " + password + cpassword);
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            } else {

                result = true;

            }
        }
        return result;
    }

    private void senduserdata(){
        Log.d("123","xyz1235");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Log.d("123","xyz123");
        DatabaseReference myRef = firebaseDatabase.getReference();
        userinfo useri = new userinfo(name,phone,mis,mail,password,cpassword,Bio,Marks,Projects,Skills,Lang,Addr,"0");
        myRef.child("Users").child(firebaseauth.getUid()).setValue(useri); ///
    }
}
