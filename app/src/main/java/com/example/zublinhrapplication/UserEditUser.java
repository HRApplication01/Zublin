package com.example.zublinhrapplication;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zublinhrapplication.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class UserEditUser extends AppCompatActivity {

    private static final String TAG = "EditUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_user_view);

        final String strUserName = getIntent().getStringExtra("username");

        final Button btnCancelChanges = findViewById(R.id.btnCancelChanges);
        final Button btnSubmitChanges = findViewById(R.id.btnSubmitChanges);

        final TextView edtUsername = findViewById(R.id.edtUsername);
        final EditText edtUserName = findViewById(R.id.edtUserName);
        final EditText edtPassword = findViewById(R.id.edtPassword);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference users = db.collection("applicationUsers");
        users.whereEqualTo("username", strUserName).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<User> userList = queryDocumentSnapshots.toObjects(User.class);
                        Log.d(TAG, "UserList Size = " + userList.size() + " " + strUserName);
                        User user = userList.get(0);
                        edtPassword.setText(user.getPassword());
                        edtUsername.setText(user.getUsername());
                        edtUserName.setText(user.getName());
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
                finish();
            }
        });
        btnSubmitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.toString().isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.strNameValid, Toast.LENGTH_SHORT).show();
                } else if (edtPassword.toString().isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.strPasswordValid, Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setName(edtUserName.getText().toString());
                    user.setPassword(edtPassword.getText().toString());
                    user.setUsername(strUserName);
                    users.document(strUserName).update("username", user.getUsername(), "password", user.getPassword(), "name", user.getName());
                    finish();
                }
            }
        });
    }
}