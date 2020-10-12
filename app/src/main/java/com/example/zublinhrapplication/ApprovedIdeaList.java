package com.example.zublinhrapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApprovedIdeaList extends AppCompatActivity {
    private static final String TAG = "approvedIdeaList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_idea_list);

        final RecyclerView rvApprovedIdeas = (RecyclerView) findViewById(R.id.rvApprovedIdeas);
        rvApprovedIdeas.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvApprovedIdeas.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvApprovedIdeas.addItemDecoration(itemDecoration);

        final List<ShortPendingIdea> ideas = new ArrayList<ShortPendingIdea>();
        for (int i = 0; i < 20; i++) {
            ideas.add(new ShortPendingIdea("Title" + i, i, "Short Description", "author"));
        }

        System.out.println(ideas.size());

        final PendingIdeaListAdapter pendingIdeas = new PendingIdeaListAdapter(ideas);
        rvApprovedIdeas.setAdapter(pendingIdeas);
    }

}

