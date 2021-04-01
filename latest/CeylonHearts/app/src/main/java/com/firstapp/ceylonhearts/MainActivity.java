package com.firstapp.ceylonhearts;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText Email,Password;
    Button btnsave;
    DatabaseReference reff;
    Member member;
    TextView LoginHere;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginHere=(TextView) findViewById(R.id.login);
        LoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Email = (EditText) findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        Password = (EditText) findViewById(R.id.password);
        btnsave = (Button) findViewById(R.id.save);
        member= new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setEmails(Email.getText().toString().trim());
                member.setPass(Password.getText().toString().trim());
                reff.push().setValue(member);
                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                AllowUserToLogin();
            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {


    }

    private void AllowUserToLogin() {
        Intent main = new Intent(MainActivity.this,SetupActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }

}