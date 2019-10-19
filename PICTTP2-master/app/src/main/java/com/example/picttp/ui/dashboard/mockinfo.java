package com.example.picttp.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.picttp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mockinfo extends AppCompatActivity {
    private TextView mock_que,comp;
    private DatabaseReference mUsersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockinfo);
        final String user_ID = getIntent().getStringExtra("user_id");
        mock_que = (TextView)findViewById(R.id.request_mock);
        comp = (TextView)findViewById(R.id.request_compname1);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("company").child(user_ID);

        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Comp = dataSnapshot.child("comp_name").getValue().toString();
                String Mock = dataSnapshot.child("mock_q").getValue().toString();

                comp.setText(Comp);
                mock_que.setText(Mock);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
