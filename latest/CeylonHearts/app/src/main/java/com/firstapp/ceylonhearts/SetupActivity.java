package com.firstapp.ceylonhearts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {
    EditText Username,Fullname;
    Button create;
    Member user;
    DatabaseReference Refuser;
     ProgressDialog loadingBar;
    ProgressBar progressBar;

    final static int Gallery_Pick=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Username = (EditText) findViewById(R.id.setup_username);
        Fullname = (EditText) findViewById(R.id.setup_fullname);
        create = (Button) findViewById(R.id.setup_information_button);
        create = (Button) findViewById(R.id.setup_information_button);
        user = new Member();
        Refuser = FirebaseDatabase.getInstance().getReference().child("Users");




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setEmails(Username.getText().toString().trim());
                user.setPass(Fullname.getText().toString().trim());
                Refuser.push().setValue(user);
                Toast.makeText(SetupActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                AllowUserToSetUp();
            }
        });
    }


    private void AllowUserToSetUp() {
        Intent main = new Intent(SetupActivity.this,RegisterActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }

}
