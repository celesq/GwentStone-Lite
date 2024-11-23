package org.poo.cards;

import java.util.ArrayList;

public class Tank extends Minion {
    public Tank(final int attackDamage, final ArrayList<String> colors, final String description,
                final int health, final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
        setIsTank(1);
    }
}
