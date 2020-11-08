package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zublinhrapplication.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class EditUser extends AppCompatActivity {

    private static final String TAG = "EditUser";
    public static String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_view);

        final String strUserName = getIntent().getStringExtra("userInfo");
        if (strUserName.isEmpty()) {
            int shit = 1;
        }
        //setup drop down list first so I don't forget
        final Spinner staticSpinner = (Spinner) findViewById(R.id.spinnerAccountType);
        // Create an ArrayAdapter using the string array and a default spinner
        final ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.arAccountType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        final Button btnCancelChanges = findViewById(R.id.btnCancelChanges);
        final Button btnSubmitChanges = findViewById(R.id.btnSubmitChanges);
        final Button btnDeleteUser = findViewById(R.id.btnDeleteUser);

        final TextView edtUsername = findViewById(R.id.edtUsername);
        final EditText edtUserName = findViewById(R.id.edtUserName);
        final CheckBox chkYes = findViewById(R.id.cbxYes);
        chkYes.setChecked(false);
        final CheckBox chkNo = findViewById(R.id.cbxNo);
        chkNo.setChecked(false);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference users = db.collection("applicationUsers");
        users.whereEqualTo("username", strUserName).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<User> userList = queryDocumentSnapshots.toObjects(User.class);
                        Log.d(TAG, "UserList Size = " + userList.size() + " " + strUserName);
                        User user = userList.get(0);
                        password = user.getPassword();
                        edtUsername.setText(user.getUsername());
                        edtUserName.setText(user.getName());
                        if (user.getApprovedUser()) {
                            chkYes.setChecked(true);
                        } else {
                            chkNo.setChecked(true);
                        }
                        if (user.getAccountType() == 0) {
                            staticSpinner.setSelection(0);
                        } else if (user.getAccountType() == 1) {
                            staticSpinner.setSelection(1);
                        } else {
                            staticSpinner.setSelection(2);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                });;

        btnCancelChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = "";
                finish();
            }
        });
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.document(strUserName).delete();
                password = "";
                finish();
            }
        });
        btnSubmitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkNo.isChecked() && chkYes.isChecked()) {
                    Toast.makeText(v.getContext(), R.string.strApprovedUser, Toast.LENGTH_SHORT).show();
                } else if (!chkNo.isChecked() && !chkYes.isChecked()) {
                    Toast.makeText(v.getContext(), R.string.strApprovedUser, Toast.LENGTH_SHORT).show();
                } else if (edtUserName.toString().isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.strNameValid, Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setName(edtUserName.getText().toString());
                    user.setPassword(password);
                    password = "";
                    user.setUsername(strUserName);
                    if (chkNo.isChecked()) {
                        user.setApprovedUser(false);
                    } else {
                        user.setApprovedUser(true);
                    }
                    if (staticSpinner.getSelectedItem().toString().equals("Employee")) {
                        long type = 0;
                        user.setAccountType(type);
                    } else if (staticSpinner.getSelectedItem().toString().equals("Reviewer")) {
                        long type = 1;
                        user.setAccountType(type);
                    } else {
                        long type = 2;
                        user.setAccountType(type);
                    }
                    users.document(strUserName).update("username", user.getUsername(), "accountType", user.getAccountType(), "approvedUser", user.getApprovedUser(), "password", user.getPassword(), "name", user.getName());
                    finish();
                }
            }
        });
    }
}