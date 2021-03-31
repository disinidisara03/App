package com.firstapp.ceylonhearts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PostActivity extends AppCompatActivity {

    ImageButton Add_image;
    Button fundraise;
    EditText comment,charity_name;
    private Uri filePath;
    private ImageView imageView;
    DatabaseReference reffe;
    Member member;
    String ImageUrl;


    private Toolbar mToolbar;
    FirebaseStorage storage;
    StorageReference storageReference;

    static final int Gallery_Pick=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(
                Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Add_image = (ImageButton) findViewById(R.id.Add_cause_image);
        fundraise = (Button) findViewById(R.id.fundraise);
        charity_name = (EditText) findViewById(R.id.name_of_charity);
        comment = (EditText) findViewById(R.id.cause);
        member= new Member();
        reffe= FirebaseDatabase.getInstance().getReference().child("Cause");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        fundraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setCharity(comment.getText().toString().trim());
                member.setCharity_cause(charity_name.getText().toString().trim());
                reffe.push().setValue(member);
                Toast.makeText(PostActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                uploadImage();
            }
        });
    }


    private void OpenGallery() {
        Intent galleryintent= new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, Gallery_Pick);

    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
        }
    }



    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                           DatabaseReference imageurls = FirebaseDatabase.getInstance().getReference().child("Images");

                                            HashMap<String,String> hashMap = new HashMap<>();
                                            hashMap.put("ImageUrls", String.valueOf(uri));

                                            imageurls.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(PostActivity.this, "Finally Completed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(PostActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }



}