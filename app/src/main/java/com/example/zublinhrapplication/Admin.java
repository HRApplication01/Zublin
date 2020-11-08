package com.example.zublinhrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);

        final Button btnApprovedIdeas = (Button) findViewById(R.id.btnAprrovedIdeas);
        final Button btnPendingIdeas = (Button) findViewById(R.id.btnPendingIdeas);
        final Button btnAddAdminUsers = (Button) findViewById(R.id.btnAddAdminUsers);
        final Button btnViewUsers = (Button) findViewById(R.id.btnViewUsers);

        btnApprovedIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ApprovedIdeaList.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                i.putExtra("username", username);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

        btnPendingIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PendingIdeaList.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                i.putExtra("username", username);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

        btnAddAdminUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddAdminUser.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                i.putExtra("username", username);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserList.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                i.putExtra("username", username);
                i.putExtra("name", name);
                startActivity(i);
            }
        });
    }
}