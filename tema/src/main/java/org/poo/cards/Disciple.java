package org.poo.cards;

import org.poo.fileio.Coordinates;
import org.poo.gameClasses.StartGame;

import java.util.ArrayList;

public class Disciple extends SpecialCard {

    public Disciple(final int attackDamage, final ArrayList<String> colors,
                    final String description,
                    final int health, final int mana, final String name) {
        super(attackDamage, colors, description, health, mana, name);
        setRow(0);
    }

    /**
     * @param startGame    start game
     * @param cardAttacker card attacker
     * @param cardAttacked card attacked
     */
    @Override
    public void ability(final StartGame startGame, final Coordinates cardAttacker,
                           final Coordinates cardAttacked) {
        //God's Plan;
        Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
        Card attacked = startGame.getBoard().getCard(cardAttacked.getX(), cardAttacked.getY());
        attacked.setHealth(attacked.getHealth() + 2);
        attacker.setHasAttacked(1);
    }
}
