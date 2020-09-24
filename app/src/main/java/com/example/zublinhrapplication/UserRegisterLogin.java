package com.example.zublinhrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegisterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_login_view);

        //setup button
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        //setup text fields
        final TextView edtName = (TextView) findViewById(R.id.edtName);
        final TextView edtUsername = (TextView) findViewById(R.id.edtUsername);
        final TextView edtPassword = (TextView) findViewById(R.id.edtPassword);

        //Employee Account Info
        final String employeeUsername = "a";
        final String employeePassword = "a";
        final int employeeApproved = 1;
        //Reviewer Account Info
        final String reviewerUsername = "b";
        final String reviewerPassword = "b";
        final int reviewerApproved = 1;

        //login button method
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pulls text from fields
                final String name = edtName.getText().toString();
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();

                //Checks if username is already used
                if (username.equals(reviewerUsername) || username.equals(employeeUsername)) {
                    //Informs user to use another username
                    Toast.makeText(v.getContext(), R.string.strIncorrectAccountRegistration, 2).show();
                    //resets username text,
                    edtUsername.setText("");
                }
                //If not used, register the account and redirect to login page
                else {
                    //puts field information into database and sets account approval to 0
                    //
                    //
                    Toast.makeText(v.getContext(), R.string.strThanksForRegistering, 2).show();
                    //Redirect to login page
                    Intent switchToLogin = new Intent(v.getContext(), UserLogin.class);
                    startActivity(switchToLogin);
                }
            }
        });
    }
}