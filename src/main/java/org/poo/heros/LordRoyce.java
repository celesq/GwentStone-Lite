package org.poo.heros;

import org.poo.gameClasses.Board;

import java.util.ArrayList;

public class LordRoyce extends Hero {

    private static final int NR_COLUMNS = 5;

    public LordRoyce(final int mana, final int health, final String description,
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
        //Sub-Zero
        for (int j = 0; j < NR_COLUMNS; j++) {
            board.getBoard()[affectedRow][j].setIsFrozen(1);
            board.getBoard()[affectedRow][j].setRoundsFrozen(0);
        }
    }
}
