package com.example.zublinhrapplication.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ShortIdeaViewHolder extends RecyclerView.ViewHolder {
    public TextView txtAuthor;
    public TextView txtId;
    public TextView txtDescription;
    public TextView txtTitle;

    public ShortIdeaViewHolder(View view, TextView txtAuthor, TextView txtId, TextView txtDescription, TextView txtTitle) {
        super(view);
        this.txtAuthor = txtAuthor;
        this.txtId = txtId;
        this.txtDescription = txtDescription;
        this.txtTitle = txtTitle;
    }
}
