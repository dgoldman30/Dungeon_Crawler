package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentInventoryBinding;
import com.example.dungeoncrawler.databinding.FragmentLeaderboardBinding;
import com.example.dungeoncrawler.model.Game;

public class LeaderBoardFragment extends Fragment implements ILeaderBoardFragment {
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

    }
}
