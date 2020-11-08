package com.example.zublinhrapplication.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public TextView txtCommentUsername;
    public TextView txtCommentID;
    public TextView txtCommentComment;

    public CommentViewHolder(@NonNull View itemView, TextView txtCommentUsername, TextView txtCommentID, TextView txtCommentComment) {
        super(itemView);
        this.txtCommentUsername = txtCommentUsername;
        this.txtCommentID = txtCommentID;
        this.txtCommentComment = txtCommentComment;
    }
}
