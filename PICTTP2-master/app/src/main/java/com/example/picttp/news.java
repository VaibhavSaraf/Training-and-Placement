package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class news extends AppCompatActivity {

    private TextView news1,high,desc;
    private DatabaseReference mUsersDatabase;
    Button verify_student;
    String user_id1;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final String user_ID = getIntent().getStringExtra("user_id");
        user_id1 = user_ID;

        news1 = (TextView)findViewById(R.id.request_new_related_to);
        high = (TextView)findViewById(R.id.request_new_highlight);
        desc = (TextView)findViewById(R.id.request_new);
        verify_student = (Button)findViewById(R.id.verify);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("News").child(user_ID);




        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String News1 = dataSnapshot.child("des1").getValue().toString();
                String Subj = dataSnapshot.child("subject1").getValue().toString();
                String title = dataSnapshot.child("title1").getValue().toString();


                news1.setText(title);
                high.setText(Subj);
                desc.setText(News1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        verify_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("123abc","lkjmnhj" );
                FirebaseAuth firebaseAuth;
                DatabaseReference rootref;
                firebaseAuth = FirebaseAuth.getInstance();
                rootref = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
                rootref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String flag1 = dataSnapshot.child("Flag").getValue().toString();
                        if(flag1.equals("1")) {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://pict.edu/"));
                            startActivity(intent);
                            Toast.makeText(news.this, "You are Placed", Toast.LENGTH_SHORT).show();
                            verify_student.setEnabled(false);
                        }
                        else {
                            Toast.makeText(news.this, "You are not Placed", Toast.LENGTH_SHORT).show();
                            verify_student.setEnabled(true);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
});
    }
}
