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

    public PlayGame(Input inputData, String filePath2) {
        this.inputData = inputData;
        this.filePath2 = filePath2;
    }

    void play() throws IOException {
        ArrayList<GameInput> gameInput = inputData.getGames();
        startGame = new StartGame(inputData);
        for (GameInput game : gameInput) {
            gameNumber++;
            startGame.setGame(inputData, game);
            Output output = new Output(game.getActions(), startGame);
            output.iterateActions(game.getActions());
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            objectWriter.writeValue(new File(filePath2), output.getOutput());
        }
    }
}
