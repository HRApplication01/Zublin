package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.Button;
import android.widget.CheckBox;
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
    public static boolean approved = true;
    public static String name = "";
    public static String username = "";
    public static String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_login_view);

        final UserInfo userInfo = new UserInfo(getIntent());

        //setup button
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final TextView txtTermsAndConditions = (TextView) findViewById(R.id.txtTermsAndConditions);

        txtTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to Employee Menu Page
                Intent switchToTerms = new Intent(v.getContext(), TermsAndConditions.class);
                startActivity(switchToTerms);
            }
        });

        //login button method
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                //setup text fields
                final TextView edtName = (TextView) findViewById(R.id.edtName);
                final TextView edtUsername = (TextView) findViewById(R.id.edtUsername);
                final TextView edtPassword = (TextView) findViewById(R.id.edtPassword);
                //pulls text from fields
                final String nameLocal = edtName.getText().toString();
                final String usernameLocal = edtUsername.getText().toString();
                final String passwordLocal = edtPassword.getText().toString();
                final CheckBox cbxTermsAndConditions = (CheckBox) findViewById(R.id.cbxTermsAndConditions);
                approved = cbxTermsAndConditions.isChecked();

                name = nameLocal;
                username = usernameLocal;
                password = passwordLocal;

                if (nameLocal.isEmpty() || usernameLocal.isEmpty() || passwordLocal.isEmpty() || !approved) {
                    if (nameLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterName, Toast.LENGTH_SHORT).show();
                    } else if (usernameLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterUsername, Toast.LENGTH_SHORT).show();
                    } else if (passwordLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterPassword, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), R.string.strReviewPrivacyConditional, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //setup database instance
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
                                if (document.exists()) {
                                    //System.out.println("DocumentSnapshot data: " + document.getData());
                                    exists = true;
                                    //if the record exists, apoligize and reload the page
                                    if (exists) {
                                        //Informs user to use another username
                                        Toast.makeText(v.getContext(), R.string.strIncorrectAccountRegistration, Toast.LENGTH_SHORT).show();
                                        //resets username text,
                                        edtUsername.setText("");
                                        exists = false;
                                    } else {
                                        exists = false;
                                    }
                                } else {
                                    //System.out.println("No such document");
                                    //If not used, register the account and redirect to login page
                                    //else {
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
                                    Toast.makeText(v.getContext(), R.string.strThanksForRegistering, Toast.LENGTH_SHORT).show();
                                    exists = false;
                                    name = "";
                                    username = "";
                                    password = "";
                                    finish();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}