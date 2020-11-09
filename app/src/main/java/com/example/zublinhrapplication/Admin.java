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

        final UserInfo userInfo = new UserInfo(getIntent());

        final Button btnApprovedIdeas = (Button) findViewById(R.id.btnAprrovedIdeas);
        final Button btnPendingIdeas = (Button) findViewById(R.id.btnPendingIdeas);
        final Button btnAddAdminUsers = (Button) findViewById(R.id.btnAddAdminUsers);
        final Button btnViewUsers = (Button) findViewById(R.id.btnViewUsers);

        btnApprovedIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ApprovedIdeaList.class);
                userInfo.setString(i);
                startActivity(i);
            }
        });

        btnPendingIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PendingIdeaList.class);
                userInfo.setString(i);
                startActivity(i);
            }
        });

        btnAddAdminUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddAdminUser.class);
                userInfo.setString(i);
                startActivity(i);
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserList.class);
                userInfo.setString(i);
                startActivity(i);
            }
        });
    }
}