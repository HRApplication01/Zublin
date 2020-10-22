package com.example.zublinhrapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zublinhrapplication.adapter.PendingIdeaListAdapter;
import com.example.zublinhrapplication.model.Idea;
import com.example.zublinhrapplication.model.ShortPendingIdea;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PendingIdeaList extends AppCompatActivity {
    private static final String TAG = "pendingIdeaList";
    private FirestoreRecyclerAdapter adapter;
    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewer_idea_list);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final RecyclerView rvPendingIdeas = (RecyclerView) findViewById(R.id.rvPendingIdeas);
        rvPendingIdeas.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPendingIdeas.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvPendingIdeas.addItemDecoration(itemDecoration);

        final List<ShortPendingIdea> ideas = new ArrayList<ShortPendingIdea>();
        for (int i = 0; i < 20; i++) {
            ideas.add(new ShortPendingIdea("Title" + i, i, "Short Description", "author"));
        }

        CollectionReference ideasRef = db.collection("ideas");
        Query ideasQuery = ideasRef.whereEqualTo("pending", true);

        FirestoreRecyclerOptions<ShortPendingIdea> options = new FirestoreRecyclerOptions.Builder<ShortPendingIdea>().setQuery(ideasQuery, new SnapshotParser<ShortPendingIdea>() {
            @NonNull
            @Override
            public ShortPendingIdea parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                ShortPendingIdea shortPendingIdea = new ShortPendingIdea();
                shortPendingIdea.setAuthor(snapshot.getString("author"));
                shortPendingIdea.setId(snapshot.getLong("id").intValue());
                shortPendingIdea.setIdeaTitle(snapshot.getString("title"));
                String shortDescription = snapshot.getString("description");
                if( shortDescription.length() >= 50 )
                    shortDescription = shortDescription.substring(0,50);
                shortPendingIdea.setShortDescription(shortDescription);
                return shortPendingIdea;
            }
        }).build();

//        ideas.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                        Map<String, Object> data = document.getData();
//                        ShortPendingIdea dbIdea = new ShortPendingIdea((String) data.get("title"), (int) ((Long) data.get("id")).intValue(), (String) data.get("description"), (String) data.get("author"));
//                        System.out.println(dbIdea);
//                        ideas.add(dbIdea);
//                    }
//                } else {
//                    Log.w(TAG, "Error getting documents.", task.getException());
//                }
//            }
//        });
//        notify();

        ideasQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> docList = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d : docList){
                    Long id = d.getLong("id");
                    id.intValue();
                }

                List<Idea> ideas = queryDocumentSnapshots.toObjects(Idea.class);
                for(Idea i : ideas) {
                    i.getAuthor();
                    i.getId();
                    done = true;
                }
            }
        });
        while(!done){ }
        System.out.println(ideas.size());

        adapter = new FirestoreRecyclerAdapter<ShortPendingIdea, PendingIdeaListAdapter.MyViewHolder>(options) {
            @Override
            public void onBindViewHolder(PendingIdeaListAdapter.MyViewHolder holder, int position, ShortPendingIdea model) {
                // Bind the Chat object to the ChatHolder
                // ...
                holder.txtAuthor.setText(model.getAuthor());
                holder.txtId.setText("" + model.getId());
                holder.txtShortDescription.setText(model.getShortDescription());
                holder.txtTitle.setText(model.getIdeaTitle());
            }

            @Override
            public PendingIdeaListAdapter.MyViewHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.pending_idea, group, false);

                return new PendingIdeaListAdapter.MyViewHolder(group.getContext(), view);
            }
        };

        final PendingIdeaListAdapter pendingIdeas = new PendingIdeaListAdapter(ideas);
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

