package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentInventoryBinding;
import com.example.dungeoncrawler.databinding.FragmentLeaderboardBinding;
import com.example.dungeoncrawler.model.Game;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

public class LeaderBoardFragment extends Fragment implements ILeaderBoardFragment {
    public Map<String, Integer> leaderboardMap;
    Game game;
    Listener listener;
    FragmentLeaderboardBinding binding;
    private final String CURGAME = "cur_Game";


    public LeaderBoardFragment(Listener listener, Game game) {
        this.game = game;
        this.listener = listener;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentLeaderboardBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle saveInstanceState){
        super.onViewStateRestored(saveInstanceState);
        if (saveInstanceState != null) {
            this.game = (Game) saveInstanceState.getSerializable(CURGAME);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayLeaderboard();
        binding.closeLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onGameRestart();
            }
        });


    }

    public void displayLeaderboard() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(this.leaderboardMap.entrySet());
        LinearLayout leaderboardLayout = new LinearLayout(this.getContext());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        for (Map.Entry entry : entries) {
            TextView highScore = new TextView(this.getContext());
            String score = entry.getKey() + " - depth: " + entry.getValue();
            highScore.setText(score);
            binding.leaderboardLayout.addView(highScore);
        }
        binding.leaderboardScroll.removeAllViews();
        binding.leaderboardScroll.addView(leaderboardLayout);
    }
}
