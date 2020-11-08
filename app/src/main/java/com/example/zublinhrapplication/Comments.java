package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zublinhrapplication.model.Comment;
import com.example.zublinhrapplication.model.Pending;
import com.example.zublinhrapplication.model.ShortIdea;
import com.example.zublinhrapplication.viewholder.CommentViewHolder;
import com.example.zublinhrapplication.viewholder.ShortIdeaViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
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
    private FirestoreRecyclerAdapter adapter; //For list of comments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_view);

        //Still working

        final String dum = getIntent().getStringExtra("ideaId");
        Log.d(TAG, dum);
        final Long dum_ideaID = Long.parseLong(dum);
        Log.d(TAG, "" + dum_ideaID);
        final String dum_author = getIntent().getStringExtra("name");


        final FirebaseFirestore db = FirebaseFirestore.getInstance(); //access database
        final Button btnComment = (Button) findViewById(R.id.post);//interact with button
        final CollectionReference comments = db.collection("comments"); //table in database called comments

        final RecyclerView rvComments = findViewById(R.id.rvComments);


        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvComments.setLayoutManager(layoutManager);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,(R.drawable.red_divider)));
        rvComments.addItemDecoration(itemDecoration);

        Query commentsQuery = comments.whereEqualTo("ideaId", dum_ideaID);

        commentsQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.strNoIdeasFound, Toast.LENGTH_SHORT).show();
                }
            }
        });

        FirestoreRecyclerOptions<Comment> options = new FirestoreRecyclerOptions.Builder<Comment>().setQuery(commentsQuery, Comment.class).build();

        adapter = new FirestoreRecyclerAdapter<Comment, CommentViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull CommentViewHolder holder, int position, @NonNull Comment model) {
                holder.txtCommentUsername.setText(model.getAuthor());
                holder.txtCommentID.setText("" + model.getId());
                holder.txtCommentComment.setText(model.getComments());
            }

            @NonNull
            @Override
            public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.comment_item, group, false);
                final TextView txtCommentUsername = view.findViewById(R.id.txtCommentUsername);
                final TextView txtCommentID = view.findViewById(R.id.txtCommentID);
                final TextView txtCommentComment = view.findViewById(R.id.txtCommentComment);
                return new CommentViewHolder(view, txtCommentUsername, txtCommentID, txtCommentComment);
            }
        };

        rvComments.setAdapter(adapter);



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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}