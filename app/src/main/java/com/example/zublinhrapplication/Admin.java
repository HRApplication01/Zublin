package com.example.zublinhrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);


        PeriodicWorkRequest firebaseDBWorkRequest =
                new PeriodicWorkRequest.Builder(FirebaseDBWorker.class, 15, TimeUnit.MINUTES)
                        .build();
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("FirebaseDBWork", ExistingPeriodicWorkPolicy.KEEP, firebaseDBWorkRequest);


        final UserInfo userInfo = new UserInfo(getIntent());

        final Button btnApprovedIdeas = (Button) findViewById(R.id.btnAprrovedIdeas);
        final Button btnPendingIdeas = (Button) findViewById(R.id.btnPendingIdeas);
        final Button btnAddAdminUsers = (Button) findViewById(R.id.btnAddAdminUsers);
        final Button btnViewUsers = (Button) findViewById(R.id.btnViewUsers);

        btnApprovedIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ApprovedIdeaList.class);
                userInfo.setIntentStrings(i);
                startActivity(i);
            }
        });

        btnPendingIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PendingIdeaList.class);
                userInfo.setIntentStrings(i);
                startActivity(i);
            }
        });

        btnAddAdminUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddAdminUser.class);
                userInfo.setIntentStrings(i);
                startActivity(i);
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserList.class);
                userInfo.setIntentStrings(i);
                startActivity(i);
            }
        });
    }
}