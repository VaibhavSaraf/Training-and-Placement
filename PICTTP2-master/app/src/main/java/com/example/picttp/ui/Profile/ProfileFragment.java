package com.example.picttp.ui.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.picttp.LOGIN_FORM;
import com.example.picttp.R;
import com.example.picttp.aadhar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    View v;
    public TextView name,Mis,bio,add,mobile,mail,qual,project,skills,lang;
    private DatabaseReference mUsersDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_myprofile1, null);

       name = v.findViewById(R.id.request_fullname);
        Mis = v.findViewById(R.id.request_mis);
        bio = v.findViewById(R.id.request_bio);
        add = v.findViewById(R.id.request_address);
        mobile = v.findViewById(R.id.request_mobile);
        mail = v.findViewById(R.id.request_mail);
        qual = v.findViewById(R.id.request_qualification);
        project = v.findViewById(R.id.request_project);
        skills = v.findViewById(R.id.request_skills);
        lang = v.findViewById(R.id.request_language);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               String fullname = dataSnapshot.child("username").getValue().toString();
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}