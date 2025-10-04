package org.poo.heros;

import org.poo.gameClasses.Board;

import java.util.ArrayList;

public class KingMudface extends Hero {

    private static final int NR_COLUMNS = 5;

    public KingMudface(final int mana, final int health, final String description,
                       final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    /**
     *
     * @param affectedRow affected row
     * @param board board
     */
    @Override
    public void ability(final int affectedRow, final Board board) {
        //Earth-Born
        for (int j = 0; j < NR_COLUMNS; j++) {
            if (!board.getBoard()[affectedRow][j].getDescription().equals("")) {
                int health = board.getBoard()[affectedRow][j].getHealth();
                board.getBoard()[affectedRow][j].setHealth(health + 1);
            }
        }
    }
}
