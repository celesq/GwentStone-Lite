package main;

import java.util.ArrayList;

public class Disciple extends SpecialCard{

    public Disciple(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    @Override
    protected void ability() {
        //God's Plan;
    }
}
