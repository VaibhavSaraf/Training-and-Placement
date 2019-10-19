package com.example.picttp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class documents extends AppCompatActivity {

    RecyclerView recyclerView;
   // String user_ID;

    DatabaseReference mUsersDatabase;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_docu);
        final String user_ID = getIntent().getStringExtra("user_ID");

        Log.d("USER ID" , "user_id" + user_ID);

//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_ID).child("Documents");
 //        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
 //       StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(user_ID);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_ID).child("Documents");
   //     mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUsersDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                String fileName=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((myAdapter)recyclerView.getAdapter()).update(fileName,url);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String aadhar_link = dataSnapshot.child("aadhar").getValue().toString();
                String paasport_link = dataSnapshot.child("Passport").getValue().toString();
                String resume_link =  dataSnapshot.child("Resume").getValue().toString();
                String pan_link = dataSnapshot.child("Pan").getValue().toString();


                ArrayList <String> filename = new ArrayList <>();
                ArrayList<String> file_link = new ArrayList<>();
                filename.add("Aadhar Card");
                filename.add("Pan Card");
                filename.add("Reume");
                filename.add("Passport");

                file_link.add(aadhar_link);
                file_link.add(pan_link);
                file_link.add(resume_link);
                file_link.add(paasport_link);
                ((myAdapter)recyclerView.getAdapter()).update("Aadhar Card" , aadhar_link);
                ((myAdapter)recyclerView.getAdapter()).update("Pan Card" , pan_link);
                ((myAdapter)recyclerView.getAdapter()).update("Passport" , paasport_link);
                ((myAdapter)recyclerView.getAdapter()).update("Resume" , resume_link);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */
   /*     databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String fileName=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((myAdapter)recyclerView.getAdapter()).update(fileName,url);
                String aadhar1 = dataSnapshot.child("aadhar").getValue().toString();
                String passport = dataSnapshot.child("Passport").getValue().toString();
                String resume = dataSnapshot.child("Resume").getValue().toString();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(documents.this));
        myAdapter MyAdapter = new myAdapter(recyclerView,documents.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(MyAdapter);

    }
}
