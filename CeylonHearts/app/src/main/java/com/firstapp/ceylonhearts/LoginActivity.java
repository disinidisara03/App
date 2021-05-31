package com.firstapp.ceylonhearts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextView SignUpHere;
    EditText Email, Password;
    Button login;

    //MJB
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

    //MJB
    private  boolean validateEmail(EditText Email){
        String emailInput = Email.getText().toString();
        boolean x = Boolean.parseBoolean(null);
        if (emailInput.isEmpty()) {
            Email.setError("Field can't be empty.");
            x = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Email.setError("Input a valid Email Address.");
            x = false;
        } else{
            Email.setError(null);
            x = true;
        }
        return x;
    }

    //MJB
    private boolean validatePassword(EditText Password) {
        String passwordInput = Password.getText().toString();

        if (passwordInput.isEmpty()) {
            Password.setError("Field can't be Empty.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            Password.setError("Password should have Minimum eight characters, at least one letter and one number");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignUpHere = (TextView) findViewById(R.id.sign_up_txt_view);
        login = (Button) findViewById(R.id.login_button);
        Email = (EditText) findViewById(R.id.login_email);
        Password = (EditText) findViewById(R.id.login_password);
        SignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mujeeb
                if(validateEmail(Email) == true){
                    if (validatePassword(Password)==true){
                        Intent login = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(login);
                        finish();
                        /*sendtohomepage();*/
                    }else{
                        Toast errorToast = Toast.makeText(LoginActivity.this, "Please check your Password", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                }else{
                    Toast errorToast = Toast.makeText(LoginActivity.this, "Please check your Email Address.", Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }
        });
    }

    //Disini
/*    private void sendtohomepage() {
        Intent login = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(login);
        finish();
    }*/
}