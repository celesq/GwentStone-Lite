package org.poo.cards;

import org.poo.fileio.Coordinates;
import org.poo.gameClasses.StartGame;

import java.util.ArrayList;

public class Miraj extends SpecialCard {

    public Miraj(final int attackDamage, final ArrayList<String> colors, final String description,
                 final int health, final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(1);
    }

    /**
     *
     * @param startGame startGame
     * @param cardAttacker cardAttacker
     * @param cardAttacked cardAttacked
     */
    @Override
    public void ability(final StartGame startGame, final Coordinates cardAttacker,
                           final Coordinates cardAttacked) {
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
