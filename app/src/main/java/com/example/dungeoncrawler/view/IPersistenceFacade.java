package com.example.dungeoncrawler.view;

import com.example.dungeoncrawler.model.Game;

import java.util.ArrayList;

public interface IPersistenceFacade {



    /* ledger-related methods start */
        /**
         * Saves the sale passed in as input to the underlying persistence solution.
         * @param game the sale to be saved
         */
        void saveGame(Game game, String name);



        public void retRanked(LeaderBoardFragment fragment);



    /* authentication-related methods end */

    }

