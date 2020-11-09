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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zublinhrapplication.model.Pending;
import com.example.zublinhrapplication.model.ShortIdea;
import com.example.zublinhrapplication.viewholder.ShortIdeaViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class EmployeeIdeaList extends AppCompatActivity {
    private static final String TAG = "EmployeeIdeaList";
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_idea_list_view);

        final UserInfo userInfo = new UserInfo(getIntent());

        final RecyclerView rvEmployeeIdeas = findViewById(R.id.rvShortIdeas);
        rvEmployeeIdeas.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvEmployeeIdeas.setLayoutManager(layoutManager);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,(R.drawable.red_divider)));
        rvEmployeeIdeas.addItemDecoration(itemDecoration);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ideasRef = db.collection("ideas");
        Query ideasQuery = ideasRef.whereEqualTo("author", userInfo.getUsername());
        Log.d(TAG, (userInfo.getUsername() == null) + "");

        ideasQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.strNoIdeasFound, Toast.LENGTH_SHORT).show();
                }
            }
        });

        FirestoreRecyclerOptions<ShortIdea> options = new FirestoreRecyclerOptions.Builder<ShortIdea>().setQuery(ideasQuery, new SnapshotParser<ShortIdea>() {
            @NonNull
            @Override
            public ShortIdea parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                ShortIdea shortIdea = new ShortIdea();
                shortIdea.setAuthor(snapshot.getString("author"));
                shortIdea.setId(snapshot.getLong("id").intValue());
                shortIdea.setIdeaTitle(snapshot.getString("title"));
                String shortDescription = snapshot.getString("description");
                if( shortDescription != null && shortDescription.length() >= 50 )
                    shortDescription = shortDescription.substring(0,50);
                shortIdea.setShortDescription(shortDescription);
                return shortIdea;
            }
        }).build();

        adapter = new FirestoreRecyclerAdapter<ShortIdea, ShortIdeaViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull ShortIdeaViewHolder holder, int position, @NonNull ShortIdea model) {
                holder.txtAuthor.setText(model.getAuthor());
                holder.txtId.setText("" + model.getId());
                holder.txtDescription.setText(model.getShortDescription());
                holder.txtTitle.setText(model.getIdeaTitle());
            }

            @NonNull
            @Override
            public ShortIdeaViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.short_idea, group, false);
                final TextView txtAuthor = view.findViewById(R.id.txtAuthor);
                final TextView txtId = view.findViewById(R.id.txtId);
                final TextView txtShortDescription = view.findViewById(R.id.txtShortDescription);
                final TextView txtTitle = view.findViewById(R.id.txtTitle);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Clicked");
                        Intent intent = new Intent(v.getContext(), EmployeeIdeaReview.class);
                        intent.putExtra("id", txtId.getText());
                        userInfo.setString(intent);
                        startActivity(intent);
                    }
                });
                return new ShortIdeaViewHolder(view, txtAuthor, txtId, txtShortDescription, txtTitle);
            }
        };

        rvEmployeeIdeas.setAdapter(adapter);

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

