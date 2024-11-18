package org.poo.main;

import java.util.ArrayList;

public class EmpressThorina extends Hero{
    public EmpressThorina(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(int affectedRow, Board board) {
        //Low-Blow
        int maxHealth = -1;
        int y = -1;
        for (int j = 0; j < 5; j++) {
            if(board.getBoard()[affectedRow][j].getHealth() > maxHealth) {
                maxHealth = board.getBoard()[affectedRow][j].getHealth();
                y = j;
            }
        }
        if (y >= 0) {
            board.eliminateCard(affectedRow, y);
        }
    }
}
