package com.example.zublinhrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class AnonymousIdea extends AppCompatActivity {

    public static int id = 0;
    public static boolean done = false;
    private static final String TAG = "AnonymousIdea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anonymous_idea_view);

        //setup drop down list first so I don't forget
        final Spinner staticSpinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.arFeasibility, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        //setup buttons
        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);

        //See If idea is valid and then return to employee screen
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                //setup text fields
                //final TextView edtPassword = (TextView) findViewById(R.id.edtPassword);
                //pulls text from fields
                //final String password = edtPassword.getText().toString();
                final TextView edtIdeaTitle = (TextView) findViewById(R.id.edtIdeaTitle);
                final String ideaTitle = edtIdeaTitle.getText().toString();
                final TextView edtLocationSite = (TextView) findViewById(R.id.edtLocationSite);
                final String locationSite = edtLocationSite.getText().toString();
                final TextView edtProblem = (TextView) findViewById(R.id.edtProblem);
                final String problem = edtProblem.getText().toString();
                final TextView edtSolution = (TextView) findViewById(R.id.edtSolution);
                final String solution = edtSolution.getText().toString();

                final String feasibility = staticSpinner.getSelectedItem().toString();

                final CheckBox cbxAdv1 = (CheckBox) findViewById(R.id.cbxAdv1);
                final boolean adv1Checked = cbxAdv1.isChecked();
                final CheckBox cbxAdv2 = (CheckBox) findViewById(R.id.cbxAdv2);
                final boolean adv2Checked = cbxAdv2.isChecked();
                final CheckBox cbxAdv3 = (CheckBox) findViewById(R.id.cbxAdv3);
                final boolean adv3Checked = cbxAdv3.isChecked();
                final CheckBox cbxAdv4 = (CheckBox) findViewById(R.id.cbxAdv4);
                final boolean adv4Checked = cbxAdv4.isChecked();

                final CheckBox cbxTried1 = (CheckBox) findViewById(R.id.cbxTried1);
                final boolean tried1Checked = cbxTried1.isChecked();
                final CheckBox cbxTried2 = (CheckBox) findViewById(R.id.cbxTried2);
                final boolean tried2Checked = cbxTried2.isChecked();

                final TextView edtSolutionTriedDesc = (TextView) findViewById(R.id.edtSolutionTriedDesc);
                final String solutionTriedDesc = edtSolutionTriedDesc.getText().toString();

                //checks if all values are good so we can add to database
                boolean pass = true;
                if (ideaTitle.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectIdeaTitle, Toast.LENGTH_SHORT).show();
                }
                if (locationSite.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectLocationSite, Toast.LENGTH_SHORT).show();
                }
                if (problem.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectProblem, Toast.LENGTH_SHORT).show();
                }
                if (solution.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectSolution, Toast.LENGTH_SHORT).show();
                }
                if (feasibility.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectFeasibility, Toast.LENGTH_SHORT).show();
                }
                if (!adv1Checked && !adv2Checked && !adv3Checked && !adv4Checked) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectAdvantage, Toast.LENGTH_SHORT).show();
                }
                if (!tried1Checked && !tried2Checked) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectTriedSolution, Toast.LENGTH_SHORT).show();
                }
                if (tried1Checked && solutionTriedDesc.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectSolutionDesc, Toast.LENGTH_SHORT).show();
                }
                //add to database
                if (pass) {
                    //setup firebase instance
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    //setup collection reference
                    final CollectionReference ideasCollection = db.collection("ideas");
                    //find largest id
                    ideasCollection.orderBy("id", Query.Direction.DESCENDING).limit(1).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    Log.d(TAG, "Got Here\n");
                                    if (documentSnapshots.size() != 0) {
                                        DocumentSnapshot item = documentSnapshots.getDocuments().get(0);
                                        Long idTemp = item.getLong("id");
                                        id = idTemp.intValue();
                                    } else {
                                        id = 0;
                                    }
                                    id++;
                                    //create document idea object
                                    Map<String, Object> idea = new HashMap<>();
                                    idea.put("author", "Anonymous");
                                    idea.put("description", solution);
                                    idea.put("id", id);
                                    idea.put("pending", 0);
                                    idea.put("title", ideaTitle);
                                    idea.put("mySelf", false);
                                    idea.put("other", false);
                                    idea.put("ideaTitle", ideaTitle);
                                    idea.put("locationSite", locationSite);
                                    idea.put("problem", problem);
                                    idea.put("solution", solution);
                                    idea.put("feasibility", feasibility);
                                    idea.put("adv1Checked", adv1Checked);
                                    idea.put("adv2Checked", adv2Checked);
                                    idea.put("adv3Checked", adv3Checked);
                                    idea.put("adv4Checked", adv4Checked);
                                    idea.put("tried1Checked", tried1Checked);
                                    idea.put("tried2Checked", tried2Checked);
                                    idea.put("solutionTriedDesc", solutionTriedDesc);
                                    idea.put("premium1Checked", false);
                                    idea.put("premium2Checked", false);
                                    idea.put("premium3Checked", false);
                                    //add user to the database
                                    ideasCollection.add(idea);
                                    //switch to Employee Menu Page
                                    Intent switchToLogin = new Intent(v.getContext(), UserLogin.class);
                                    id = 0;
                                    done = false;
                                    startActivity(switchToLogin);
                                }
                            });
                }
            }
        });
        //register cancel button method
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch to Employee Menu Page
                Intent switchToLogin = new Intent(v.getContext(), UserLogin.class);
                startActivity(switchToLogin);
            }
        });

    }
}