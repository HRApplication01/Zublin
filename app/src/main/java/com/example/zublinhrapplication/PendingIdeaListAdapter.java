package com.example.zublinhrapplication;

import android.content.Context;
import android.service.autofill.AutofillService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.List;

public class PendingIdeaListAdapter extends RecyclerView.Adapter<PendingIdeaListAdapter.MyViewHolder> {
    private List<ShortPendingIdea> pendingIdeas;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtAuthor;
        public TextView txtId;
        public TextView txtShortDescription;
        public TextView txtTitle;
        private Context context;
        public MyViewHolder(Context context, View view) {
            super(view);
            txtAuthor = (TextView) view.findViewById(R.id.txtAuthor);
            txtId = (TextView) view.findViewById(R.id.txtId);
            txtShortDescription = (TextView) view.findViewById(R.id.txtShortDescription);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            this.context = context;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Toast.makeText(context, "Title:" + txtTitle.getText(), Toast.LENGTH_SHORT).show();
                //todo Create new view to show the idea
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PendingIdeaListAdapter(List<ShortPendingIdea> pendingIdeas) {
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
        MyViewHolder viewHolder = new MyViewHolder(context, contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtAuthor.setText(pendingIdeas.get(position).getAuthor());
        holder.txtId.setText(""+pendingIdeas.get(position).getId());
        holder.txtShortDescription.setText(pendingIdeas.get(position).getShortDescription());
        holder.txtTitle.setText(pendingIdeas.get(position).getIdeaTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pendingIdeas.size();
    }
}
