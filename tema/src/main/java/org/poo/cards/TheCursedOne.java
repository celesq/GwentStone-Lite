package org.poo.cards;

import org.poo.fileio.Coordinates;
import org.poo.gameClasses.StartGame;

import java.util.ArrayList;

public class TheCursedOne extends SpecialCard {

    public TheCursedOne(final int attackDamage, final ArrayList<String> colors,
                        final String description, final int health,
                        final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    /**
     *
     * @param startGame current game
     * @param cardAttacker card attacker
     * @param cardAttacked card attacked
     */
    @Override
    public void ability(final StartGame startGame, final Coordinates cardAttacker,
                           final Coordinates cardAttacked) {
        //Shapeshift;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        attacker.setHasAttacked(1);
        if (attacked.getAttackDamage() == 0) {
            startGame.getBoard().eliminateCard(cardAttacked.getX(), cardAttacked.getY());
        } else {
            int healthAttacked = attacked.getHealth();
            int attackDamageAttacked = attacked.getAttackDamage();
            attacked.setAttackDamage(healthAttacked);
            attacked.setHealth(attackDamageAttacked);
        }
    }
}
