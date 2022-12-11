package com.example.dungeoncrawler.view;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dungeoncrawler.model.Game;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.auth.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that implements the persistence facade using a Google Cloud Firestore database.
 */
public class FirestoreFacade implements IPersistenceFacade {


    private final FirebaseFirestore db = FirebaseFirestore.getInstance(); // database connection


    private static final String GAME_COLLECTION = "Leaderboard"; // sales collection name



    /**
     * Saves the game passed in as input to the underlying persistence solution.
     *
     * @param game the sale to be saved
     */
    public void saveGame(Game game) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", game.pc.name);
        data.put("depth", game.depth);
        db.collection(GAME_COLLECTION).document("Scores").set(data)
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



    /**
     * Issues a ledger retrieval operation.
     *
     * @param listener the listener to be notified when the ledger becomes available.
     */
    public void retrieveGame(DataListener<Game> listener) {
        this.db.collection(GAME_COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() { // called when the data comes back from the database
                    @Override
                    public void onSuccess(QuerySnapshot qsnap) {
                        ArrayList leaderBoard = new ArrayList();
                        for (DocumentSnapshot dsnap : qsnap) {
                            Game game = dsnap.toObject(Game.class);
                            leaderBoard.add(game);
                        }
                        listener.onDataReceived(leaderBoard); // let the listener know we have a ledger now
                    }
                });
    }


    /**
     * Creates an entry for the specified User in the underlying persistence subsystem.
     *
     * @param game     the user to be created
     * @param listener the observer to be notified of the query result. OnYesResult() is called if
     *                 a new user was created. Conversely, OnNoResult() is called if a user with
     *                 the specified username already existed.
     */
    @Override
    public void createGameIfNotExists(@NonNull Game game, @NonNull BinaryResultListener listener) {

        String charName = game.pc.name;

        this.retrieveUser(charName, new DataListener<Game>() {
            @Override
            public void onDataReceived(@NonNull ArrayList data) {
                listener.onNoResult();
            }


            @Override
            public void onNoDataFound() {
                db.collection(GAME_COLLECTION).document(charName).
                        set(game).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                listener.onYesResult();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                listener.onNoResult();
                            }
                        });

            }
        });
    }

    @Override
    public void retrieveUser(@NonNull String charName, @NonNull DataListener<Game> listener) {

    }
}


