package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reviewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewer_view);

        final UserInfo userInfo = new UserInfo(getIntent());

        final Button btnApprovedIdeas = (Button) findViewById(R.id.btnAprrovedIdeas);
        final Button btnPendingIdeas = (Button) findViewById(R.id.btnPendingIdeas);

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
    }
}