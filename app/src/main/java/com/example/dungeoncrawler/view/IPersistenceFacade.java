package com.example.dungeoncrawler.view;

import androidx.annotation.NonNull;

import com.example.dungeoncrawler.model.Game;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Map;

public interface IPersistenceFacade {



        /* ledger-related methods start */
        /**
         * Saves the sale passed in as input to the underlying persistence solution.
         * @param game the sale to be saved
         */
        void saveGame(Game game, String name);


        /**
         * Retrieves the User with the specified username from the underlying persistence subsystem.
         *
         *
         */
        void retrieveScores(LeaderBoardFragment leaderBoardFragment);



    /* authentication-related methods end */

    }

