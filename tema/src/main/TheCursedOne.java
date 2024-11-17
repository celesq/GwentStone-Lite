package main;

import fileio.Coordinates;

import java.util.ArrayList;

public class TheCursedOne extends SpecialCard{

    public TheCursedOne(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    @Override
    protected void ability(StartGame startGame, Coordinates cardAttacker, Coordinates cardAttacked) {
        //Shapeshift;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        attacker.setHasAttacked(1);
        if(attacked.getAttackDamage() == 0)
            startGame.getBoard().eliminateCard(cardAttacked.getX(), cardAttacked.getY());
        else {
            int healthAttacked = attacked.getHealth();
            int attackDamageAttacked = attacked.getAttackDamage();
            attacked.setAttackDamage(healthAttacked);
            attacked.setHealth(attackDamageAttacked);
        }
    }
}
