package com.example.dungeoncrawler.view;


import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dungeoncrawler.model.Game;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that implements the persistence facade using a Google Cloud Firestore database.
 */
public class FirestoreFacade implements IPersistenceFacade {


    private final FirebaseFirestore db = FirebaseFirestore.getInstance(); // database connection


    private static final String GAME_COLLECTION = "Leaderboard"; // sales collection name

    public class DataSave {
       private int depth;
       private String name;
       public DataSave(String name, Integer depth) {
           this.depth = depth.intValue();
           this.name = name;
       }
    }


    /**
     * Saves the game passed in as input to the underlying persistence solution.
     *
     * @param game the sale to be saved
     */
    public void saveGame(Game game, String name) {
        Map<String, Object> data = new HashMap<>();
        String entryName = name + " - " + game.pc.name;
        data.put("name", entryName);
        data.put("depth", game.depth);
        db.collection(GAME_COLLECTION).document().set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("Firestore", "Great Success!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Didn't get there");
                    }
                });
    }

    public class LeaderboardEntry {
        String name;
        int depth;

        public LeaderboardEntry(String name, int depth) {
            this.name = name;
            this.depth = depth;
        }
    }

    /**
     * Issues a ledger retrieval operation.
     *
     *
     * */
    public ArrayList<LeaderboardEntry> retrieveScores() {
        Log.d("Firestore", "inside retrieveScores");
        ArrayList<LeaderboardEntry> data = new ArrayList<>();
        this.db.collection(GAME_COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot qsnap) {
                            for (DocumentSnapshot document : qsnap) {
                                int i = (int) (long) document.get("depth");
                                String name = (String) document.get("name");
                                LeaderboardEntry entry = new LeaderboardEntry(name, i);
                                data.add(entry);
                                Log.d("Firestore", "accessed entry from Firestore");
                    }
                            Log.d("Firestore", data.toString());
                }
                });
        Log.d("Firestore", data.toString());
        return data;
    }


    @Override
    public void retRanked(LeaderBoardFragment fragment) {
        this.db.collection("Leaderboard")
                .orderBy("depth", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // document.getData() contains the data for each score
                                Map<String, Object> data = document.getData();
                                // You can use the data here
                                TextView view = new TextView(fragment.getContext());
                                view.setText(data.toString());
                                fragment.binding.leaderboardLayout.addView(view);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}


