package main;

import fileio.Coordinates;

import java.util.ArrayList;

public class Disciple extends SpecialCard{

    public Disciple(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    @Override
    protected void ability(StartGame startGame, Coordinates cardAttacker, Coordinates cardAttacked) {
        //God's Plan;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        attacked.setHealth(attacked.getHealth() + 2);
        attacker.setHasAttacked(1);
    }
}
