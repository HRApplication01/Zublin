package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.zublinhrapplication.model.Idea;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ReviewerIdea extends AppCompatActivity {
    private static final String TAG = "ReviewerIdea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewer_idea_view);

        final String strId = getIntent().getStringExtra("id");
        final int id = Integer.parseInt(strId);
        Log.d(TAG, strId);

        //todo assign variable to value in layout
        final CheckBox chkAdv1Checked;
        final CheckBox chkAdv2Checked;
        final CheckBox chkAdv3Checked;
        final CheckBox chkAdv4Checked;
        final TextView txtAuthor;
        final TextView txtDescription;
        final TextView txtFeasibility;
        final TextView txtId;
        final TextView txtIdeaTitle;
        final TextView txtLocationSite;
        final CheckBox chkMySelf;
        final CheckBox chkOther;
        final CheckBox chkPending;
        final CheckBox chkPremium1Checked;
        final CheckBox chkPremium2Checked;
        final CheckBox chkPremium3Checked;
        final TextView txtProblem;
        final TextView txtSolution;
        final TextView txtSolutionTriedDesc;
        final TextView txtTitle;
        final CheckBox chkTried1Checked;
        final CheckBox chkTried2Checked;

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference ideas = db.collection("ideas");
        ideas.whereEqualTo("id", id).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Idea> ideaList = queryDocumentSnapshots.toObjects(Idea.class);
                        if(ideaList.size() != 1) {
                            String errorString = String.format("There were %d documents found with id: %d", ideaList.size(), id);
                            Log.e(TAG, errorString);
                            return;
                        }
                        Idea idea = ideaList.get(0);
                /* Todo uncomment when view is merged
                chkAdv1Checked.setChecked(idea.isAdv1Checked());
                chkAdv2Checked.setChecked(idea.isAdv2Checked());
                chkAdv3Checked.setChecked(idea.isAdv3Checked());
                chkAdv4Checked.setChecked(idea.isAdv4Checked());
                txtAuthor.setText(idea.getAuthor());
                txtDescription.setText(idea.getDescription());
                txtFeasibility.setText(idea.getFeasibility());
                txtId.setText(idea.getId().toString());
                txtIdeaTitle.setText(idea.getIdeaTitle());
                txtLocationSite.setText(idea.getLocationSite());
                chkMySelf.setChecked(idea.isMySelf());
                chkOther.setChecked(idea.isOther());
                chkPending.setChecked(idea.isPending());
                chkPremium1Checked.setChecked(idea.isPremium1Checked());
                chkPremium2Checked.setChecked(idea.isPremium2Checked());
                chkPremium3Checked.setChecked(idea.isPremium3Checked());
                txtProblem.setText(idea.getProblem());
                txtSolution.setText(idea.getSolution());
                txtSolutionTriedDesc.setText(idea.getSolutionTriedDesc());
                txtTitle.setText(idea.getTitle());
                chkTried1Checked.setChecked(idea.isTried1Checked());
                chkTried2Checked.setChecked(idea.isTried2Checked());
                */
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "There is no documents with id " + id);
                        Log.e(TAG, e.getMessage());
                    }
                });

        /* todo uncomment when view merged
        final Button btnComment;
        final CollectionReference comments = db.collection("comments");
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment c = new Comment(Long.valueOf(100), Long.valueOf(105), "Jason", "This is a comment2");
                comments.add(c).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Document created: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Failed to add document to db");
                    }
                });
            }
        });
        */
        /* todo uncomment when view merged
        final Button btnApproveIdea;
        btnApproveIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ideas.whereEqualTo("id", id).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> ideaList = queryDocumentSnapshots.getDocuments();
                            if(ideaList.size() != 1) {
                                String errorString = String.format("There were %d documents found with id: %d", ideaList.size(), id);
                                Log.w(TAG, errorString);
                                return;
                            }
                            final String docId = ideaList.get(0).getId();
                            ideas.document(docId).update("pending", false)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "Document " + docId + " successfully updated");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Document " + docId + " failed to update");
                                    Log.w(TAG, e.getMessage());
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "There is no documents with id " + id);
                            Log.w(TAG, e.getMessage());
                        }
                    });
            }
        });
         */
    }
}