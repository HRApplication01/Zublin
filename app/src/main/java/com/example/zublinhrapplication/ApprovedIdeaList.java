package com.example.zublinhrapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zublinhrapplication.adapter.PendingIdeaListAdapter;
import com.example.zublinhrapplication.model.ShortPendingIdea;

import java.util.ArrayList;
import java.util.List;

public class ApprovedIdeaList extends AppCompatActivity {
    private static final String TAG = "approvedIdeaList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_idea_list);

        final RecyclerView rvApprovedIdeas = findViewById(R.id.rvApprovedIdeas);
        rvApprovedIdeas.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvApprovedIdeas.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvApprovedIdeas.addItemDecoration(itemDecoration);

        final List<ShortPendingIdea> ideas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ideas.add(new ShortPendingIdea("Title" + i, i, "Short Description", "author"));
        }

        System.out.println(ideas.size());

        final PendingIdeaListAdapter pendingIdeas = new PendingIdeaListAdapter(ideas);
        rvApprovedIdeas.setAdapter(pendingIdeas);
    }

}

