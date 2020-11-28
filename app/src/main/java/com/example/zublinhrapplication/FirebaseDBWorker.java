package com.example.zublinhrapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.type.DateTime;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirebaseDBWorker extends Worker {
    private static int notificationID = 1;
    private static String TAG = "notifications";

    public FirebaseDBWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }

        Log.d(TAG, "Called do work");

        checkDBNewIdeas();

        // Indicate whether the work finished successfully with the Result
        Log.d(TAG, "success");
        return Result.success();
    }

    public void checkDBNewIdeas() {
//        Log.d(TAG, "Created worker");
        final Context context = getApplicationContext();
        final Timestamp currentTime = Timestamp.now();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        long previousTimestamp = sharedPref.getLong("previousTimestamp", currentTime.toDate().getTime());

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("previousTimestamp", currentTime.toDate().getTime());
        boolean suc = editor.commit();
//        Log.d(TAG, suc + "");

        Date date = new Date(previousTimestamp);
        final Timestamp ts = new Timestamp(date);
//        Log.d(TAG, ts.toString());

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query ideasQuery = db.collection("ideas");
        ideasQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                Log.d("notifications", "Did query");
                List<DocumentSnapshot> newDocuments = new ArrayList<>();
                for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()) {
                    if(document.get("timeStamp") == null) {
                        newDocuments.add(document);
                        String docID = document.getId();
                        db.collection("ideas").document(docID).update("timeStamp", currentTime);
                        Log.d(TAG, "document has no timeStamp");
                    } else if (document.getTimestamp("timeStamp").compareTo(ts) > 0) {
                        newDocuments.add(document);
                        Log.d(TAG, "document is newer than previous time");
                        Log.d(TAG, document.getTimestamp("timeStamp").toString());
                        Log.d(TAG, ts.toString());
                        Log.d(TAG, (document.getTimestamp("timeStamp").compareTo(ts)) + "");
                    }
                }
                if(newDocuments.size() > 0) {
//                    Log.d(TAG, "found some docs");
                    Log.d(TAG, String.format(context.getString(R.string.NewIdeaMessage), newDocuments.size()));
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "FirebaseDB").setSmallIcon(R.drawable.besser)
                            .setContentTitle(context.getString(R.string.NewIdea))
                            .setContentText(String.format(context.getString(R.string.NewIdeaMessage), newDocuments.size()))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.notify(notificationID, builder.build());
                }
            }
        });
        Log.d(TAG, "End of function");
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final Context context = getApplicationContext();
            CharSequence name = context.getString(R.string.FirebaseDB);
            String description = context.getString(R.string.FirebaseDBDescription);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("FirebaseDB", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
