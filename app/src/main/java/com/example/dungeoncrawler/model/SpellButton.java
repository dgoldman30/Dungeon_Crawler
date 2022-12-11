package com.example.dungeoncrawler.model;

import android.content.Context;

import androidx.annotation.NonNull;

public class SpellButton extends androidx.appcompat.widget.AppCompatButton{

    Spell spell;

    public SpellButton(@NonNull Context context, Spell spell) {
        super(context);
        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.spell = spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Spell getSpell() { return spell; }
}
