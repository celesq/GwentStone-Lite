package org.poo.gameClasses;

import org.poo.fileio.CardInput;
import org.poo.cards.Card;
import org.poo.cards.Disciple;
import org.poo.cards.Minion;
import org.poo.cards.Miraj;
import org.poo.cards.Tank;
import org.poo.cards.TheCursedOne;
import org.poo.cards.TheRipper;

import java.util.ArrayList;

public class Package {
    private ArrayList<Deck> pack;
    private int nrDecks;

    public Package(final int nrDecks) {
        this.pack = new ArrayList<>();
        this.nrDecks = nrDecks;
    }

    /**
     * @return pack
     */
    public ArrayList<Deck> getPack() {
        return pack;
    }

    /**
     * @param pack setter
     */
    public void setPack(final ArrayList<Deck> pack) {
        this.pack = pack;
    }

    /**
     *
     * @return number of decks
     */
    public int getNrDecks() {
        return nrDecks;
    }

    /**
     *
     * @param nrDecks setter
     */
    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    /**
     *
     * @param deck puts deck in pack
     */
    public void putDeckInPack(final Deck deck) {
        pack.add(deck);
    }

    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Package with " + nrDecks + " decks:\n");
        for (Deck deck : pack) {
            result.append(deck.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     *
     * @param deckInput input of decks
     * @param nrCardsInDeck number of cards in a deck
     */
    public void makePack(final ArrayList<ArrayList<CardInput>> deckInput, final int nrCardsInDeck) {
        for (ArrayList<CardInput> deck : deckInput) {
            Deck deckTemp = new Deck(nrCardsInDeck);
                for (CardInput card : deck) {
                    Card cardTemp;
                    if (card.getName().equals("Sentinel") || card.getName().equals("Berserker")) {
                        cardTemp = new Minion(card.getAttackDamage(), card.getColors(),
                                card.getDescription(), card.getHealth(), card.getMana(),
                                card.getName());
                    } else if (card.getName().equals("Goliath")
                            || card.getName().equals("Warden")) {
                        cardTemp = new Tank(card.getAttackDamage(), card.getColors(),
                                card.getDescription(), card.getHealth(), card.getMana(),
                                card.getName());
                    } else {
                        switch (card.getName()) {
                            case "The Ripper":
                                cardTemp = new TheRipper(card.getAttackDamage(), card.getColors(),
                                        card.getDescription(), card.getHealth(), card.getMana(),
                                        card.getName());
                                break;
                            case "Miraj":
                                cardTemp = new Miraj(card.getAttackDamage(), card.getColors(),
                                        card.getDescription(), card.getHealth(), card.getMana(),
                                        card.getName());
                                break;
                            case "The Cursed One":
                                cardTemp = new TheCursedOne(card.getAttackDamage(),
                                        card.getColors(),
                                        card.getDescription(), card.getHealth(), card.getMana(),
                                        card.getName());
                                break;
                            default:
                                cardTemp = new Disciple(card.getAttackDamage(), card.getColors(),
                                        card.getDescription(), card.getHealth(), card.getMana(),
                                        card.getName());
                                break;
                        }
                    }
                    deckTemp.addCard(cardTemp);
                }
                putDeckInPack(deckTemp);
        }
    }
}
