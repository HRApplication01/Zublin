package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.EditText;

import com.example.zublinhrapplication.model.Comment;
import com.example.zublinhrapplication.model.Idea;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class ReviewerIdea extends AppCompatActivity {
    private static final String TAG = "ReviewerIdea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewer_idea_view);

        final String strId = getIntent().getStringExtra("id");
        final int id = Integer.parseInt(strId);
        Log.d(TAG, strId);

        final String name = getIntent().getStringExtra("name");
        final String username = getIntent().getStringExtra("username");


        //todo do feasibility and add pending status
        final CheckBox chkAdv1Checked = findViewById(R.id.cbxAdv1);
        final CheckBox chkAdv2Checked = findViewById(R.id.cbxAdv2);
        final CheckBox chkAdv3Checked = findViewById(R.id.cbxAdv3);
        final CheckBox chkAdv4Checked = findViewById(R.id.cbxAdv4);
//        final EditText txtAuthor = findViewById(R.id.txtAuthor);
//        final EditText txtFeasibility;
//        final EditText txtId;
        final EditText txtIdeaTitle = findViewById(R.id.edtIdeaTitle);
        final EditText txtLocationSite = findViewById(R.id.edtLocationSite);
        final CheckBox chkMySelf = findViewById(R.id.cbxMyself);
        final CheckBox chkOther = findViewById(R.id.cbxOther);
//        final CheckBox chkPending = findViewById(R.id.cbxP);
        final CheckBox chkPremium1Checked = findViewById(R.id.cbxPremium1);
        final CheckBox chkPremium2Checked = findViewById(R.id.cbxPremium2);
        final CheckBox chkPremium3Checked = findViewById(R.id.cbxPremium3);
        final EditText txtProblem = findViewById(R.id.edtProblem);
        final EditText txtSolution = findViewById(R.id.edtSolution);
        final EditText txtSolutionTriedDesc = findViewById(R.id.edtSolutionTriedDesc);
//        final EditText txtTitle = findViewById(R.id.txtTi);
        final CheckBox chkTried1Checked = findViewById(R.id.cbxTried1);
        final CheckBox chkTried2Checked = findViewById(R.id.cbxTried2);

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

                chkAdv1Checked.setChecked(idea.isAdv1Checked());
                chkAdv2Checked.setChecked(idea.isAdv2Checked());
                chkAdv3Checked.setChecked(idea.isAdv3Checked());
                chkAdv4Checked.setChecked(idea.isAdv4Checked());
//                txtAuthor.setText(idea.getAuthor());
//                txtDescription.setText(idea.getDescription());
//                txtFeasibility.setText(idea.getFeasibility());
//                txtId.setText(idea.getId().toString());
                txtIdeaTitle.setText(idea.getIdeaTitle());
                txtLocationSite.setText(idea.getLocationSite());
                chkMySelf.setChecked(idea.isMySelf());
                chkOther.setChecked(idea.isOther());
//                chkPending.setChecked(idea.isPending());
                chkPremium1Checked.setChecked(idea.isPremium1Checked());
                chkPremium2Checked.setChecked(idea.isPremium2Checked());
                chkPremium3Checked.setChecked(idea.isPremium3Checked());
                txtProblem.setText(idea.getProblem());
                txtSolution.setText(idea.getSolution());
                txtSolutionTriedDesc.setText(idea.getSolutionTriedDesc());
//                txtTitle.setText(idea.getTitle());
                chkTried1Checked.setChecked(idea.isTried1Checked());
                chkTried2Checked.setChecked(idea.isTried2Checked());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "There is no documents with id " + id);
                        Log.e(TAG, e.getMessage());
                    }
                });


        final Button btnComment = findViewById(R.id.btnComments);
        final CollectionReference comments = db.collection("comments");
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent commentView = new Intent(v.getContext(), Comments.class);
//                commentView.putExtra("username", username);
//                commentView.putExtra("ideaId", strId);
//                commentView.putExtra("name", name);
            }
        });

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
                            ideas.document(docId).update("pending", pending.APPROVED)
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
                            Log.w(TAG, "Failed to connect to db");
                    });
            }
        });
         */
    }
}
