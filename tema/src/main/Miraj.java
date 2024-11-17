package main;

import fileio.Coordinates;

import java.util.ArrayList;

public class Miraj extends SpecialCard{

    public Miraj(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
    }

    @Override
    protected void ability(StartGame startGame, Coordinates cardAttacker, Coordinates cardAttacked) {
        //Skyjack;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        int mirajHealth = attacker.getHealth();
        int attackedHealth = attacked.getHealth();
        attacked.setHealth(mirajHealth);
        attacker.setHealth(attackedHealth);
        attacker.setHasAttacked(1);
    }
}
