package org.poo.main;

import java.util.ArrayList;

public class Player {
    private int numberDecks;
    private Package pack;
    private Deck deck;
    private Hero hero;
    private int startingDeck;
    private ArrayList<Card> hand;
    private int currentMana;

    public Player(int number, Package pack) {
        this.numberDecks = number;
        this.pack = pack;
        hand = new ArrayList<>();
    }

    public int getNumberDecks() {
        return numberDecks;
    }

    public int getStartingDeck() {
        return startingDeck;
    }

    public void setStartingDeck(int startingDeck) {
        this.startingDeck = startingDeck;
    }

    public void setNumberDecks(int numberDecks) {
        this.numberDecks = numberDecks;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void addMana(int mana) {
        currentMana += mana;
    }
}
