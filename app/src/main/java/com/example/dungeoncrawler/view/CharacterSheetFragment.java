package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentCharacterSheetBinding;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Skill;

import org.w3c.dom.Text;

public class CharacterSheetFragment extends Fragment implements ICharacterSheetFragment{

    FragmentCharacterSheetBinding binding;
    Listener listener;
    Game game;

    public CharacterSheetFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentCharacterSheetBinding.inflate(inflater);

        String charInfo = "Level " + game.pc.level + " " + game.pc.race.name() + " " + game.pc.caste.name();
        this.binding.characterInfo.setText(charInfo);
        String hpDisp = game.pc.HP.value + " / " + game.pc.maxHP;
        this.binding.hpValue.setText(hpDisp);

        this.binding.strButton.setText(game.pc.STR.value);
        this.binding.dexButton.setText(game.pc.DEX.value);
        this.binding.intButton.setText(game.pc.INT.value);
        this.binding.willButton.setText(game.pc.WILL.value);

        this.binding.dvButton.setText(game.pc.DV.value);
        this.binding.avButton.setText(game.pc.AV.value);
        this.binding.mvButton.setText(game.pc.MV.value);

        for (Skill s : game.pc.skills.values()) {
            TextView skill = new TextView(this.getRootView().getContext());
            String skillText = "" + s.name + ": " + s.value;
            skill.setText(skillText);
            this.binding.skillsLayout.addView(skill);
        }
        return this.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void attributeButtons() {

    }

    @Override
    public View getRootView() { return this.binding.getRoot(); }
}
