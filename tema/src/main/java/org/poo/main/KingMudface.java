package org.poo.main;

import java.util.ArrayList;

public class KingMudface extends Hero{
    public KingMudface(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(int affectedRow, Board board) {
        //Earth-Born
        for (int j = 0; j < 5; j++) {
            if (!board.getBoard()[affectedRow][j].getDescription().equals("")) {
                int health = board.getBoard()[affectedRow][j].getHealth();
                board.getBoard()[affectedRow][j].setHealth(health + 1);
            }
        }
    }
}
