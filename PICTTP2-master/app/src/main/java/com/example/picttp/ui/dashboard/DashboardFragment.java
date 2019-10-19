package com.example.picttp.ui.dashboard;

import android.content.Intent;
import android.icu.text.Transliterator;
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
import com.example.picttp.page_3_3_admin;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardFragment extends Fragment {
    @Nullable
    private RecyclerView mcompList;
    View v;
    private DatabaseReference mUsersDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        //return inflater.inflate(R.layout.activity_page_3_1_admin, null);

        v= inflater.inflate(R.layout.comp_recyclerview, null);
        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("company");
        mcompList = (RecyclerView)v.findViewById(R.id.company_list);
        mcompList.setHasFixedSize(true);

        mcompList.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<companydata> options=new FirebaseRecyclerOptions.Builder<companydata>()
                .setQuery(mUsersDatabase,companydata.class).build();

        FirebaseRecyclerAdapter<companydata, DashboardFragment.UsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<companydata, UsersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i, @NonNull companydata companydata) {

                        usersViewHolder.comp_name.setText(companydata.getComp_name());


                        final String user_ID=getRef(i).getKey();
                        usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent requestintent = new Intent(getContext(), mockinfo.class);
                                requestintent.putExtra("user_id",user_ID);
                                startActivity(requestintent);
                            }
                        });
                    }



                    @NonNull
                    @Override
                    public DashboardFragment.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_mock_single_layout,viewGroup,false);
                        DashboardFragment.UsersViewHolder viewHolder = new DashboardFragment.UsersViewHolder(view);
                        return viewHolder;
                    }

               /*     protected void onBindViewHolder(@NonNull DashboardFragment.UsersViewHolder usersViewHolder, int position, @NonNull companydata users) {

                        usersViewHolder.comp_name.setText(users.getComp_name());


                        final String user_ID=getRef(position).getKey();
                        usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent requestintent = new Intent(getContext(), mockinfo.class);
                                requestintent.putExtra("user_id",user_ID);
                                startActivity(requestintent);
                            }
                        });
                    }*/
                };

        mcompList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView comp_name;
        CircleImageView profileIv;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            // email= itemView.findViewById(R.id.users_single_email);
            comp_name=itemView.findViewById(R.id.comp_name1);
            profileIv=itemView.findViewById(R.id.imageView1);
            mView=itemView;
        }
    }
}