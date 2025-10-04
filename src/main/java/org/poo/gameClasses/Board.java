package org.poo.gameClasses;

import org.poo.cards.Card;
import org.poo.cards.Minion;

import java.util.ArrayList;

public final class Board {
    private final Card[][] board;
    private static final int NR_ROWS = 4;
    private static final int NR_COLUMNS = 5;

    public Card[][] getBoard() {
        return board;
    }
    public Board() {
        board = new Minion[NR_ROWS][NR_COLUMNS];
        for (int i = 0; i < NR_ROWS; i++) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                board[i][j] = new Minion();
            }
        }
    }

    /**
     *
     * @param i index
     * @param j index
     * @return card at index i,j
     */
    public Card getCard(final int i, final int j) {
        return board[i][j];
    }

    /**
     *
     * @param round round number
     * @param card card to be placed
     */
    public void placeCard(final int round, final Card card) {
        if (round == 2) {
            if (card.getRow() == 0) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[0][j].getDescription().isEmpty()) {
                        board[0][j] = card;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < board[1].length; j++) {
                    if (board[1][j].getDescription().isEmpty()) {
                        board[1][j] = card;
                        break;
                    }
                }
            }
        } else {
            if (card.getRow() == 0) {
                for (int j = 0; j < board[NR_ROWS - 1].length; j++) {
                    if (board[NR_ROWS - 1][j].getDescription().isEmpty()) {
                        board[NR_ROWS - 1][j] = card;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < board[2].length; j++) {
                    if (board[2][j].getDescription().isEmpty()) {
                        board[2][j] = card;
                        break;
                    }
                }
            }
        }
    }

    /**
     *
     * @return cards on table
     */
    public ArrayList<Card> getCardsOnTable() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < NR_ROWS; i++) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (!board[i][j].getDescription().isEmpty()) {
                    cards.add(getCard(i, j));
                }
            }
        }
        return cards;
    }

    /**
     *
     * @param index index
     * @param row row to be verified
     * @return true if is full
     */
    public boolean isFull(final int index, final int row) {
        if (index == 2) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[row][j].getName().isEmpty()) {
                    return true;
                }
            }
        } else {
            int rowForIdx1;
            if (row == 0) {
                rowForIdx1 = NR_ROWS - 1;
            } else {
                rowForIdx1 = 2;
            }
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[rowForIdx1][j].getName().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * resets the board
     */
    public void roundReset() {
        for (int i = 0; i < NR_ROWS; i++) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                board[i][j].setHasAttacked(0);
                if (board[i][j].getRoundsFrozen() > 1) {
                    board[i][j].setIsFrozen(0);
                    board[i][j].setRoundsFrozen(0);
                }
            }
        }
    }

    /**
     * increment frozen rounds
     */
    public void incrementFrozen() {
        for (int i = 0; i < NR_ROWS; i++) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[i][j].getIsFrozen() == 1) {
                    board[i][j].setRoundsFrozen(board[i][j].getRoundsFrozen() + 1);
                }
            }
        }
    }

    /**
     *
     * @param x index x
     * @param y index y
     * @return true if attacked
     */
    public boolean verifyIfAttacked(final int x, final int y) {
        return board[x][y].getHasAttacked() != 0;
    }

    /**
     *
     * @param x index x
     * @param y index y
     * @return true if is frozen
     */
    public boolean verifyIfFrozen(final int x, final int y) {
        return board[x][y].getIsFrozen() != 0;
    }

    /**
     *
     * @param x index x
     * @param y index y
     * @param turn player turn
     * @return true if a tank exists on the same row and is not attacked
     */
    public boolean verifyTank(final int x, final int y, final int turn) {
        int ok = 1;
        if (turn == 1) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[1][j].getIsTank() == 1) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                return false;
            }
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[1][j].getIsTank() == 1 && x == 1 && y == j) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[2][j].getIsTank() == 1) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                return false;
            }
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[2][j].getIsTank() == 1 && x == 2 && y == j) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param attackerX index x of the attacker
     * @param attackerY index y of the attacker
     * @param attackedX index x of the attacked
     * @param attackedY index y of the attacked
     */
    public void useAttack(final int attackerX, final int attackerY,
                          final int attackedX, final int attackedY) {
        Card attacker = board[attackerX][attackerY];
        Card attacked = board[attackedX][attackedY];
        attacker.setHasAttacked(1);
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        if (attacked.getHealth() <= 0) {
            eliminateCard(attackedX, attackedY);
        }
    }

    /**
     *
     * @param attackedX index x of the attacked
     * @param attackedY index y of the attacked
     */
    public void eliminateCard(final int attackedX, final int attackedY) {
        int empty = 0;
        for (int j = attackedY + 1; j < NR_COLUMNS; j++) {
            board[attackedX][j - 1] = board[attackedX][j];
            if (board[attackedX][j].getDescription().isEmpty()) {
                empty++;
                board[attackedX][j - 1] = new Minion();
                break;
            }
        }
        if (empty == 0) {
            board[attackedX][NR_ROWS] = new Minion();
        }
    }

    /**
     *
     * @param turn current turn
     * @param attackedX index x of the attacked
     * @return if the attacked is an ally
     */
    public boolean verifyIfAlly(final int turn, final int attackedX) {
        if (turn == 1) {
            return attackedX == 2 || attackedX == NR_COLUMNS - 2;
        } else {
            return attackedX == 1 || attackedX == 0;
        }
    }

    /**
     *
     * @param turn current turn
     * @return if it exists a tank on the enemy lane
     */
    public boolean verifyHasTank(final int turn) {
        if (turn == 1) {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[1][j].getIsTank() == 1) {
                    return true;
                }
            }
        } else {
            for (int j = 0; j < NR_COLUMNS; j++) {
                if (board[2][j].getIsTank() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
