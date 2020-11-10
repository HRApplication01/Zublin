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

        final UserInfo userInfo = new UserInfo(getIntent());

        final Button btnMyIdea = (Button) findViewById(R.id.btnMyIdea);
        final Button btnInspired = (Button) findViewById(R.id.btnInspired);
        final Button btnProfile = (Button) findViewById(R.id.btnProfile);
        final Button btnViewEditIdeas = (Button) findViewById(R.id.btnViewEditIdeas);

        btnMyIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToEmployeeIdea = new Intent(v.getContext(), EmployeeIdea.class);
                userInfo.setIntentStrings(switchToEmployeeIdea);
                startActivity(switchToEmployeeIdea);
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

        btnViewEditIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToEmployeeIdeaListView = new Intent(v.getContext(), EmployeeIdeaList.class);
                userInfo.setIntentStrings(switchToEmployeeIdeaListView);
                startActivity(switchToEmployeeIdeaListView);
            }
        });
    }
}