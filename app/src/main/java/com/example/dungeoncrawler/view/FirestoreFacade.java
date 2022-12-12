package com.example.dungeoncrawler.view;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.model.Game;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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


}


