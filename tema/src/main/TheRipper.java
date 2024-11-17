package main;

import fileio.Coordinates;

import java.util.ArrayList;

public class TheRipper extends SpecialCard{
    public TheRipper(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
    }

    @Override
    protected void ability(StartGame startGame, Coordinates cardAttacker, Coordinates cardAttacked) {
        //Weak Knees;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        if (attacked.getAttackDamage() < 2)
            attacked.setAttackDamage(0);
        else
            attacked.setAttackDamage(attacked.getAttackDamage() - 2);
        attacker.setHasAttacked(1);
    }
}
