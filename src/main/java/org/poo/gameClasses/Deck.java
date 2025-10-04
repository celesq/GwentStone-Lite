package org.poo.gameClasses;

import org.poo.cards.Card;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int nrCards;

    public Deck() {
        deck = new ArrayList<>();
        nrCards = 0;
    }

    public Deck(final int nrCards) {
        deck = new ArrayList<>();
        this.nrCards = nrCards;
    }

    /**
     *
     * @return deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     *
     * @param deck setter
     */
    public void setDeck(final ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     *
     * @return nrCards getter
     */
    public int getNrCards() {
        return nrCards;
    }

    /**
     *
     * @param nrCards setter
     */
    public void setNrCards(final int nrCards) {
        this.nrCards = nrCards;
    }

    /**
     *
     * @param card which has to be added
     */
    public void addCard(final Card card) {
        deck.add(card);
    }

    /**
     *
     * @param card which has to be removed
     */
    public void removeCard(final Card card) {
        deck.remove(card);
    }
}
