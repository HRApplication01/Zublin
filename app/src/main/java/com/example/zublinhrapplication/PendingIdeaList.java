package com.example.zublinhrapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PendingIdeaList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewer_idea_list);

        final RecyclerView rvPendingIdeas = (RecyclerView) findViewById(R.id.rvPendingIdeas);
        rvPendingIdeas.setHasFixedSize(true);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPendingIdeas.setLayoutManager(layoutManager);

        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvPendingIdeas.addItemDecoration(itemDecoration);


        List<ShortPendingIdea> ideas = new ArrayList<ShortPendingIdea>();
        for(int i = 0; i < 20; i++){
            ideas.add(new ShortPendingIdea("Title"+i, i, "Short Description", "author"));
        }
        final PendingIdeaListAdapter pendingIdeas = new PendingIdeaListAdapter(ideas);
        rvPendingIdeas.setAdapter(pendingIdeas);



    }
}

