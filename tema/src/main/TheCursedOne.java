package main;

import java.util.ArrayList;

public class TheCursedOne extends SpecialCard{

    public TheCursedOne(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    @Override
    protected void ability() {
        //Shapeshift;
    }
}
