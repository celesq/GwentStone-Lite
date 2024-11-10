package main;

import java.util.ArrayList;

public class Tank extends Minion{
    public Tank(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
        setIsTank(1);
    }
}
