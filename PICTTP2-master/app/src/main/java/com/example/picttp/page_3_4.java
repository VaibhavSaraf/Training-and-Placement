package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class page_3_4 extends AppCompatActivity {

    private RecyclerView mnewsList;

    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_4);

        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("News");
        mnewsList = (RecyclerView)findViewById(R.id.news_list);
        mnewsList.setHasFixedSize(true);
        mnewsList.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<send_news> options=new FirebaseRecyclerOptions.Builder<send_news>()
                .setQuery(mUsersDatabase,send_news.class).build();

        FirebaseRecyclerAdapter<send_news, page_3_4.UsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<send_news, page_3_4.UsersViewHolder>(options)
                {
                    @NonNull
                    @Override
                    public page_3_4.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_news_single_layout,viewGroup,false);
                        page_3_4.UsersViewHolder viewHolder = new page_3_4.UsersViewHolder(view);
                        return viewHolder;
                    }

                    protected void onBindViewHolder(@NonNull page_3_4.UsersViewHolder usersViewHolder, int position, @NonNull send_news users) {
                        //usersViewHolder.usermail.setText(users.getEmail());
                        usersViewHolder.fullName.setText(users.getSubject1());
                        //usersViewHolder.profileIv.//Image Loading Needs Picasso library

                        final String user_ID=getRef(position).getKey();
                        usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String user1=user_ID;
                                Intent requestintent = new Intent(page_3_4.this,news.class);
                                requestintent.putExtra("user_id",user1);
                                startActivity(requestintent);

                            }
                        });
                    }
                };
        mnewsList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        public View mView;
        public TextView fullName;
        CircleImageView profileIv;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            // email= itemView.findViewById(R.id.users_single_email);
            fullName=itemView.findViewById(R.id.news_name);
            profileIv=itemView.findViewById(R.id.imageView1);
            mView=itemView;
        }
    }
}
