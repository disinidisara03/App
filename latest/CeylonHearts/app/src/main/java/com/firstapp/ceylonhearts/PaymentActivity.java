package com.firstapp.ceylonhearts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity {

        EditText firstname,amounts;
        Button donation;
        DatabaseReference dreff;
        Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        donation = (Button) findViewById(R.id.messages);
        firstname = (EditText) findViewById(R.id.firstname);
        amounts = (EditText) findViewById(R.id.amount);
        dreff = FirebaseDatabase.getInstance().getReference().child("Amount");
        member = new Member();

        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setFirstname(firstname.getText().toString().trim());
                member.setAmount(amounts.getText().toString().trim());

                Toast.makeText(PaymentActivity.this, "Donated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}