package main;

import java.util.ArrayList;

public class LordRoyce extends Hero{
    public LordRoyce(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(int affectedRow, Board board) {
        //Sub-Zero
        for (int j = 0; j < 5; j++) {
            board.getBoard()[affectedRow][j].setIsFrozen(1);
            board.getBoard()[affectedRow][j].setRoundsFrozen(0);
        }
    }
}
