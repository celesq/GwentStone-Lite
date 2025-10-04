package org.poo.heros;

import org.poo.gameClasses.Board;
import org.poo.cards.Card;

import java.util.ArrayList;

public class Hero extends Card {

    private static final int HERO_HEALTH = 30;

    public Hero(final int mana, final int health, final String description,
                final ArrayList<String> colors, final String name) {
        super(0, colors, description, HERO_HEALTH, mana, name);
    }

    /**
     *
     * @param affectedRow affected row
     * @param board board
     */
    public void ability(final int affectedRow, final Board board) {
    }
}
