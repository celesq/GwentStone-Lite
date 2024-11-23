package org.poo.cards;

import java.util.ArrayList;

public class SpecialCard extends Minion {
    public SpecialCard(final int attackDamage, final ArrayList<String> colors,
                       final String description, final int health,
                       final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
    }
}
