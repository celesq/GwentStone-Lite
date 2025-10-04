package org.poo.heros;

import org.poo.gameClasses.Board;

import java.util.ArrayList;

public class EmpressThorina extends Hero {

    private static final int NR_COLUMNS = 5;

    public EmpressThorina(final int mana, final int health, final String description,
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
        //Low-Blow
        int maxHealth = -1;
        int y = -1;
        for (int j = 0; j < NR_COLUMNS; j++) {
            if (board.getBoard()[affectedRow][j].getHealth() > maxHealth) {
                maxHealth = board.getBoard()[affectedRow][j].getHealth();
                y = j;
            }
        }
        if (y >= 0) {
            board.eliminateCard(affectedRow, y);
        }
    }
}
