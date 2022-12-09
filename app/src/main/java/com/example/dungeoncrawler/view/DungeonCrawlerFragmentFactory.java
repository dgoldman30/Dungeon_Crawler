package com.example.dungeoncrawler.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.model.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DungeonCrawlerFragmentFactory  extends FragmentFactory {


        private static final String VIEW_PACKAGE = "com.example.dungeoncrawler.view"; // package where all the view reside
        private final ControllerActivity controller; // the controller instance to pass to fragments

        public Game game;

        /**
         * Constructor method.
         * @param controller the activity to pass in to fragments
         */
        public DungeonCrawlerFragmentFactory(ControllerActivity controller, Game game){
            this.controller = controller;
            this.game = game;
        }

        /**
         * Method used by fragment manager/transaction to instantiate fragments.
         * @param classLoader object to use to load fragment class
         * @param className name of fragment class to instantiate
         * @return instantiated fragment
         */
        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {

            // convert from class name to class
            Class<? extends Fragment> fragClass = loadFragmentClass(classLoader, className);

            // is this fragment in our view package? if so, it must be one of ours!
            if (fragClass.getPackage().getName().equals(VIEW_PACKAGE)) {
                try {
                    Constructor<?>[] fcons = fragClass.getConstructors(); // get all the constructors
                    assert fcons.length > 0 : "Fragment class does not have a constructor";
                    return (Fragment) fcons[0].newInstance(controller, game); // go with first constructor
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    final String emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                            "has a public constructor with a ControllerActivity parameter", fragClass);
                    Log.e("DungeonCrawler", emsg);
                    e.printStackTrace();
                }
            }

            // default is to delegate to superclass
            return super.instantiate(classLoader, className);
        }
    }
