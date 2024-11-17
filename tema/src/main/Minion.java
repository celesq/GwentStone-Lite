package main;

import java.util.ArrayList;

public class Minion extends Card{

    private int isFrozen;

    public Minion() {
        super();
    }

    public Minion(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        isFrozen = 0;
        if (name.equals("Sentinel") || name.equals("Berserker"))
            setRow(0);
        setHasAttacked(0);
        setIsTank(0);
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

}
