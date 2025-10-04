package org.poo.gameClasses;

import org.poo.cards.Card;
import org.poo.heros.Hero;

import java.util.ArrayList;

public class Player {
    private int numberDecks;
    private org.poo.gameClasses.Package pack;
    private Deck deck;
    private Hero hero;
    private int startingDeck;
    private ArrayList<Card> hand;
    private int currentMana;


    public Player(final int number, final org.poo.gameClasses.Package pack) {
        this.numberDecks = number;
        this.pack = pack;
        hand = new ArrayList<>();
    }

    /**
     *
     * @return number of decks
     */
    public int getNumberDecks() {
        return numberDecks;
    }

    /**
     *
     * @return starting deck
     */
    public int getStartingDeck() {
        return startingDeck;
    }

    /**
     *
     * @param startingDeck setter
     */
    public void setStartingDeck(final int startingDeck) {
        this.startingDeck = startingDeck;
    }

    /**
     *
     * @param numberDecks setter
     */
    public void setNumberDecks(final int numberDecks) {
        this.numberDecks = numberDecks;
    }

    /**
     *
     * @return pack getter
     */
    public org.poo.gameClasses.Package getPack() {
        return pack;
    }

    /**
     *
     * @param pack setter
     */
    public void setPack(final Package pack) {
        this.pack = pack;
    }

    /**
     *
     * @return deck getter
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     *
     * @param deck setter
     */
    public void setDeck(final Deck deck) {
        this.deck = deck;
    }

    /**
     *
     * @return hero getter
     */
    public Hero getHero() {
        return hero;
    }

    /**
     *
     * @param hero setter
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     *
     * @return hand getter
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     *
     * @param hand setter
     */
    public void setHand(final ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     *
     * @return mana getter
     */
    public int getCurrentMana() {
        return currentMana;
    }

    /**
     *
     * @param currentMana setter
     */
    public void setCurrentMana(final int currentMana) {
        this.currentMana = currentMana;
    }

    /**
     *
     * @param mana added
     */
    public void addMana(final int mana) {
        currentMana += mana;
    }
}
