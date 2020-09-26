package com.example.zublinhrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Employee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_view);

        final Button btnMyIdea = (Button) findViewById(R.id.btnMyIdea);
        final Button btnInspired = (Button) findViewById(R.id.btnInspired);
        final Button btnProfile = (Button) findViewById(R.id.btnProfile);

        btnMyIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToEmployeeIdea = new Intent(v.getContext(), EmployeeIdea.class);
                startActivity(switchToEmployeeIdea);
                //todo
            }
        });

        btnInspired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });
    }
}