package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.picttp.ui.dashboard.companydata;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class page_3_2_admin extends AppCompatActivity {

    private EditText name_comp,ctc,type,role,url,des,mock_que,req;
   // FirebaseFirestore db = FirebaseFirestore.getInstance();
    String Name_Comp,Ctc,Type,Role,Url,Des,Mock_que,Req;
    private Button addcompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_2_admin);
        getSupportActionBar().setTitle("Add Company");
        setupuiviews();

        addcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("case1", "onClick: onclick");

                if(validate())

                {
                    Log.d("case11", "onClick: onclick");
                    sendcompanydata();
                   Toast.makeText(page_3_2_admin.this, "Company Added Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(page_3_2_admin.this , page_3_admin.class));
                }
                else
                    Toast.makeText(page_3_2_admin.this, "Failed to add Company", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupuiviews()
    {
        name_comp = (EditText)findViewById(R.id.name_comp);
        des = (EditText)findViewById(R.id.des1);
        ctc = (EditText)findViewById(R.id.ctc);
        type = (EditText)findViewById(R.id.type);
        role = (EditText)findViewById(R.id.role);
        url = (EditText)findViewById(R.id.url);
        mock_que = (EditText)findViewById(R.id.mock_que);
        addcompany = (Button)findViewById(R.id.etsubmit);
        req = (EditText)findViewById(R.id.requirement);

    }
    private boolean validate()
    {
        boolean result=false;
        Name_Comp = name_comp.getText().toString();
        Ctc = ctc.getText().toString();
        Type = type.getText().toString();
        Role = role.getText().toString();
        Url = url.getText().toString();
        Des = des.getText().toString();
        Req = req.getText().toString();
        Mock_que = mock_que.getText().toString();
        if(Name_Comp.isEmpty() || Ctc.isEmpty() || Type.isEmpty() || Role.isEmpty() || Url.isEmpty() || Des.isEmpty() || Mock_que.isEmpty() || Req.isEmpty())
        {
            Toast.makeText(this, "Enter all Details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        Log.d("result11", "validate: "+result);
        return result;
    }
    private void sendcompanydata()
    {
        Log.d("case123", "onClick: Inside");

//        String name = Name_Comp;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Log.d("case123", "onClick: Inside1");
        DatabaseReference myRef = firebaseDatabase.getReference();
        Log.d("case123", "onClick: Inside2");
        String name1=myRef.push().getKey();
        Log.d("case123", name1);
        companydata cmpinfo =  new companydata(Name_Comp,Ctc,Type,Role,Url,Des,Mock_que,Req);
        Log.d("case123", "onClick: Inside4");
        myRef.child("company").child(name1).setValue(cmpinfo);
        Log.d("case1234", "onClick: Done");


    }

}
