package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zublinhrapplication.model.Comment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Comments extends AppCompatActivity {

    static final private String TAG = "comments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_view);

        final String dum = getIntent().getStringExtra("ideaId");
        Log.d(TAG, dum);
        final Long dum_ideaID = Long.parseLong(dum);
        Log.d(TAG, "" + dum_ideaID);
        final String dum_author = getIntent().getStringExtra("name");


        final FirebaseFirestore db = FirebaseFirestore.getInstance(); //access database
        final Button btnComment = (Button) findViewById(R.id.post);//interact with button
        final CollectionReference comments = db.collection("comments"); //table in database called comments

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText inputComment = (EditText) findViewById(R.id.add_comment);//Add comment
                final String txtComment = inputComment.getText().toString(); //text from view to string

                final Query idQuery = comments.orderBy("id", Query.Direction.DESCENDING).limit(1); //get largest id
                idQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) { //returns list of documents
                        long id =1;
                        List<DocumentSnapshot> docList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : docList) {
                            Long idTemp = d.getLong("id");
                            id = idTemp + 1;
                        }
                        inputComment.setText("");
                        createComment(id, dum_ideaID, dum_author,txtComment);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        long id = 1;
                        inputComment.setText("");
                        createComment(id, dum_ideaID, dum_author,txtComment);
                    }
                });


            }
        });
    }

    private void createComment(Long id, Long ideaId, String author, String comments){

        final FirebaseFirestore db = FirebaseFirestore.getInstance(); //access database
        final CollectionReference comments_ref = db.collection("comments"); //table in database called comments

        Comment c = new Comment(id, ideaId, author, comments);
        comments_ref.add(c).addOnSuccessListener(new OnSuccessListener<DocumentReference>() { //Add the comment to the collection
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

}