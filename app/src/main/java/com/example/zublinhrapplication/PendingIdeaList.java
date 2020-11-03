package com.example.zublinhrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zublinhrapplication.model.Pending;
import com.example.zublinhrapplication.model.ShortIdea;
import com.example.zublinhrapplication.viewholder.ShortIdeaViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PendingIdeaList extends AppCompatActivity {
    private static final String TAG = "pendingIdeaList";
    private FirestoreRecyclerAdapter adapter;
    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_idea_list_view);

        final String username = getIntent().getStringExtra("username");
        final String name = getIntent().getStringExtra("name");

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final RecyclerView rvPendingIdeas = findViewById(R.id.rvShortIdeas);
        rvPendingIdeas.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPendingIdeas.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvPendingIdeas.addItemDecoration(itemDecoration);

        //Sets the query to db for find all the ideas with pending status as pending
        final CollectionReference ideasRef = db.collection("ideas");
        final Query ideasQuery = ideasRef.whereEqualTo("pending", Pending.PENDING.ordinal());

        //Sets up the options for the ForebaseUI recycler adapter, does a custom parsing of ideas to show a short description instead
        FirestoreRecyclerOptions<ShortIdea> options = new FirestoreRecyclerOptions.Builder<ShortIdea>().setQuery(ideasQuery, new SnapshotParser<ShortIdea>() {
            @NonNull
            @Override
            public ShortIdea parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                ShortIdea shortIdea = new ShortIdea();
                shortIdea.setAuthor(snapshot.getString("author"));
                Long id = snapshot.getLong("id");
                if(id != null) shortIdea.setId(id.intValue());
                shortIdea.setIdeaTitle(snapshot.getString("title"));
                String shortDescription = snapshot.getString("description");
                if( shortDescription != null && shortDescription.length() >= 50 )
                    shortDescription = shortDescription.substring(0,50);
                shortIdea.setShortDescription(shortDescription);
                return shortIdea;
            }
        }).build();

        //Creates the firestore adapter using the shortidea model and view holder
        adapter = new FirestoreRecyclerAdapter<ShortIdea, ShortIdeaViewHolder>(options) {

            //Required method that sets the contents of the view
            @Override
            public void onBindViewHolder(@NonNull ShortIdeaViewHolder holder, int position, @NonNull ShortIdea model) {
                holder.txtAuthor.setText(model.getAuthor());
                holder.txtId.setText("" + model.getId());
                holder.txtDescription.setText(model.getShortDescription());
                holder.txtTitle.setText(model.getIdeaTitle());
            }

            //Creates a new ShortIdeaViewHolder to holder the view at each spot in the list
            @NonNull
            @Override
            public ShortIdeaViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                final View view = LayoutInflater.from(group.getContext()).inflate(R.layout.short_idea, group, false);
                final TextView txtAuthor = view.findViewById(R.id.txtAuthor);
                final TextView txtId = view.findViewById(R.id.txtId);
                final TextView txtShortDescription = view.findViewById(R.id.txtShortDescription);
                final TextView txtTitle = view.findViewById(R.id.txtTitle);
                //Creates a onclick listener to start the activity ReviewerIdea with the idea's id and user info pasted in
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Clicked");
                        Intent intent = new Intent(v.getContext(), ReviewerIdea.class);
                        intent.putExtra("id", txtId.getText());
                        intent.putExtra("name", name);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                });

                return new ShortIdeaViewHolder(view, txtAuthor, txtId, txtShortDescription, txtTitle);
            }

        };

        rvPendingIdeas.setAdapter(adapter);


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

