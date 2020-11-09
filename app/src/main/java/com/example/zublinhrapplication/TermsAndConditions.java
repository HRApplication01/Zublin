package com.example.zublinhrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TermsAndConditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_conditions_view);

        final UserInfo userInfo = new UserInfo(getIntent());
    }
}