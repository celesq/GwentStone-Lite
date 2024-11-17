package main;

import fileio.CardInput;

import java.util.ArrayList;

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
        StringBuilder result = new StringBuilder("Package with " + nrDecks + " decks:\n");
        for (Deck deck : pack) {
            result.append(deck.toString()).append("\n");
        }
        return result.toString();
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
                        switch (card.getName()) {
                            case "The Ripper":
                                cardTemp = new TheRipper(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                                break;
                            case "Miraj":
                                cardTemp = new Miraj(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                                break;
                            case "The Cursed One":
                                cardTemp = new TheCursedOne(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                                break;
                            default:
                                cardTemp = new Disciple(card.getAttackDamage(), card.getColors(), card.getDescription(), card.getHealth(), card.getMana(), card.getName());
                                break;
                        }
                    }
                    deckTemp.addCard(cardTemp);
                }
                putDeckInPack(deckTemp);
        }
    }
}
