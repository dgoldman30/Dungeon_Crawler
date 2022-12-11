package com.example.dungeoncrawler.view;

import androidx.annotation.NonNull;

import com.example.dungeoncrawler.model.Game;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public interface IPersistenceFacade {

    /**
         * Interface that classes interested in being notified of data-generating events
         * from the persistence layer should implement.
         */
        interface DataListener<T> {

        void onDataReceived(@NonNull ArrayList data);

        /**
             * Called when the requested data isn't found in the underlying persistence subsystem.
             */
            void onNoDataFound();
        }

        /**
         * Interface that classes interested in being notified of binary (i.e., true vs false) events
         * from the persistence layer should implement.
         */
        interface BinaryResultListener {
            /**
             * Called when the answer to the issued query is positive.
             */
            void onYesResult();
            /**
             * Called when the answer to the issued query is negative.
             */
            void onNoResult();
        }


        /* ledger-related methods start */
        /**
         * Saves the sale passed in as input to the underlying persistence solution.
         * @param game the sale to be saved
         */
        void saveGame(Game game);

        /**
         * Issues a ledger retrieval operation.
         * @param listener the listener to be notified when the ledger becomes available.
         */
        void retrieveGame(DataListener<Game> listener);

        /* ledger-related methods end */

        /* authentication-related methods start */

        /**
         *  Creates an entry for the specified User in the underlying persistence subsystem.
         *
         * @param game the user to be created
         * @param listener the observer to be notified of the query result. OnYesResult() is called if
         *                 a new user was created. Conversely, OnNoResult() is called if a user with
         *                 the specified username already existed.
         */
        void createGameIfNotExists(@NonNull Game game, @NonNull BinaryResultListener listener);

        /**
         * Retrieves the User with the specified username from the underlying persistence subsystem.
         *
         * @param charName the username of the user to be retrieved.
         * @param listener observer to be notified of query result. onDataReceived() is called if a
         *                 user with the specified username was found. Otherwise, onNoDataFound() is
         *                 called.
         */
        void retrieveUser(@NonNull String charName, @NonNull DataListener<Game> listener);

        /* authentication-related methods end */

    }

