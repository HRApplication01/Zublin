package com.example.zublinhrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Reviewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewer_view);

        final Button btnApprovedIdeas = (Button) findViewById(R.id.btnAprrovedIdeas);
        final Button btnPendingIdeas = (Button) findViewById(R.id.btnPendingIdeas);

        btnApprovedIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

        btnPendingIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

    }
}