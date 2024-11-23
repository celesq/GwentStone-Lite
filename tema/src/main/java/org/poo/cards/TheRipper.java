package org.poo.cards;

import org.poo.fileio.Coordinates;
import org.poo.gameClasses.StartGame;

import java.util.ArrayList;

public class TheRipper extends SpecialCard {
    public TheRipper(final int attackDamage, final ArrayList<String> colors,
                     final String description, final int health,
                     final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
    }

    /**
     *
     * @param startGame current game
     * @param cardAttacker card attacked
     * @param cardAttacked card attacked
     */
    @Override
    public void ability(final StartGame startGame,
                           final Coordinates cardAttacker, final Coordinates cardAttacked) {
        //Weak Knees;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        if (attacked.getAttackDamage() < 2) {
            attacked.setAttackDamage(0);
        } else {
            attacked.setAttackDamage(attacked.getAttackDamage() - 2);
        }
        attacker.setHasAttacked(1);
    }
}
