package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Coordinates;

import java.util.ArrayList;

public class Output {
    ArrayList<ActionsInput> actions;
    private StartGame startGame;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ArrayNode output = objectMapper.createArrayNode();
    static int cnt = 2;

    public Output(ArrayList<ActionsInput> actions, StartGame startGame) {
        this.actions = actions;
        this.startGame = startGame;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    public StartGame getStartGame() {
        return startGame;
    }

    public void setStartGame(StartGame startGame) {
        this.startGame = startGame;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(ArrayNode output) {
        this.output = output;
    }

    public void iterateActions(ArrayList<ActionsInput> actions) {
        for (ActionsInput action : actions) {
            String currentCommand = action.getCommand();
            if (currentCommand.equals("getPlayerDeck"))
                getPlayerDeck(action.getPlayerIdx(), currentCommand);
            if (currentCommand.equals("getPlayerHero"))
                getPlayerHero(action.getPlayerIdx(), currentCommand);
            if (currentCommand.equals("getPlayerTurn"))
                getPlayerTurn(startGame.getCurrentTurn(), currentCommand);
            if (currentCommand.equals("endPlayerTurn"))
                endPlayerTurn();
            if (currentCommand.equals("placeCard"))
                placeCard(action.getHandIdx(), currentCommand);
            if (currentCommand.equals("getPlayerMana"))
                getPlayerMana(action.getPlayerIdx(), currentCommand);
            if (currentCommand.equals("getCardsOnTable"))
                getCardsOnTable(currentCommand);
            if (currentCommand.equals("getCardsInHand"))
                getCardsInHand(currentCommand, action.getPlayerIdx());
            if (currentCommand.equals("cardUsesAttack"))
                cardUsesAttack(currentCommand, action.getCardAttacker(), action.getCardAttacked());
            if (currentCommand.equals("getCardAtPosition"))
                getCardAtPosition(currentCommand, action.getX(), action.getY());
        }
    }
    private void getPlayerDeck(int playerIdx, String currentCommand) {
        Deck deck;
        if(playerIdx == 1)
            deck = startGame.getPlayer1().getDeck();
        else
            deck = startGame.getPlayer2().getDeck();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command" , currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Card cardTemp : deck.getDeck()) {
            ObjectNode objNode = objectMapper.createObjectNode();
            objNode.put("mana" , cardTemp.getMana());
            objNode.put("attackDamage" , cardTemp.getAttackDamage());
            objNode.put("health" , cardTemp.getHealth());
            objNode.put("description" , cardTemp.getDescription());
            ArrayNode colorsArray = objectMapper.createArrayNode();
            for (String color: cardTemp.getColors())
                colorsArray.add(color);
            objNode.set("colors", colorsArray);
            objNode.put("name" , cardTemp.getName());
            arrayNode.add(objNode);
        }
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }
    private void getPlayerHero(int playerIdx, String currentCommand) {
        Card hero;
        if (playerIdx == 1)
            hero = startGame.getPlayer1().getHero();
        else
            hero = startGame.getPlayer2().getHero();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.put("mana" , hero.getMana());
        objNode.put("description" , hero.getDescription());
        ArrayNode colorsArray = objectMapper.createArrayNode();
        for (String color: hero.getColors())
            colorsArray.add(color);
        objNode.set("colors", colorsArray);
        objNode.put("name" , hero.getName());
        objNode.put("health" , hero.getHealth());
        objectNode.put("output", objNode);
        output.add(objectNode);
    }

    private void getCardsOnTable(String currentCommand) {
        ArrayList<Card> cardsOnTable= startGame.getBoard().getCardsOnTable();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ArrayNode arrayNode2 = objectMapper.createArrayNode();
        ArrayNode arrayNode3 = objectMapper.createArrayNode();
        ArrayNode arrayNode4 = objectMapper.createArrayNode();
        ArrayNode arrayNode5 = objectMapper.createArrayNode();
        for (Card card : cardsOnTable) {


            if(card.getRow() == 0 && card.getWhoPlacedMe() == 2) {
                ObjectNode objNode = objectMapper.createObjectNode();
                objNode.put("mana", card.getMana());
                objNode.put("attackDamage", card.getAttackDamage());
                objNode.put("health", card.getHealth());
                objNode.put("description", card.getDescription());
                ArrayNode colorsArray = objectMapper.createArrayNode();
                for (String color : card.getColors())
                    colorsArray.add(color);
                objNode.set("colors", colorsArray);
                objNode.put("name", card.getName());
                arrayNode2.add(objNode);
            }

            if(card.getRow() == 1 && card.getWhoPlacedMe() == 2) {
                ObjectNode objNode = objectMapper.createObjectNode();
                objNode.put("mana", card.getMana());
                objNode.put("attackDamage", card.getAttackDamage());
                objNode.put("health", card.getHealth());
                objNode.put("description", card.getDescription());
                ArrayNode colorsArray = objectMapper.createArrayNode();
                for (String color : card.getColors())
                    colorsArray.add(color);
                objNode.set("colors", colorsArray);
                objNode.put("name", card.getName());
                arrayNode3.add(objNode);
            }

            if(card.getRow() == 1 && card.getWhoPlacedMe() == 1) {
                ObjectNode objNode = objectMapper.createObjectNode();
                objNode.put("mana", card.getMana());
                objNode.put("attackDamage", card.getAttackDamage());
                objNode.put("health", card.getHealth());
                objNode.put("description", card.getDescription());
                ArrayNode colorsArray = objectMapper.createArrayNode();
                for (String color : card.getColors())
                    colorsArray.add(color);
                objNode.set("colors", colorsArray);
                objNode.put("name", card.getName());
                arrayNode4.add(objNode);
            }

            if(card.getRow() == 0 && card.getWhoPlacedMe() == 1) {
                ObjectNode objNode = objectMapper.createObjectNode();
                objNode.put("mana", card.getMana());
                objNode.put("attackDamage", card.getAttackDamage());
                objNode.put("health", card.getHealth());
                objNode.put("description", card.getDescription());
                ArrayNode colorsArray = objectMapper.createArrayNode();
                for (String color : card.getColors())
                    colorsArray.add(color);
                objNode.set("colors", colorsArray);
                objNode.put("name", card.getName());
                arrayNode5.add(objNode);
            }

        }
        arrayNode.add(arrayNode2);
        arrayNode.add(arrayNode3);
        arrayNode.add(arrayNode4);
        arrayNode.add(arrayNode5);
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    private void getCardsInHand(String currentCommand, int playerIdx) {
        ArrayList<Card> cardsInHand;
        if (playerIdx == 1)
            cardsInHand = startGame.getPlayer1().getHand();
        else
            cardsInHand = startGame.getPlayer2().getHand();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Card card : cardsInHand) {
            ObjectNode objNode = objectMapper.createObjectNode();
            objNode.put("mana" , card.getMana());
            objNode.put("attackDamage" , card.getAttackDamage());
            objNode.put("health" , card.getHealth());
            objNode.put("description" , card.getDescription());
            ArrayNode colorsArray = objectMapper.createArrayNode();
            for (String color: card.getColors())
                colorsArray.add(color);
            objNode.set("colors", colorsArray);
            objNode.put("name" , card.getName());
            arrayNode.add(objNode);
        }
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    private void getPlayerTurn(int playerTurn, String currentCommand) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command" , currentCommand);
        objectNode.put("output", playerTurn);
        output.add(objectNode);
    }

    private void getPlayerMana(int playerIdx, String currentCommand) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command" , currentCommand);
        objectNode.put("playerIdx", playerIdx);
        if (playerIdx == 1)
            objectNode.put("output", startGame.getPlayer1().getCurrentMana());
        else
            objectNode.put("output", startGame.getPlayer2().getCurrentMana());
        output.add(objectNode);
    }

    private void placeCard(int handIdx, String currentCommand) {
        if (startGame.getCurrentTurn() == 1) {
            if (handIdx < startGame.getPlayer1().getHand().size()) {
                int manaCard = startGame.getPlayer1().getHand().get(handIdx).getMana();
                if (startGame.getPlayer1().getCurrentMana() >= manaCard) {
                    if (!startGame.getBoard().isFull(1, startGame.getPlayer1().getHand().get(handIdx).getRow())) {
                        startGame.getPlayer1().getHand().get(handIdx).setWhoPlacedMe(1);
                        startGame.getBoard().placeCard(1, startGame.getPlayer1().getHand().get(handIdx));
                        startGame.getPlayer1().getHand().remove(handIdx);
                        startGame.getPlayer1().setCurrentMana(startGame.getPlayer1().getCurrentMana() - manaCard);
                    } else {
                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.put("command" , currentCommand);
                        objectNode.put("handIdx", handIdx);
                        objectNode.put("error", "Cannot place card on table since row is full.");
                        output.add(objectNode);
                    }
                } else {
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command" , currentCommand);
                    objectNode.put("handIdx", handIdx);
                    objectNode.put("error", "Not enough mana to place card on table.");
                    output.add(objectNode);
                }
            }
        }
        else {
            if (handIdx < startGame.getPlayer2().getHand().size()) {
                int manaCard = startGame.getPlayer2().getHand().get(handIdx).getMana();
                if (startGame.getPlayer2().getCurrentMana() >= manaCard) {
                    if (!startGame.getBoard().isFull(2, startGame.getPlayer2().getHand().get(handIdx).getRow())) {
                        startGame.getPlayer2().getHand().get(handIdx).setWhoPlacedMe(2);
                        startGame.getBoard().placeCard(2, startGame.getPlayer2().getHand().get(handIdx));
                        startGame.getPlayer2().getHand().remove(handIdx);
                        startGame.getPlayer2().setCurrentMana(startGame.getPlayer2().getCurrentMana() - manaCard);
                    } else {
                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.put("command" , currentCommand);
                        objectNode.put("handIdx", handIdx);
                        objectNode.put("error", "Cannot place card on table since row is full.");
                        output.add(objectNode);
                    }
                } else {
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command" , currentCommand);
                    objectNode.put("handIdx", handIdx);
                    objectNode.put("error", "Not enough mana to place card on table.");
                    output.add(objectNode);
                }
            }
        }
    }

    private void endPlayerTurn() {
        startGame.endPlayerTurn();
        cnt++;
        if (cnt % 2 == 0) {
            if (startGame.getCurrentRound() <= 10) {
                startGame.setCurrentRound(startGame.getCurrentRound() + 1);
                startGame.getPlayer1().addMana(startGame.getCurrentRound());
                startGame.getPlayer2().addMana(startGame.getCurrentRound());
                startGame.putCardinHand(startGame.getPlayer1());
                startGame.putCardinHand(startGame.getPlayer2());
                startGame.getBoard().roundReset();
            }
        }
    }

    private void cardUsesAttack(String currentCommand, Coordinates cardAttacker, Coordinates cardAttacked) {
        if (startGame.getCurrentTurn() == 1 && cardAttacked.getX() > 1 || startGame.getCurrentTurn() == 2 && cardAttacked.getX() < 2) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command" , currentCommand);
            ObjectNode objectNode2 = objectMapper.createObjectNode();
            objectNode2.put("x" , cardAttacker.getX());
            objectNode2.put("y" , cardAttacker.getY());
            objectNode.put("cardAttacker", objectNode2);
            ObjectNode objectNode3 = objectMapper.createObjectNode();
            objectNode3.put("x" , cardAttacked.getX());
            objectNode3.put("y" , cardAttacked.getY());
            objectNode.put("cardAttacked", objectNode3);
            objectNode.put("error", "Attacked card does not belong to the enemy.");
            output.add(objectNode);
        } else if (startGame.getBoard().verifyIfAttacked(cardAttacker.getX(), cardAttacker.getY())) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command" , currentCommand);
            ObjectNode objectNode2 = objectMapper.createObjectNode();
            objectNode2.put("x" , cardAttacker.getX());
            objectNode2.put("y" , cardAttacker.getY());
            objectNode.put("cardAttacker", objectNode2);
            ObjectNode objectNode3 = objectMapper.createObjectNode();
            objectNode3.put("x" , cardAttacked.getX());
            objectNode3.put("y" , cardAttacked.getY());
            objectNode.put("cardAttacked", objectNode3);
            objectNode.put("error", "Attacker card has already attacked this turn.");
            output.add(objectNode);
        } else if(startGame.getBoard().verifyIfFrozen(cardAttacker.getX(), cardAttacker.getY())) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command" , currentCommand);
            ObjectNode objectNode2 = objectMapper.createObjectNode();
            objectNode2.put("x" , cardAttacker.getX());
            objectNode2.put("y" , cardAttacker.getY());
            objectNode.put("cardAttacker", objectNode2);
            ObjectNode objectNode3 = objectMapper.createObjectNode();
            objectNode3.put("x" , cardAttacked.getX());
            objectNode3.put("y" , cardAttacked.getY());
            objectNode.put("cardAttacked", objectNode3);
            objectNode.put("error", "Attacker card is frozen.");
            output.add(objectNode);
        } else if(!startGame.getBoard().verifyTank(cardAttacked.getX(), cardAttacked.getY(), startGame.getCurrentTurn())) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command" , currentCommand);
            ObjectNode objectNode2 = objectMapper.createObjectNode();
            objectNode2.put("x" , cardAttacker.getX());
            objectNode2.put("y" , cardAttacker.getY());
            objectNode.put("cardAttacker", objectNode2);
            ObjectNode objectNode3 = objectMapper.createObjectNode();
            objectNode3.put("x" , cardAttacked.getX());
            objectNode3.put("y" , cardAttacked.getY());
            objectNode.put("cardAttacked", objectNode3);
            objectNode.put("error", "Attacked card is not of type 'Tank'.");
            output.add(objectNode);
        } else startGame.getBoard().useAttack(cardAttacker.getX(), cardAttacker.getY(), cardAttacked.getX(), cardAttacked.getY());
    }

    public void getCardAtPosition(String currentCommand, int x, int y) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command" , currentCommand);
        objectNode.put("x" , x);
        objectNode.put("y" , y);
        Card card = startGame.getBoard().getCard(x, y);
        if(card.getName().isEmpty()) {
            objectNode.put("output", "No card available at that position.");
        } else {
            ObjectNode objNode = objectMapper.createObjectNode();
            objNode.put("mana" , card.getMana());
            objNode.put("attackDamage" , card.getAttackDamage());
            objNode.put("health" , card.getHealth());
            objNode.put("description" , card.getDescription());
            ArrayNode colorsArray = objectMapper.createArrayNode();
            for (String color: card.getColors())
                colorsArray.add(color);
            objNode.set("colors", colorsArray);
            objNode.put("name" , card.getName());
            objectNode.put("output", objNode);
        }
        output.add(objectNode);
    }
}
