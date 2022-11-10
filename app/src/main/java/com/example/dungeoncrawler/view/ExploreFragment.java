package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Tile;

import java.util.List;

public class ExploreFragment extends Fragment implements IExploreFragment {

    FragmentExploreBinding binding;
    Listener listener;
    Game game;

    public ExploreFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentExploreBinding.inflate(inflater);
        return inflater.inflate(R.layout.fragment_char_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView mapView = this.binding.mapView;
        mapView.setText(printMap(game));

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();

                return false;
            }
        });
    }

    private String printMap(Game game) {
        String ret = "";
        Tile[][] map = game.map;
        char[][] charMap = new char[map.length][map.length];

        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map.length; ++j) {
                charMap[i][j] = map[i][j].display();
                ret = ret + charMap[i][j] + " ";
                if (j == map.length - 1) {
                    ret = ret + "\n";
                }
            }
        }
        return ret;
    }

    public void move(float x, float y, Game game) {

    }



    @Override
    public View getRootView() { return this.binding.getRoot(); }
}
