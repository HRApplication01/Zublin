package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdminUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_admin_user_view);

        final UserInfo userInfo = new UserInfo(getIntent());

        final Button btnSubmit = (Button) findViewById(R.id.btnSubmitChanges);
        final Button btnCancel = (Button) findViewById(R.id.btnCancelChanges);

        final EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        final EditText edtUserName = (EditText) findViewById(R.id.edtUserName);

        //setup drop down list first so I don't forget
        final Spinner staticSpinner = (Spinner) findViewById(R.id.spinnerAccountType);
        // Create an ArrayAdapter using the string array and a default spinner
        final ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.arAccountType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setSelection(0);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nameLocal = edtUserName.getText().toString();
                final String usernameLocal = edtUsername.getText().toString();
                final String passwordLocal = edtPassword.getText().toString();
                final String accountType = staticSpinner.getSelectedItem().toString();

                if (nameLocal.isEmpty() || usernameLocal.isEmpty() || passwordLocal.isEmpty() || accountType.isEmpty()) {
                    if (nameLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterName, Toast.LENGTH_SHORT).show();
                    } else if (usernameLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterUsername, Toast.LENGTH_SHORT).show();
                    } else if (passwordLocal.isEmpty()) {
                        Toast.makeText(v.getContext(), R.string.strMustEnterPassword, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), R.string.strMustEnterAccountType, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //setup database instance
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    //setup collection reference
                    final CollectionReference applicationUsers = db.collection("applicationUsers");
                    //Checks if username is already use
                    DocumentReference docRef = db.collection("applicationUsers").document(usernameLocal);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Toast.makeText(getApplicationContext(), R.string.strIncorrectAccountRegistration, Toast.LENGTH_SHORT).show();
                                    edtUsername.setText("");
                                } else {
                                    //System.out.println("No such document");
                                    //If not used, register the account and redirect to login page
                                    //else {
                                    // Create a new user with username, password, name
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("username", usernameLocal);
                                    user.put("password", passwordLocal);
                                    user.put("name", nameLocal);
                                    user.put("approvedUser", true);
                                    if (staticSpinner.getSelectedItem().toString().equals("Employee")) {
                                        long type = 0;
                                        user.put("accountType", type);
                                    } else if (staticSpinner.getSelectedItem().toString().equals("Reviewer")) {
                                        long type = 1;
                                        user.put("accountType", type);
                                    } else {
                                        long type = 2;
                                        user.put("accountType", type);
                                    }
                                    applicationUsers.document(usernameLocal).set(user);
                                    Toast.makeText(getApplicationContext(), R.string.strAccountSucessfullyCreated, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }
                    });
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static boolean approveAddUser(boolean pass, String nameLocal, String usernameLocal, String passwordLocal, String accountType) {

        if (nameLocal.isEmpty() || usernameLocal.isEmpty() || passwordLocal.isEmpty() || accountType.isEmpty()) {
            if (nameLocal.isEmpty()) {
                pass = false;
            } else if (usernameLocal.isEmpty()) {
                pass = false;
            } else if (passwordLocal.isEmpty()) {
                pass = false;
            } else {
                pass = false;
            }
        }

        return pass;
    }
}