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
        final Button btnViewEditIdeas = (Button) findViewById(R.id.btnViewEditIdeas);

        btnMyIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToEmployeeIdea = new Intent(v.getContext(), EmployeeIdea.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                switchToEmployeeIdea.putExtra("username", username);
                switchToEmployeeIdea.putExtra("name", name);
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
        btnViewEditIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToEmployeeIdeaListView = new Intent(v.getContext(), EmployeeIdeaList.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                switchToEmployeeIdeaListView.putExtra("username", username);
                switchToEmployeeIdeaListView.putExtra("name", name);
                startActivity(switchToEmployeeIdeaListView);
            }
        });
    }
}