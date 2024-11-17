package main;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero{
    public GeneralKocioraw(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(int affectedRow, Board board) {
        //Blood-Thirst
        for (int j = 0; j < 5; j++) {
            if (!board.getBoard()[affectedRow][j].getDescription().equals("")) {
                int attackDamage = board.getBoard()[affectedRow][j].getAttackDamage();
                board.getBoard()[affectedRow][j].setAttackDamage(attackDamage + 1);
            }
        }
    }
}
