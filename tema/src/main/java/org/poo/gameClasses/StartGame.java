package org.poo.gameClasses;

import org.poo.fileio.CardInput;
import org.poo.fileio.GameInput;
import org.poo.fileio.Input;
import org.poo.fileio.StartGameInput;
import org.poo.heros.EmpressThorina;
import org.poo.heros.GeneralKocioraw;
import org.poo.heros.Hero;
import org.poo.heros.KingMudface;
import org.poo.heros.LordRoyce;
import org.poo.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class StartGame {
    private Player player1, player2;
    private int startingPlayer;
    private int currentTurn;
    private Input inputData;
    private int shuffleSeed;
    private Board board;
    private int currentRound;


    /**
     *
     * @return inputData
     */
    public Input getInputData() {
        return inputData;
    }

    /**
     *
     * @param inputData setter
     */
    public void setInputData(final Input inputData) {
        this.inputData = inputData;
    }

    /**
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     *
     * @param board setter
     */
    public void setBoard(final Board board) {
        this.board = board;
    }

    /**
     *
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     *
     * @param player1 setter
     */
    public void setPlayer1(final Player player1) {
        this.player1 = player1;
    }

    /**
     *
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     *
     * @param player2 setter
     */
    public void setPlayer2(final Player player2) {
        this.player2 = player2;
    }

    /**
     *
     * @return shuffle seed
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     *
     * @param shuffleSeed setter
     */
    public void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    /**
     *
     * @return startingPlayer
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     *
     * @param startingPlayer setter
     */
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    /**
     *
     * @return currentTurn
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
     *
     * @param currentTurn setter
     */
    public void setCurrentTurn(final int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     *
     * @return currentRound
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     *
     * @param currentRound setter
     */
    public void setCurrentRound(final int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     *
     * @param inputData input
     */
    public StartGame(final Input inputData) {
        this.inputData = inputData;
    }

    /**
     *
     * @param inputData input
     * @param game game
     * @param output output
     */
    public void setGame(final Input inputData, final GameInput game, final Output output) {
        board = new Board();

        Package //set players packs
                packagePlayerOne = new Package(inputData.getPlayerOneDecks().getNrDecks());
        Package packagePlayerTwo = new Package(inputData.getPlayerTwoDecks().getNrDecks());
        packagePlayerOne.makePack(inputData.getPlayerOneDecks().getDecks(),
                inputData.getPlayerOneDecks().getNrCardsInDeck());
        packagePlayerTwo.makePack(inputData.getPlayerTwoDecks().getDecks(),
                inputData.getPlayerTwoDecks().getNrCardsInDeck());

        //set players packs and number of decks
        player1 = new Player(inputData.getPlayerOneDecks().getNrDecks(), packagePlayerOne);
        player2 = new Player(inputData.getPlayerTwoDecks().getNrDecks(), packagePlayerTwo);

        //get the data for starting the game
        StartGameInput startGame = game.getStartGame();

        //set starting decks
        player1.setStartingDeck(startGame.getPlayerOneDeckIdx());
        player2.setStartingDeck(startGame.getPlayerTwoDeckIdx());

        //set shuffle seed, starting player, current turn, hero, deck ,and mana for each player
        setShuffleSeed(startGame.getShuffleSeed());
        setStartingPlayer(startGame.getStartingPlayer());
        currentTurn = startGame.getStartingPlayer();
        makeHero1(startGame.getPlayerOneHero());
        makeHero2(startGame.getPlayerTwoHero());
        setCurrentRound(1);
        player1.setCurrentMana(1);
        player2.setCurrentMana(1);
        makeDeckPlayerOne(player1.getPack().getPack());
        makeDeckPlayerTwo(player2.getPack().getPack());

        //shuffle decks and put first card in hand
        shuffleDeck(player1, getShuffleSeed());
        shuffleDeck(player2, getShuffleSeed());
        putCardinHand(player1);
        putCardinHand(player2);
        output.setCnt(2);
    }

    /**
     *
     * @param pack packPlayerOne
     */
    public void makeDeckPlayerOne(final ArrayList<Deck> pack) {
        int cnt = 0;
        for (Deck deckPlayerOne : pack) {
            if (cnt == player1.getStartingDeck()) {
                player1.setDeck(deckPlayerOne);
                break;
            }
            cnt++;
        }
    }

    /**
     *
     * @param pack packPlayerTwo
     */
    public void makeDeckPlayerTwo(final ArrayList<Deck> pack) {
        int cnt = 0;
        for (Deck deckPlayerTwo : pack) {
            if (cnt == player2.getStartingDeck()) {
                player2.setDeck(deckPlayerTwo);
                break;
            }
            cnt++;
        }
    }

    /**
     *
     * @param player player
     * @param shuffleSeed shuffleSeed
     */
    public void shuffleDeck(final Player player, final int shuffleSeed) {
        Random rand = new Random(shuffleSeed);
        Collections.shuffle(player.getDeck().getDeck(), rand);
    }

    /**
     *
     * @param card heroCard
     */
    public void makeHero1(final CardInput card) {
        Hero player1Hero = null;
        switch (card.getName()) {
            case "Lord Royce":
                player1Hero = new LordRoyce(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "Empress Thorina":
                player1Hero = new EmpressThorina(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "King Mudface":
                player1Hero = new KingMudface(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "General Kocioraw":
                player1Hero = new GeneralKocioraw(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
                default:
                    break;
        }
        player1.setHero(player1Hero);
    }

    /**
     *
     * @param card heroCard
     */
    public void makeHero2(final CardInput card) {
        Hero player2Hero = null;
        switch (card.getName()) {
            case "Lord Royce":
                player2Hero = new LordRoyce(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "Empress Thorina":
                player2Hero = new EmpressThorina(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "King Mudface":
                player2Hero = new KingMudface(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            case "General Kocioraw":
                player2Hero = new GeneralKocioraw(card.getMana(), card.getHealth(),
                        card.getDescription(), card.getColors(), card.getName());
                break;
            default:
                break;
        }
        player2.setHero(player2Hero);
    }

    /**
     *
     * @param player player
     */
    public void putCardinHand(final Player player) {
        if (!player.getDeck().getDeck().isEmpty()) {
            Card card = player.getDeck().getDeck().remove(0);
            player.getHand().add(card);
        }
    }

    /**
     * end player turn
     */
    public void endPlayerTurn() {
        if (currentTurn == 1) {
            currentTurn = 2;
        } else {
            currentTurn = 1;
        }
    }
}
