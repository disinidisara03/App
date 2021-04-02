package com.firstapp.ceylonhearts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    Button donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        donate = (Button) findViewById(R.id.btn_donate);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messagetoshow();
            }
        });
    }

    private void Messagetoshow() {
        Toast.makeText(this, "Thank you for making a difference.Successfully Donated", Toast.LENGTH_SHORT).show();

    }
}