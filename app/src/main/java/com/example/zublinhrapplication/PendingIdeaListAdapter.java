package com.example.zublinhrapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PendingIdeaListAdapter extends RecyclerView.Adapter<PendingIdeaListAdapter.MyViewHolder> {
    private ShortPendingIdea[] pendingIdeas;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtAuthor;
        public TextView txtShortDescription;
        public TextView txtTitle;
        public MyViewHolder(View v) {
            super(v);
            txtAuthor = (TextView) v.findViewById(R.id.txtAuthor);
            txtShortDescription = (TextView) v.findViewById(R.id.txtShortDescription);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PendingIdeaListAdapter(ShortPendingIdea[] pendingIdeas) {
        this.pendingIdeas = pendingIdeas;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.pending_idea, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtAuthor.setText(pendingIdeas[position].getAuthor());
        holder.txtShortDescription.setText(pendingIdeas[position].getShortDescription());
        holder.txtTitle.setText(pendingIdeas[position].getIdeaTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pendingIdeas.length;
    }
}
