package com.example.picttp.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picttp.R;
import com.example.picttp.news;
import com.example.picttp.page_3_4;
import com.example.picttp.send_news;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsFragment extends Fragment {
    View v;
    private RecyclerView mnewsList;

    private DatabaseReference mUsersDatabase;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        v= inflater.inflate(R.layout.activity_page_3_4, null);
        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("News");
        mnewsList = (RecyclerView)v.findViewById(R.id.news_list);
        mnewsList.setHasFixedSize(true);
        mnewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
    public  void onStart() {
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
                                Intent requestintent = new Intent(getContext(), news.class);
                                requestintent.putExtra("user_id",user1);
                                startActivity(requestintent);

                            }
                        });
                    }
                };
        mnewsList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }
    public class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView fullName;
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