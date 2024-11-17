package main;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int nrCards;

    public Deck() {
        deck = new ArrayList<>();
        nrCards = 0;
    }

    public Deck(int nrCards) {
        deck = new ArrayList<>();
        this.nrCards = nrCards;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public int getNrCards() {
        return nrCards;
    }

    public void setNrCards(int nrCards) {
        this.nrCards = nrCards;
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void removeCard(Card card) {
        deck.remove(card);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
            for (Card card : deck) {
                result.append(card.toString());
            }
        return result.toString();
    }
}
