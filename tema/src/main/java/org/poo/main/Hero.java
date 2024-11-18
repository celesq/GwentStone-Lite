package org.poo.main;

import java.util.ArrayList;

public class Hero extends Card{

    public Hero(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(0, colors, description, 30, mana, name);
    }

    public void ability(int affectedRow, Board board) {
    }

    @Override
    public String toString() {
        return "Card{" +
                "mana = " + super.getMana() + ", " +
                "health = " + super.getHealth() + ", " +
                "description = '" + super.getDescription() + '\'' + ", " +
                "colors : " + super.getColors() + '\'' + ", " +
                "name: " + super.getName() + '\'';
    }
}
