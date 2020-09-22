package com.example.zublinhrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_view);

        //setup buttons
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        //setup text fields
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
        //New Account
        final String accountUsername = "c";
        final String accountPassword = "c";
        final int accountApproved = 0;

        //login button method
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pulls text from fields
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();

                if (username.equals(employeeUsername) && password.equals(employeePassword)) {
                    //switch to Employee page
                    Intent switchToEmployee = new Intent(v.getContext(), Employee.class);
                    startActivity(switchToEmployee);
                }
                else if (username.equals(reviewerUsername) && password.equals(reviewerPassword)) {
                    //switch to Reviewer Page
                    Intent switchToReviewer = new Intent(v.getContext(), Reviewer.class);
                    startActivity(switchToReviewer);
                }
                else if (username.equals(accountUsername) && password.equals(accountPassword) && accountApproved == 0) {
                    //Inform user that account has not yet been approved by supervisor
                    Toast.makeText(v.getContext(), R.string.strLoginNotApproved, 2).show();
                    edtUsername.setText("");
                    edtPassword.setText("");
                }
                else {
                    //inform user of incorrect password attempt
                    Toast.makeText(v.getContext(), R.string.strIncorrectPasswordToast, 2).show();
                    //reset text fields
                    edtUsername.setText("");
                    edtPassword.setText("");
                }
            }
        });

        //register button method
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch to register page
                Intent switchToRegister = new Intent(v.getContext(), UserRegisterLogin.class);
                startActivity(switchToRegister);
            }
        });
    }
}