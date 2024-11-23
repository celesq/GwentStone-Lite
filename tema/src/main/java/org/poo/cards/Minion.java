package org.poo.cards;

import java.util.ArrayList;

public class Minion extends Card {
    private int isFrozen;

    public Minion() {
        super();
    }

    public Minion(final int attackDamage, final ArrayList<String> colors, final String description,
                  final int health, final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        isFrozen = 0;
        if (name.equals("Sentinel") || name.equals("Berserker")) {
            setRow(0);
        }
        setHasAttacked(0);
        setIsTank(0);
    }

    /**
     *
     * @return is frozen
     */
    public int getIsFrozen() {
        return isFrozen;
    }

    /**
     *
     * @param isFrozen setter
     */
    public void setIsFrozen(final int isFrozen) {
        this.isFrozen = isFrozen;
    }

}
