package com.example.zublinhrapplication.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ShortUserViewHolder extends RecyclerView.ViewHolder {
    public TextView txtAccountType;
    public TextView txtName;
    public TextView txtUsername;
    public TextView txtApprovedUser;

    public ShortUserViewHolder(View view, TextView txtAccountType, TextView txtName, TextView txtUsername, TextView txtApprovedUser) {
        super(view);
        this.txtAccountType = txtAccountType;
        this.txtName = txtName;
        this.txtUsername = txtUsername;
        this.txtApprovedUser = txtApprovedUser;
    }
}