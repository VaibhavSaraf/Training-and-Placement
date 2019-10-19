package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class page_3_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_admin);
    }
    public void btn_viewstudents(View view) {
        startActivity(new Intent(this,page_3_1_admin.class));
    }
    public void btn_addcompany(View view) {
        startActivity(new Intent(this,page_3_2_admin.class));
    }
    public void btn_viewcompany(View view) {
        startActivity(new Intent(this,page_3_3_admin.class));
    }
    public void btn_logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,first.class));
        finishAffinity();
    }
    public void btn_addnews(View view){
        startActivity(new Intent(this , page_3_4_admin.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.log_out1 :
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,first.class));
                Toast.makeText(this, "Logging Out...", Toast.LENGTH_SHORT).show();
                finishAffinity();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
