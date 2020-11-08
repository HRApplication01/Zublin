package com.example.zublinhrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zublinhrapplication.model.ShortIdea;
import com.example.zublinhrapplication.model.User;
import com.example.zublinhrapplication.viewholder.ShortUserViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class UserList extends AppCompatActivity {
    private static final String TAG = "EmployeeIdeaList";
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_idea_list_view);

        final String username = getIntent().getStringExtra("username");
        final String name = getIntent().getStringExtra("name");

        final RecyclerView rvUserList = findViewById(R.id.rvShortIdeas);
        rvUserList.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvUserList.addItemDecoration(itemDecoration);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ideasRef = db.collection("applicationUsers");

        ideasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.strNoUsersFound, Toast.LENGTH_SHORT).show();
                }
            }
        });

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>().setQuery(ideasRef, User.class).build();

        adapter = new FirestoreRecyclerAdapter<User, ShortUserViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull ShortUserViewHolder holder, int position, @NonNull User model) {

                if (model.getAccountType() == 0) {
                    holder.txtAccountType.setText(R.string.strEmployee);
                } else if (model.getAccountType() == 1) {
                    holder.txtAccountType.setText(R.string.strReviewer);
                } else {
                    holder.txtAccountType.setText(R.string.strAdmin);
                }
                holder.txtName.setText(model.getName());
                holder.txtUsername.setText(model.getUsername());

                if (!model.getApprovedUser()) {
                    holder.txtApprovedUser.setText(R.string.strTrue);
                } else {
                    holder.txtApprovedUser.setText(R.string.strFalse);
                }
            }

            @NonNull
            @Override
            public ShortUserViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.short_user, group, false);
                final TextView txtAccountType = view.findViewById(R.id.txtAccountType);
                final TextView txtName = view.findViewById(R.id.txtName);
                final TextView txtUsername = view.findViewById(R.id.txtUsername);
                final TextView txtApprovedUser = view.findViewById(R.id.txtApprovedUser);
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.d(TAG, "Clicked");
//                        Intent intent = new Intent(v.getContext(), UserReview.class);
//                        intent.putExtra("name", name);
//                        intent.putExtra("username", username);
//                        startActivity(intent);
//                    }
//                });
                return new ShortUserViewHolder(view, txtAccountType, txtName, txtUsername, txtApprovedUser);
            }
        };

        rvUserList.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

