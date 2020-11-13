package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserLogin extends AppCompatActivity {

    public static int existsUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_view);

        //setup buttons
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final Button btnAnonymous = (Button) findViewById(R.id.btnAnonymousSubmit);

        //login button method
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                //setup text fields
                final TextView edtUsername = (TextView) findViewById(R.id.edtUsername);
                final TextView edtPassword = (TextView) findViewById(R.id.edtPassword);
                //pulls text from fields
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    if (username.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterUsername, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), R.string.strMustEnterPassword, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //setup firebase instance
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    //setup collection reference
                    final CollectionReference applicationUsers = db.collection("applicationUsers");
                    //Checks if username is already use
                    DocumentReference docRef = db.collection("applicationUsers").document(username);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                assert document != null;
                                if (document.exists()) {
                                    String usernameText = document.getString("username");
                                    String passwordText = document.getString("password");
                                    String name = document.getString("name");
                                    boolean approved = document.getBoolean("approvedUser");
                                    long accountType = document.getLong("accountType"); 
                                    if (username.equals(usernameText) && password.equals(passwordText) && approved && accountType == 0) {
                                        existsUser = 1;
                                    } else if (username.equals(usernameText) && password.equals(passwordText) && approved && accountType == 1) {
                                        existsUser = 2;
                                    } else if (username.equals(usernameText) && password.equals(passwordText) && approved && accountType == 2) {
                                        existsUser = 3;
                                    } else if (username.equals(usernameText) && password.equals(passwordText) && !approved) {
                                        existsUser = 4;
                                    }
                                    UserInfo userInfo = new UserInfo(username, name);
                                    if (existsUser == 1) {
                                        //switch to Employee page
                                        Intent switchToEmployee = new Intent(v.getContext(), Employee.class);
                                        userInfo.setIntentStrings(switchToEmployee);
                                        startActivity(switchToEmployee);
                                        existsUser = 0;
                                    } else if (existsUser == 2) {
                                        //switch to Reviewer Page
                                        Intent switchToReviewer = new Intent(v.getContext(), Reviewer.class);
                                        userInfo.setIntentStrings(switchToReviewer);
                                        startActivity(switchToReviewer);
                                        existsUser = 0;
                                    } else if (existsUser == 3) {
                                        //switch to Reviewer Page
                                        Intent switchToAdmin = new Intent(v.getContext(), Admin.class);
                                        userInfo.setIntentStrings(switchToAdmin);
                                        startActivity(switchToAdmin);
                                        existsUser = 0;
                                    } else if (existsUser == 4) {
                                        //Inform user that account has not yet been approved by supervisor
                                        Toast.makeText(v.getContext(), R.string.strLoginNotApproved, Toast.LENGTH_SHORT).show();
                                        edtUsername.setText("");
                                        edtPassword.setText("");
                                        existsUser = 0;
                                    } else {
                                        //inform user of incorrect password attempt
                                        Toast.makeText(v.getContext(), R.string.strIncorrectPasswordToast, Toast.LENGTH_SHORT).show();
                                        //reset text fields
                                        edtUsername.setText("");
                                        edtPassword.setText("");
                                        existsUser = 0;
                                    }
                                } else {
                                    //inform user of incorrect password attempt
                                    Toast.makeText(v.getContext(), R.string.strIncorrectPasswordToast, Toast.LENGTH_SHORT).show();
                                    edtUsername.setText("");
                                    edtPassword.setText("");
                                }
                            }
                        }
                    });
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

        btnAnonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to register page
                Intent switchToAnonymous = new Intent(v.getContext(), AnonymousIdea.class);
                startActivity(switchToAnonymous);
            }
        });
    }
}