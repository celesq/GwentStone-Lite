package org.poo.main;

import java.util.ArrayList;

public class Minion extends Card{
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
     * checks if it is frozen
     */
    public int getIsFrozen() {
        return isFrozen;
    }

    /**
     * sets frozen
     */
    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

}
