package com.example.dungeoncrawler.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.dungeoncrawler.ControllerActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DungeonCrawlerFragmentFactory  extends FragmentFactory {
    private ControllerActivity controller;
    public DungeonCrawlerFragmentFactory(ControllerActivity controller){
        super();
        this.controller = controller;
    }
    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);
        if (fragmentClass.getPackage().getName().equals("edu.vassar.cmpu203.dungeoncrawler.view")) { try {
            Constructor<?>[] fcons = fragmentClass.getConstructors(); // find all constructors assert fcons.length > 0 : "Fragment class does not have a constructor";
            return (Fragment) fcons[0].newInstance(controller); // invoke first constructor
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) { final String emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                "has a public constructor with a ControllerActivity parameter", fragmentClass); Log.e("DungeonCrawler", emsg);
            e.printStackTrace();
        }
        }
        return super.instantiate(classLoader, className); // delegate to super by default }
    }
}
