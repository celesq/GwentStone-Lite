package org.poo.main;

import org.poo.fileio.CardInput;
import org.poo.fileio.GameInput;
import org.poo.fileio.Input;
import org.poo.fileio.StartGameInput;

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


    public Input getInputData() {
        return inputData;
    }

    public void setInputData(Input inputData) {
        this.inputData = inputData;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getShuffleSeed() {
        return shuffleSeed;
    }

    public void setShuffleSeed(int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public StartGame(Input inputData) {
        this.inputData = inputData;
    }

    public void setGame(Input inputData, GameInput game, Output output) {
        board = new Board();

        Package packagePlayerOne = new Package(inputData.getPlayerOneDecks().getNrDecks());
        Package packagePlayerTwo = new Package(inputData.getPlayerTwoDecks().getNrDecks());
        packagePlayerOne.makePack(inputData.getPlayerOneDecks().getDecks(), inputData.getPlayerOneDecks().getNrCardsInDeck());
        packagePlayerTwo.makePack(inputData.getPlayerTwoDecks().getDecks(), inputData.getPlayerTwoDecks().getNrCardsInDeck());

        player1 = new Player(inputData.getPlayerOneDecks().getNrDecks(), packagePlayerOne);
        player2 = new Player(inputData.getPlayerTwoDecks().getNrDecks(), packagePlayerTwo);

        player1.setPack(packagePlayerOne);
        player2.setPack(packagePlayerTwo);

        StartGameInput startGame = game.getStartGame();
        player1.setStartingDeck(startGame.getPlayerOneDeckIdx());
        player2.setStartingDeck(startGame.getPlayerTwoDeckIdx());
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
        shuffleDeck(player1, getShuffleSeed());
        shuffleDeck(player2, getShuffleSeed());
        putCardinHand(player1);
        putCardinHand(player2);
        getBoard().roundReset();
        output.setCnt(2);
    }

    public void makeDeckPlayerOne(ArrayList<Deck> pack) {
        int cnt = 0;
        for (Deck deckPlayerOne : pack) {
            if (cnt == player1.getStartingDeck()) {
                player1.setDeck(deckPlayerOne);
                break;
            }
            cnt++;
        }
    }
    public void makeDeckPlayerTwo(ArrayList<Deck> pack) {
        int cnt = 0;
        for (Deck deckPlayerTwo : pack) {
            if (cnt == player2.getStartingDeck()) {
                player2.setDeck(deckPlayerTwo);
                break;
            }
            cnt++;
        }
    }

    public void shuffleDeck(Player player, int shuffleSeed) {
        Random rand = new Random(shuffleSeed);
        Collections.shuffle(player.getDeck().getDeck(), rand);
    }

    public void makeHero1(CardInput card) {
        Hero player1Hero = null;
        switch (card.getName()) {
            case "Lord Royce":
                player1Hero = new LordRoyce(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "Empress Thorina":
                player1Hero = new EmpressThorina(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "King Mudface":
                player1Hero = new KingMudface(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "General Kocioraw":
                player1Hero = new GeneralKocioraw(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
        }
        player1.setHero(player1Hero);
    }

    public void makeHero2(CardInput card) {
        Hero player2Hero = null;
        switch (card.getName()) {
            case "Lord Royce":
                player2Hero = new LordRoyce(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "Empress Thorina":
                player2Hero = new EmpressThorina(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "King Mudface":
                player2Hero = new KingMudface(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
            case "General Kocioraw":
                player2Hero = new GeneralKocioraw(card.getMana(), card.getHealth(), card.getDescription(), card.getColors(), card.getName());
                break;
        }
        player2.setHero(player2Hero);
    }

    public void putCardinHand(Player player) {
        if (!player.getDeck().getDeck().isEmpty()) {
            Card card = player.getDeck().getDeck().remove(0);
            player.getHand().add(card);
        }
    }

    public void endPlayerTurn() {
        if (currentTurn == 1)
            currentTurn = 2;
        else
            currentTurn = 1;
    }
}
