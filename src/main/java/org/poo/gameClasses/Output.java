package org.poo.gameClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Card;
import org.poo.fileio.ActionsInput;
import org.poo.fileio.Coordinates;

import java.util.ArrayList;

public class Output {
    private static final int NR_ROWS = 4;
    private static final int NR_COLUMNS = 5;
    private static final int MAX_ROUNDS_FOR_MANA_INC = 10;
    private ArrayList<ActionsInput> actions;
    private StartGame startGame;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ArrayNode output = objectMapper.createArrayNode();
    private int cnt = 2;

    public Output(final StartGame startGame) {
        this.startGame = startGame;
    }

    /**
     * @return actions
     */
    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    /**
     *
     * @param actions setter
     */
    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    /**
     *
     * @return startGame
     */
    public StartGame getStartGame() {
        return startGame;
    }

    /**
     *
     * @param startGame setter
     */
    public void setStartGame(final StartGame startGame) {
        this.startGame = startGame;
    }

    /**
     *
     * @return objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     *
     * @param objectMapper setter
     */
    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * @param output setter
     */
    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    /**
     *
     * @return cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     *
     * @param cnt setter
     */
    public void setCnt(final int cnt) {
        this.cnt = cnt;
    }

    /**
     *
     * @param iterateActions arrayList of actions
     * @param gameNumber the game number
     * @param game current game
     */
    public void iterateActions(final ArrayList<ActionsInput> iterateActions, final int gameNumber,
                               final PlayGame game) {
        for (ActionsInput action : iterateActions) {
            String currentCommand = action.getCommand();
            switch (currentCommand) {
                case "getPlayerDeck":
                    getPlayerDeck(action.getPlayerIdx(), currentCommand);
                    break;
                case "getPlayerHero":
                    getPlayerHero(action.getPlayerIdx(), currentCommand);
                    break;
                case "getPlayerTurn":
                    getPlayerTurn(startGame.getCurrentTurn(), currentCommand);
                    break;
                case "endPlayerTurn":
                    endPlayerTurn();
                    break;
                case "placeCard":
                    placeCard(action.getHandIdx(), currentCommand);
                    break;
                case "getPlayerMana":
                    getPlayerMana(action.getPlayerIdx(), currentCommand);
                    break;
                case "getCardsOnTable":
                    getCardsOnTable(currentCommand);
                    break;
                case "getCardsInHand":
                    getCardsInHand(currentCommand, action.getPlayerIdx());
                    break;
                case "cardUsesAttack":
                    cardUsesAttack(currentCommand, action.getCardAttacker(),
                            action.getCardAttacked());
                    break;
                case "getCardAtPosition":
                    getCardAtPosition(currentCommand, action.getX(), action.getY());
                    break;
                case "cardUsesAbility":
                    cardUsesAbility(currentCommand, action.getCardAttacker(),
                            action.getCardAttacked());
                    break;
                case "useAttackHero":
                    useAttackHero(currentCommand, action.getCardAttacker(), game);
                    break;
                case "useHeroAbility":
                    useHeroAbility(currentCommand, action.getAffectedRow(),
                            startGame.getCurrentTurn());
                    break;
                case "getFrozenCardsOnTable":
                    getFrozenCards(currentCommand);
                    break;
                case "getTotalGamesPlayed":
                    getTotalGamesPlayed(currentCommand, gameNumber);
                    break;
                case "getPlayerOneWins":
                    getPlayerOneWinsCommand(currentCommand, game.getPlayerOneWins());
                    break;
                case "getPlayerTwoWins":
                    getPlayerTwoWinsCommand(currentCommand, game.getPlayerTwoWins());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *
     * @param playerIdx player number
     * @param currentCommand current command
     */
    private void getPlayerDeck(final int playerIdx, final String currentCommand) {
        Deck deck;
        if (playerIdx == 1) {
            deck = startGame.getPlayer1().getDeck();
        } else {
            deck = startGame.getPlayer2().getDeck();
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Card cardTemp : deck.getDeck()) {
            printCard(arrayNode, cardTemp);
        }
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    /**
     *
     * @param arrayNode the array for the output
     * @param cardTemp the card that needs to be printed
     */
    private void printCard(final ArrayNode arrayNode, final Card cardTemp) {
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.put("mana", cardTemp.getMana());
        objNode.put("attackDamage", cardTemp.getAttackDamage());
        objNode.put("health", cardTemp.getHealth());
        objNode.put("description", cardTemp.getDescription());
        ArrayNode colorsArray = objectMapper.createArrayNode();
        for (String color : cardTemp.getColors()) {
            colorsArray.add(color);
        }
        objNode.set("colors", colorsArray);
        objNode.put("name", cardTemp.getName());
        arrayNode.add(objNode);
    }

    /**
     *
     * @param currentCommand current command
     * gets frozen cards
     */
    private void getFrozenCards(final String currentCommand) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (int i = 0; i < NR_ROWS; i++) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (startGame.getBoard().getBoard()[i][j].getIsFrozen() == 1) {
                    Card cardTemp = startGame.getBoard().getBoard()[i][j];
                    if (!cardTemp.getDescription().isEmpty()) {
                        printCard(arrayNode, cardTemp);
                    }
                }
            }
        }
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    /**
     *
     * @param playerIdx player number
     * @param currentCommand current command
     */
    private void getPlayerHero(final int playerIdx, final String currentCommand) {
        Card hero;
        if (playerIdx == 1) {
            hero = startGame.getPlayer1().getHero();
        } else {
            hero = startGame.getPlayer2().getHero();
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.put("mana", hero.getMana());
        objNode.put("description", hero.getDescription());
        ArrayNode colorsArray = objectMapper.createArrayNode();
        for (String color : hero.getColors()) {
            colorsArray.add(color);
        }
        objNode.set("colors", colorsArray);
        objNode.put("name", hero.getName());
        objNode.put("health", hero.getHealth());
        objectNode.put("output", objNode);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     */
    private void getCardsOnTable(final String currentCommand) {
        ArrayList<Card> cardsOnTable = startGame.getBoard().getCardsOnTable();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ArrayNode arrayNode2 = objectMapper.createArrayNode();
        ArrayNode arrayNode3 = objectMapper.createArrayNode();
        ArrayNode arrayNode4 = objectMapper.createArrayNode();
        ArrayNode arrayNode5 = objectMapper.createArrayNode();
        for (Card card : cardsOnTable) {
            if (card.getRow() == 0 && card.getWhoPlacedMe() == 2) {
                printCard(arrayNode2, card);
            }
            if (card.getRow() == 1 && card.getWhoPlacedMe() == 2) {
                printCard(arrayNode3, card);
            }
            if (card.getRow() == 1 && card.getWhoPlacedMe() == 1) {
                printCard(arrayNode4, card);
            }
            if (card.getRow() == 0 && card.getWhoPlacedMe() == 1) {
                printCard(arrayNode5, card);
            }
        }
        arrayNode.add(arrayNode2);
        arrayNode.add(arrayNode3);
        arrayNode.add(arrayNode4);
        arrayNode.add(arrayNode5);
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param playerIdx player number
     */

    private void getCardsInHand(final String currentCommand, final int playerIdx) {
        ArrayList<Card> cardsInHand;
        if (playerIdx == 1) {
            cardsInHand = startGame.getPlayer1().getHand();
        } else {
            cardsInHand = startGame.getPlayer2().getHand();
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Card card : cardsInHand) {
            printCard(arrayNode, card);
        }
        objectNode.put("output", arrayNode);
        output.add(objectNode);
    }

    /**
     *
     * @param playerTurn turn number
     * @param currentCommand current command
     */
    private void getPlayerTurn(final int playerTurn, final String currentCommand) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("output", playerTurn);
        output.add(objectNode);
    }

    /**
     *
     * @param playerIdx player number
     * @param currentCommand current command
     */
    private void getPlayerMana(final int playerIdx, final String currentCommand) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("playerIdx", playerIdx);
        if (playerIdx == 1) {
            objectNode.put("output", startGame.getPlayer1().getCurrentMana());
        } else {
            objectNode.put("output", startGame.getPlayer2().getCurrentMana());
        }
        output.add(objectNode);
    }

    /**
     *
     * @param handIdx card number
     * @param currentCommand current command
     */
    private void placeCard(final int handIdx, final String currentCommand) {
        if (startGame.getCurrentTurn() == 1) {
            if (handIdx < startGame.getPlayer1().getHand().size()) {
                int manaCard = startGame.getPlayer1().getHand().get(handIdx).getMana();
                if (startGame.getPlayer1().getCurrentMana() >= manaCard) {
                    if (startGame.getBoard().isFull(1,
                            startGame.getPlayer1().getHand().get(handIdx).getRow())) {
                        startGame.getPlayer1().getHand().get(handIdx).setWhoPlacedMe(1);
                        startGame.getBoard().placeCard(1,
                                startGame.getPlayer1().getHand().get(handIdx));
                        startGame.getPlayer1().getHand().remove(handIdx);
                        startGame.getPlayer1().setCurrentMana(
                                startGame.getPlayer1().getCurrentMana() - manaCard);
                    } else {
                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.put("command", currentCommand);
                        objectNode.put("handIdx", handIdx);
                        objectNode.put("error",
                                "Cannot place card on table since row is full.");
                        output.add(objectNode);
                    }
                } else {
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command", currentCommand);
                    objectNode.put("handIdx", handIdx);
                    objectNode.put("error",
                            "Not enough mana to place card on table.");
                    output.add(objectNode);
                }
            }
        } else {
            if (handIdx < startGame.getPlayer2().getHand().size()) {
                int manaCard = startGame.getPlayer2().getHand().get(handIdx).getMana();
                if (startGame.getPlayer2().getCurrentMana() >= manaCard) {
                    if (startGame.getBoard().isFull(2,
                            startGame.getPlayer2().getHand().get(handIdx).getRow())) {
                        startGame.getPlayer2().getHand().get(handIdx).setWhoPlacedMe(2);
                        startGame.getBoard().placeCard(2,
                                startGame.getPlayer2().getHand().get(handIdx));
                        startGame.getPlayer2().getHand().remove(handIdx);
                        startGame.getPlayer2().setCurrentMana(
                                startGame.getPlayer2().getCurrentMana() - manaCard);
                    } else {
                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.put("command", currentCommand);
                        objectNode.put("handIdx", handIdx);
                        objectNode.put("error",
                                "Cannot place card on table since row is full.");
                        output.add(objectNode);
                    }
                } else {
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command", currentCommand);
                    objectNode.put("handIdx", handIdx);
                    objectNode.put("error",
                            "Not enough mana to place card on table.");
                    output.add(objectNode);
                }
            }
        }
    }

    /**
     * end player turn
     */
    private void endPlayerTurn() {
        startGame.endPlayerTurn();
        cnt++;
        startGame.getBoard().incrementFrozen();
        if (cnt % 2 == 0) {
            if (startGame.getCurrentRound() < MAX_ROUNDS_FOR_MANA_INC) {
                startGame.setCurrentRound(startGame.getCurrentRound() + 1);
                startGame.getPlayer1().addMana(startGame.getCurrentRound());
                startGame.getPlayer2().addMana(startGame.getCurrentRound());
                startGame.putCardinHand(startGame.getPlayer1());
                startGame.putCardinHand(startGame.getPlayer2());
                startGame.getBoard().roundReset();
                startGame.getPlayer1().getHero().setHasAttacked(0);
                startGame.getPlayer2().getHero().setHasAttacked(0);
            } else {
                startGame.setCurrentRound(startGame.getCurrentRound() + 1);
                startGame.putCardinHand(startGame.getPlayer1());
                startGame.putCardinHand(startGame.getPlayer2());
                startGame.getBoard().roundReset();
                startGame.getPlayer1().getHero().setHasAttacked(0);
                startGame.getPlayer2().getHero().setHasAttacked(0);
                startGame.getPlayer1().addMana(MAX_ROUNDS_FOR_MANA_INC);
                startGame.getPlayer2().addMana(MAX_ROUNDS_FOR_MANA_INC);
            }
        }
    }

    /**
     *
     * @param currentCommand current command
     * @param cardAttacker attacked card
     * @param cardAttacked attacked card
     */
    private void cardUsesAttack(final String currentCommand, final Coordinates cardAttacker,
                                final Coordinates cardAttacked) {
        if (startGame.getCurrentTurn() == 1 && cardAttacked.getX() > 1
                || startGame.getCurrentTurn() == 2 && cardAttacked.getX() < 2) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacked card does not belong to the enemy.");
        } else if (startGame.getBoard().verifyIfAttacked(cardAttacker.getX(),
                cardAttacker.getY())) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacker card has already attacked this turn.");
        } else if (startGame.getBoard().verifyIfFrozen(cardAttacker.getX(),
                cardAttacker.getY())) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacker card is frozen.");
        } else if (startGame.getBoard().verifyTank(cardAttacked.getX(),
                cardAttacked.getY(), startGame.getCurrentTurn())) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacked card is not of type 'Tank'.");
        } else {
            startGame.getBoard().useAttack(cardAttacker.getX(), cardAttacker.getY(),
                    cardAttacked.getX(), cardAttacked.getY());
        }
    }

    /**
     *
     * @param currentCommand current command
     * @param cardAttacker card attacker
     * @param cardAttacked card attacked
     * @param errorMessage error
     */

    private void errorCard(final String currentCommand, final Coordinates cardAttacker,
                           final Coordinates cardAttacked, final String errorMessage) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode2.put("x", cardAttacker.getX());
        objectNode2.put("y", cardAttacker.getY());
        objectNode.put("cardAttacker", objectNode2);
        ObjectNode objectNode3 = objectMapper.createObjectNode();
        objectNode3.put("x", cardAttacked.getX());
        objectNode3.put("y", cardAttacked.getY());
        objectNode.put("cardAttacked", objectNode3);
        objectNode.put("error", errorMessage);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param x position x
     * @param y position y
     */
    public void getCardAtPosition(final String currentCommand,
                                  final int x, final int y) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("x", x);
        objectNode.put("y", y);
        Card card = startGame.getBoard().getCard(x, y);
        if (card.getName().isEmpty()) {
            objectNode.put("output", "No card available at that position.");
        } else {
            ObjectNode objNode = objectMapper.createObjectNode();
            objNode.put("mana", card.getMana());
            objNode.put("attackDamage", card.getAttackDamage());
            objNode.put("health", card.getHealth());
            objNode.put("description", card.getDescription());
            ArrayNode colorsArray = objectMapper.createArrayNode();
            for (String color : card.getColors()) {
                colorsArray.add(color);
            }
            objNode.set("colors", colorsArray);
            objNode.put("name", card.getName());
            objectNode.put("output", objNode);
        }
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param cardAttacker card attacker
     * @param cardAttacked card attacked
     */
    private void cardUsesAbility(final String currentCommand, final Coordinates cardAttacker,
                                 final Coordinates cardAttacked) {
        if (startGame.getBoard().verifyIfFrozen(cardAttacker.getX(), cardAttacker.getY())) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacker card is frozen.");
        } else if (startGame.getBoard().verifyIfAttacked(cardAttacker.getX(),
                cardAttacker.getY())) {
            errorCard(currentCommand, cardAttacker, cardAttacked,
                    "Attacker card has already attacked this turn.");
        } else if (startGame.getBoard().getCard(cardAttacker.getX(),
                cardAttacker.getY()).getName().equals("Disciple")) {
            if (!startGame.getBoard().verifyIfAlly(startGame.getCurrentTurn(),
                    cardAttacked.getX())) {
                errorCard(currentCommand, cardAttacker, cardAttacked,
                        "Attacked card does not belong to the current player.");
            } else {
                Card card = startGame.getBoard().getCard(cardAttacker.getX(),
                        cardAttacker.getY());
                card.ability(startGame, cardAttacker, cardAttacked);
            }
        } else {
            if (startGame.getBoard().verifyIfAlly(startGame.getCurrentTurn(),
                    cardAttacked.getX())) {
                errorCard(currentCommand, cardAttacker, cardAttacked,
                        "Attacked card does not belong to the enemy.");
            } else if (startGame.getBoard().verifyTank(cardAttacked.getX(),
                    cardAttacked.getY(), startGame.getCurrentTurn())) {
                errorCard(currentCommand, cardAttacker, cardAttacked,
                        "Attacked card is not of type 'Tank'.");
            } else {
                Card card = startGame.getBoard().getCard(cardAttacker.getX(),
                        cardAttacker.getY());
                card.ability(startGame, cardAttacker, cardAttacked);
            }
        }
    }

    /**
     *
     * @param currentCommand current command
     * @param cardAttacker card attacker
     * @param errorMessage card attacked
     */
    private void attackHeroError(final String currentCommand, final Coordinates cardAttacker,
                                 final String errorMessage) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode2.put("x", cardAttacker.getX());
        objectNode2.put("y", cardAttacker.getY());
        objectNode.put("cardAttacker", objectNode2);
        objectNode.put("error", errorMessage);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param cardAttacker card attacker
     * @param game current game
     */
    private void useAttackHero(final String currentCommand, final Coordinates cardAttacker,
                               final PlayGame game) {
        if (startGame.getBoard().verifyIfFrozen(cardAttacker.getX(), cardAttacker.getY())) {
            attackHeroError(currentCommand, cardAttacker,
                    "Attacker card is frozen.");
        } else if (startGame.getBoard().verifyIfAttacked(cardAttacker.getX(),
                cardAttacker.getY())) {
            attackHeroError(currentCommand, cardAttacker,
                    "Attacker card has already attacked this turn.");
        } else if (startGame.getBoard().verifyHasTank(startGame.getCurrentTurn())) {
            attackHeroError(currentCommand, cardAttacker, "Attacked card is not of type 'Tank'.");
        } else {
            Card attacker = startGame.getBoard().getCard(cardAttacker.getX(), cardAttacker.getY());
            attacker.setHasAttacked(1);
            if (startGame.getCurrentTurn() == 2) {
                startGame.getPlayer1().getHero().setHealth(
                        startGame.getPlayer1().getHero().getHealth() - attacker.getAttackDamage());
                if (startGame.getPlayer1().getHero().getHealth() <= 0) {
                    startGame.getPlayer1().getHero().setHealth(0);
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("gameEnded", "Player two killed the enemy hero.");
                    output.add(objectNode);
                    game.setPlayerTwoWins(game.getPlayerTwoWins() + 1);
                }
            } else {
                startGame.getPlayer2().getHero().setHealth(
                        startGame.getPlayer2().getHero().getHealth() - attacker.getAttackDamage());
                if (startGame.getPlayer2().getHero().getHealth() <= 0) {
                    startGame.getPlayer2().getHero().setHealth(0);
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("gameEnded", "Player one killed the enemy hero.");
                    output.add(objectNode);
                    game.setPlayerOneWins(game.getPlayerOneWins() + 1);
                }
            }
        }
    }

    /**
     *
     * @param currentCommand current command
     * @param affectedRow affected row
     * @param output output
     * @param errorMessage error
     */
    private void heroError(final String currentCommand, final int affectedRow,
                           final ArrayNode output, final String errorMessage) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("affectedRow", affectedRow);
        objectNode.put("error", errorMessage);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param affectedRow affected row
     * @param currentTurn player turn
     */
    private void useHeroAbility(final String currentCommand, final int affectedRow,
                                final int currentTurn) {
        if (!verifyManaForHero(currentTurn)) {
            heroError(currentCommand, affectedRow, output,
                    "Not enough mana to use hero's ability.");
        } else if (verifyHeroAttacked(currentTurn)) {
            heroError(currentCommand, affectedRow, output,
                    "Hero has already attacked this turn.");
        } else if (isLordRoyceOrEmpressThorina(currentTurn)) {
            if (currentTurn == 1 && affectedRow > 1 || currentTurn == 2 && affectedRow < 2) {
                heroError(currentCommand, affectedRow, output,
                        "Selected row does not belong to the enemy.");
            } else {
                heroAbility(affectedRow, currentTurn);
            }
        } else {
            if (currentTurn == 1 && affectedRow < 2 || currentTurn == 2 && affectedRow > 1) {
                heroError(currentCommand, affectedRow, output,
                        "Selected row does not belong to the current player.");
            } else {
                heroAbility(affectedRow, currentTurn);
            }
        }
    }

    /**
     *
     * @param affectedRow affected row
     * @param currentTurn player turn
     */
    private void heroAbility(final int affectedRow, final int currentTurn) {
        if (currentTurn == 1) {
            startGame.getPlayer1().getHero().ability(affectedRow, startGame.getBoard());
            startGame.getPlayer1().getHero().setHasAttacked(1);
            int heroMana = startGame.getPlayer1().getHero().getMana();
            int currentMana = startGame.getPlayer1().getCurrentMana();
            startGame.getPlayer1().setCurrentMana(currentMana - heroMana);
        } else {
            startGame.getPlayer2().getHero().ability(affectedRow, startGame.getBoard());
            startGame.getPlayer2().getHero().setHasAttacked(1);
            int heroMana = startGame.getPlayer2().getHero().getMana();
            int currentMana = startGame.getPlayer2().getCurrentMana();
            startGame.getPlayer2().setCurrentMana(currentMana - heroMana);
        }
    }

    /**
     *
     * @param currentTurn current turn
     * @return mana
     */
    private boolean verifyManaForHero(final int currentTurn) {
        if (currentTurn == 1) {
            return startGame.getPlayer1().getCurrentMana()
                    >= startGame.getPlayer1().getHero().getMana();
        } else {
            return startGame.getPlayer2().getCurrentMana()
                    >= startGame.getPlayer2().getHero().getMana();
        }
    }

    /**
     *
     * @param currentTurn current turn
     * @return if hero attacked
     */
    private boolean verifyHeroAttacked(final int currentTurn) {
        if (currentTurn == 1) {
            return startGame.getPlayer1().getHero().getHasAttacked() == 1;
        } else {
            return startGame.getPlayer2().getHero().getHasAttacked() == 1;
        }
    }

    /**
     *
     * @param currentTurn current turn
     * @return 1 if hero is Lord Royce or Empress Thorina
     */
    private boolean isLordRoyceOrEmpressThorina(final int currentTurn) {
        if (currentTurn == 1) {
            return startGame.getPlayer1().getHero().getName().equals("Lord Royce")
                    || startGame.getPlayer1().getHero().getName().equals("Empress Thorina");
        } else {
            return startGame.getPlayer2().getHero().getName().equals("Lord Royce")
                    || startGame.getPlayer2().getHero().getName().equals("Empress Thorina");
        }
    }

    /**
     *
     * @param currentCommand current command
     * @param gameNumber game number
     */
    private void getTotalGamesPlayed(final String currentCommand, final int gameNumber) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("output", gameNumber);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param playerOneWins number of wins for player one
     */
    private void getPlayerOneWinsCommand(final String currentCommand, final int playerOneWins) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("output", playerOneWins);
        output.add(objectNode);
    }

    /**
     *
     * @param currentCommand current command
     * @param playerTwoWins number of wins for player two
     */
    private void getPlayerTwoWinsCommand(final String currentCommand, final int playerTwoWins) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", currentCommand);
        objectNode.put("output", playerTwoWins);
        output.add(objectNode);
    }
}
