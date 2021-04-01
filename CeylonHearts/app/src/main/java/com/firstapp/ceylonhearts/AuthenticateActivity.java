package com.firstapp.ceylonhearts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class AuthenticateActivity extends AppCompatActivity {

    //Mujeeb
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

    //Mujeeb
    private  boolean validateEmail(EditText email){
        String emailInput = email.getText().toString();
        boolean x = Boolean.parseBoolean(null);
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty.");
            x = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Input a valid Email Address.");
            x = false;
        } else{
            email.setError(null);
            x = true;
        }
        return x;
    }

    //Mujeeb
    private boolean validatePassword(EditText password) {
        String passwordInput = password.getText().toString();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be Empty.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password should have Minimum eight characters, at least one letter and one number");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateid(EditText nic){
        String id = nic.getText().toString();

        if(id.isEmpty()){
            nic.setError("Field can't be Empty");
            return false;
        }else{
            nic.setError(null);
            return true;
        }
    }



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

                if(validateEmail(email)==true){
                    if (validatePassword(password)==true){
                        if(validateid(nic)==true){
                            member.setEmails(email.getText().toString().trim());
                            member.setPass(password.getText().toString().trim());
                            member.setNic(nic.getText().toString().trim());
                            member.setSamurdhi(samurdhi.getText().toString().trim());
                            dref.push().setValue(member);
                            Toast.makeText(AuthenticateActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            AllowUsertoupload();
                        }else {
                            Toast errorToast = Toast.makeText(AuthenticateActivity.this, "Please check your NIC", Toast.LENGTH_SHORT);
                            errorToast.show();
                        }
                    }else{
                        Toast errorToast = Toast.makeText(AuthenticateActivity.this, "Please check your Password", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                }else{
                    Toast errorToast = Toast.makeText(AuthenticateActivity.this, "Please check your Email Address.", Toast.LENGTH_SHORT);
                    errorToast.show();
                }


/*                member.setEmails(email.getText().toString().trim());
                member.setPass(password.getText().toString().trim());
                member.setNic(nic.getText().toString().trim());
                member.setSamurdhi(samurdhi.getText().toString().trim());
                dref.push().setValue(member);
                Toast.makeText(AuthenticateActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                AllowUsertoupload();*/
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