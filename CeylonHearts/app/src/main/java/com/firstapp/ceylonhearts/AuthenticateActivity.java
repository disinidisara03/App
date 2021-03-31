package com.firstapp.ceylonhearts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthenticateActivity extends AppCompatActivity {

    EditText email,password,nic,samurdhi;
    Button authenticate;
    FirebaseAuth mAuth;
    DatabaseReference dref;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        nic = (EditText) findViewById(R.id.nic);
        samurdhi = (EditText) findViewById(R.id.samurdhi);
        authenticate = (Button) findViewById(R.id.save);
        member = new Member();
        dref = FirebaseDatabase.getInstance().getReference().child("Authentication");

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setEmails(email.getText().toString().trim());
                member.setPass(password.getText().toString().trim());
                member.setNic(nic.getText().toString().trim());
                member.setSamurdhi(samurdhi.getText().toString().trim());
                dref.push().setValue(member);
                Toast.makeText(AuthenticateActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                AllowUsertoupload();
            }
        });



    }


    private void reload() {
    }

    private void AllowUsertoupload() {
        Intent main = new Intent(AuthenticateActivity.this,PostActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }
}