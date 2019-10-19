package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class aadhar extends AppCompatActivity {

    Button select_aadhar,upload_aadhar,select_resume,upload_resume,select_passport,upload_passport,select_pan,upload_pan;
    Button signin;
    TextView Notification;
    FirebaseStorage storage;
    FirebaseDatabase database;
    DatabaseReference rf,rootref;
    StorageReference storageReference,ref;
    Uri pdfUri;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar);

        firebaseauth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        rf = rootref = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child(firebaseauth.getCurrentUser().getUid());


      /*  selectFile=findViewById(R.id.select_file);
        Upload=findViewById(R.id.upload);*/
        signin = findViewById(R.id.etsignin);
        select_aadhar=findViewById(R.id.select_aadhar);
        upload_aadhar=findViewById(R.id.upload_aadhar);
        select_pan=findViewById(R.id.select_pan);
        upload_pan=findViewById(R.id.upload_pan);
        select_passport=findViewById(R.id.select_passport);
        upload_passport=findViewById(R.id.upload_passport);
        select_resume=findViewById(R.id.select_resume);
        upload_resume=findViewById(R.id.upload_resume);
        Notification=findViewById(R.id.notification);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(aadhar.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(aadhar.this,LOGIN_FORM.class));

            }
        });
        select_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
            }
        });
        select_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
            }
        });
        select_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
            }
        });
        select_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
            }
        });
        upload_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(view);
            }
        });
        upload_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(view);
            }
        });
        upload_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(view);
            }
        });
        upload_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(view);
            }
        });


    }
    public void btn_logout1(View view) {

        startActivity(new Intent(this,first.class));
        finish();
    }
    private void uploadfile(final Uri pdfUri, final String x){
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
   /*     final String filename=System.currentTimeMillis()+".pdf";
        final String filename1=System.currentTimeMillis()+"";*/
        final StorageReference storageReference=storage.getReference();



        storageReference.child(firebaseauth.getCurrentUser().getUid()).child(x).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                /*final StorageReference fileppath = storageReference.child(firebaseauth.getCurrentUser().getUid());
                fileppath.putFile(pdfUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            final String downloadUrl = (task.getResult().getStorage().getDownloadUrl()).toString();
                            rootref.child("Users").child(firebaseauth.getCurrentUser().getUid()).child(x).setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                                Toast.makeText(aadhar.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                            else
                                                Toast.makeText(aadhar.this,"Failed to Upload File",Toast.LENGTH_SHORT).show();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                    }
                })/*.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileppath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url= pdfUri.toString();
                                rootref.child("Users").child(firebaseauth.getCurrentUser().getUid()).child("Documents").setValue(url);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                })*/;

                String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                Log.d("123" ,"URL123" + url);
                DatabaseReference reference=database.getReference().child("Users");
               // reference.child(firebaseauth.getCurrentUser().getUid()).setValue(url);
                reference.child(firebaseauth.getCurrentUser().getUid()).child("Documents").child(x).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                          //  Uri uri = data.getData();
                            final StorageReference finalpath = storageReference.child(firebaseauth.getCurrentUser().getUid()).child(x);
                            finalpath.putFile(pdfUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    final String downloadUrl = String.valueOf(task.getResult().getStorage().getDownloadUrl());
                                    rootref.child("Users").child(firebaseauth.getCurrentUser().getUid()).child("Documents").child(x).setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            finalpath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String Url = uri.toString();

                                                    rootref.child("Users").child(firebaseauth.getCurrentUser().getUid()).child("Documents").child(x).setValue(Url);
                                                    Log.d("123" ,"URL123" + Url);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            Toast.makeText(aadhar.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(aadhar.this,"Failed to Upload File",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(aadhar.this,"Failed to Upload File",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectpdf();
        }
        else
            Toast.makeText(aadhar.this,"Please provide permission",Toast.LENGTH_LONG).show();
    }

    public void getpermission()
    {
        if(ContextCompat.checkSelfPermission(aadhar.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {

            selectpdf();
        }
        else
            ActivityCompat.requestPermissions(aadhar.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
    }
    public void upload(View V){
        if(pdfUri!=null) {
            switch (V.getId())
            {
                case R.id.upload_aadhar:
                    uploadfile(pdfUri,"aadhar");
                    break;
                case R.id.upload_pan:
                    uploadfile(pdfUri,"Pan");
                    break;
                case R.id.upload_passport:
                    uploadfile(pdfUri,"Passport");
                    break;
                case R.id.upload_resume:
                    uploadfile(pdfUri,"Resume");
                    break;

            }

        }
        else {
            Toast.makeText(aadhar.this, "Select File", Toast.LENGTH_SHORT).show();
        }
    }
    private void selectpdf()
    {

        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {

            pdfUri = data.getData();
            Notification.setText("File is Selected :" + data.getData().getLastPathSegment());

        } else
            Toast.makeText(aadhar.this, "Please select FILE", Toast.LENGTH_SHORT).show();
    }
}