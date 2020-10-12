package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterLogin extends AppCompatActivity {

    //setup global exists variable
    public static boolean exists = false;

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

        //login button method
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                //pulls text from fields
                final String name = edtName.getText().toString();
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();

                //setup database instance
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                //setup collection reference
                CollectionReference applicationUsers = db.collection("applicationUsers");

                //Checks if username is already use
                DocumentReference docRef = db.collection("applicationUsers").document(username);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                //System.out.println("DocumentSnapshot data: " + document.getData());
                                exists = true;
                            } else {
                                //System.out.println("No such document");
                            }
                        } else {
                            //System.out.println("get failed with ");
                        }
                    }
                });

                //if the record exists, apoligize and reload the page
                if (exists) {
                    //Informs user to use another username
                    Toast.makeText(v.getContext(), R.string.strIncorrectAccountRegistration, 2).show();
                    //resets username text,
                    edtUsername.setText("");
                }

                //If not used, register the account and redirect to login page
                else {
                    // Create a new user with username, password, name
                    Map<String, Object> user = new HashMap<>();
                    user.put("username", username);
                    user.put("password", password);
                    user.put("name", name);
                    user.put("approvedUser", false);
                    user.put("accountType", 0);
                    //add user to the database
                    applicationUsers.document(username).set(user);
                    //how to add with randown documentid
                    //applicationUsers.add(user);

                    //Tell user they have to wait for their account to be approved and redirect to login screen
                    Toast.makeText(v.getContext(), R.string.strThanksForRegistering, 2).show();
                    //Redirect to login page
                    Intent switchToLogin = new Intent(v.getContext(), UserLogin.class);
                    startActivity(switchToLogin);
                }
            }

        });
    }
}