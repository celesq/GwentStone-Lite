package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.GameInput;
import fileio.Input;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayGame {
    StartGame startGame;
    Input inputData;
    String filePath2;
    private int gameNumber = 0;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;

    public PlayGame(Input inputData, String filePath2) {
        this.inputData = inputData;
        this.filePath2 = filePath2;
    }

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    void play() throws IOException {
        ArrayList<GameInput> gameInput = inputData.getGames();
        startGame = new StartGame(inputData);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Output output = new Output(startGame);
        for (GameInput game : gameInput) {
            gameNumber++;
            startGame.setGame(inputData, game, output);
            output.iterateActions(game.getActions(), gameNumber, this);
            objectWriter.writeValue(new File(filePath2), output.getOutput());
        }
    }
}
