package com.example.zublinhrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeIdea extends AppCompatActivity {

    public static int id = 0;
    public static boolean done = false;
    private static final String TAG = "employeeIdea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_idea_view);

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

                //setup fields
                final CheckBox cbxMyself = (CheckBox) findViewById(R.id.cbxMyself);
                final boolean mySelf = cbxMyself.isChecked();
                final CheckBox cbxOther = (CheckBox) findViewById(R.id.cbxOther);
                final boolean other = cbxOther.isChecked();

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

                final CheckBox cbxPremium1 = (CheckBox) findViewById(R.id.cbxPremium1);
                final boolean premium1Checked = cbxPremium1.isChecked();
                final CheckBox cbxPremium2 = (CheckBox) findViewById(R.id.cbxPremium2);
                final boolean premium2Checked = cbxPremium2.isChecked();
                final CheckBox cbxPremium3 = (CheckBox) findViewById(R.id.cbxPremium3);
                final boolean premium3Checked = cbxPremium3.isChecked();

                //checks if all values are good so we can add to database
                boolean pass = true;
                if(!mySelf && !other) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectAuthor, Toast.LENGTH_SHORT).show();
                }
                if(ideaTitle.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectIdeaTitle, Toast.LENGTH_SHORT).show();
                }
                if(locationSite.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectLocationSite, Toast.LENGTH_SHORT).show();
                }
                if(problem.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectProblem, Toast.LENGTH_SHORT).show();
                }
                if(solution.isEmpty()) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectSolution, Toast.LENGTH_SHORT).show();
                }
                if(feasibility.isEmpty()) {
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
                if (!premium1Checked && !premium2Checked && !premium3Checked) {
                    pass = false;
                    Toast.makeText(v.getContext(), R.string.strSelectPremium, Toast.LENGTH_SHORT).show();
                }
                //add to database
                if(pass) {
                    //setup firebase instance
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    //setup collection reference
                    final CollectionReference ideasCollection = db.collection("ideas");
                    //find largest id
                    ideasCollection.orderBy("id", Query.Direction.DESCENDING).limit(1).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    Log.d(TAG,"Got Here\n");
                                    if (documentSnapshots.size() != 0) {
                                        DocumentSnapshot item = documentSnapshots.getDocuments().get(0);
                                        Long idTemp = item.getLong("id");
                                        id = idTemp.intValue();
                                    }
                                    else {
                                        id = 0;
                                    }
                                    id++;
                                    String username = getIntent().getStringExtra("username");
                                    String name = getIntent().getStringExtra("name");
                                    //create document idea object
                                    Map<String, Object> idea = new HashMap<>();
                                    idea.put("author", name);
                                    idea.put("description", solution);
                                    idea.put("id", id);
                                    idea.put("pending", true);
                                    idea.put("title", ideaTitle);
                                    idea.put("mySelf", mySelf);
                                    idea.put("other", other);
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
                                    idea.put("premium1Checked", premium1Checked);
                                    idea.put("premium2Checked", premium2Checked);
                                    idea.put("premium3Checked", premium3Checked);
                                    //add user to the database
                                    ideasCollection.add(idea);
                                    //switch to Employee Menu Page
                                    Intent switchToEmployee = new Intent(v.getContext(), Employee.class);
                                    switchToEmployee.putExtra("username", username);
                                    switchToEmployee.putExtra("name", name);
                                    id = 0;
                                    done = false;
                                    startActivity(switchToEmployee);
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
                Intent switchToEmployee = new Intent(v.getContext(), Employee.class);
                String username = getIntent().getStringExtra("username");
                String name = getIntent().getStringExtra("name");
                switchToEmployee.putExtra("username", username);
                switchToEmployee.putExtra("name", name);
                startActivity(switchToEmployee);
            }
        });

    }
}