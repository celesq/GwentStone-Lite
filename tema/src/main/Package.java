package main;

import fileio.CardInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Package {
    private ArrayList<Deck> pack;
    private int nrDecks;

    public Package(int nrDecks) {
        this.pack = new ArrayList<>();
        this.nrDecks = nrDecks;
    }

    public ArrayList<Deck> getPack() {
        return pack;
    }

    public void setPack(ArrayList<Deck> pack) {
        this.pack = pack;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public void setNrDecks(int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public void putDeckInPack(Deck deck) {
        pack.add(deck);
    }

    @Override
    public String toString() {
        String result = "Package with " + nrDecks + " decks:\n";
        for (Deck deck : pack) {
            result = result + deck.toString() + "\n";
        }
        return result;
    }


    public void makePack(ArrayList<ArrayList<CardInput>> deckInput, int nrCardsInDeck) {
        for (ArrayList<CardInput> deck : deckInput) {
            Deck deckTemp = new Deck(nrCardsInDeck);
                for (CardInput card : deck) {
                    Card cardTemp;
                    if (card.getName().equals("Sentinel") || card.getName().equals("Berserker"))
                        cardTemp = new Minion(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                    else if (card.getName().equals("Goliath") || card.getName().equals("Warden"))
                        cardTemp = new Tank(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                    else {
                        if (card.getName().equals("The Ripper"))
                            cardTemp = new TheRipper(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                        else if (card.getName().equals("Miraj"))
                            cardTemp = new Miraj(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                        else if (card.getName().equals("The Cursed One"))
                            cardTemp = new TheCursedOne(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                        else cardTemp = new Disciple(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                    }
                    deckTemp.addCard(cardTemp);
                }
                putDeckInPack(deckTemp);
        }
    }
}
