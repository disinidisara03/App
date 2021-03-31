package com.firstapp.ceylonhearts;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //Mujeeb
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{6,20}" +
            "$");

    EditText Email, Password;
    Button btnsave;
    DatabaseReference reff;
    Member member;
    TextView LoginHere;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mujeeb
        private boolean validateEmail(){
            String emailInput = Email.getEditText().getText().toString().trim();

            if (emailInput.isEmpty()) {
                Email.setError("Field can't be empty.");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                Email.setError("Input a valid Email Address.");
            } else{
                Email.setError(null);
                return true;
            }
        };

        //Mujeeb
        private boolean validatePassword() {
            String passwordInput = Password.getEditableText().getText().toString().trim();

            if (passwordInput.isEmpty()) {
                Password.setError("Field can't be Empty.");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                Password.setError("Password too weak!");
                return false;
            } else {
                Password.setError(null);
                return true;
            }
        }

        LoginHere = (TextView) findViewById(R.id.login);
        LoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Email = (EditText) findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        Password = (EditText) findViewById(R.id.password);
        btnsave = (Button) findViewById(R.id.save);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();
                validatePassword();
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
        if (currentUser != null) {
            reload();
        }
    }

    private void reload() {


    }

    private void AllowUserToLogin() {
        Intent main = new Intent(MainActivity.this, SetupActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }

}