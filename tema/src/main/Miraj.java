package main;

import java.util.ArrayList;

public class Miraj extends SpecialCard{

    public Miraj(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
    }

    @Override
    protected void ability() {
        //Skyjack;
    }
}
