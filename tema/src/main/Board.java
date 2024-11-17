package main;

import java.util.ArrayList;

public class Board {
    Card[][] board;

    public Card[][] getBoard() {
        return board;
    }

    public void setBoard(Card[][] board) {
        this.board = board;
    }

    public Board() {
        board = new Minion[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Minion();
            }
        }
    }

    public Card getCard(int i, int j) {
        return board[i][j];
    }

    public void placeCard(int round, Card card) {
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
        }
        else {
            if (card.getRow() == 0) {
                for (int j = 0; j < board[3].length; j++) {
                    if (board[3][j].getDescription().isEmpty()) {
                        board[3][j] = card;
                        break;
                    }
                }
            }
            else {
                for (int j = 0; j < board[2].length; j++) {
                    if (board[2][j].getDescription().isEmpty()) {
                        board[2][j] = card;
                        break;
                    }
                }
            }
        }
    }

    public ArrayList<Card> getCardsOnTable() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++) {
                if (!board[i][j].getDescription().isEmpty())
                    cards.add(getCard(i, j));
            }
        return cards;
    }

    public boolean isFull(int index, int row) {
        if (index == 2) {
            for (int j = 0; j < 5; j++)
                if (board[row][j].getName().isEmpty())
                    return false;
        }
        else {
            if (row == 0)
                row = 3;
            else row = 2;
            for (int j = 0; j < 5; j++)
                if(board[row][j].getName().isEmpty())
                    return false;
        }
        return true;
    }

    public void roundReset() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].setHasAttacked(0);
                if (board[i][j].getRoundsFrozen() > 1) {
                    board[i][j].setIsFrozen(0);
                    board[i][j].setRoundsFrozen(0);
                }
            }
        }
    }

    public void incrementFrozen() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getIsFrozen() == 1) {
                    board[i][j].setRoundsFrozen(board[i][j].getRoundsFrozen() + 1);
                }
            }
        }
    }

    public boolean verifyIfAttacked(int x, int y) {
        if (board[x][y].getHasAttacked() == 0)
            return false;
        return true;
    }

    public boolean verifyIfFrozen(int x, int y) {
        if (board[x][y].getIsFrozen() == 0)
            return false;
        return true;
    }

    public boolean verifyTank(int x, int y, int turn) {
        if (turn == 1) {
            int ok = 1;
            for (int j = 0; j < 5; j++)
                if (board[1][j].getIsTank() == 1)
                    ok = 0;
            if (ok == 1)
                return true;
            for (int j = 0; j < 5; j++)
                if (board[1][j].getIsTank() == 1 && x == 1 && y == j)
                    return true;
        } else {
            int ok = 1;
            for (int j = 0; j < 5; j++)
                if (board[2][j].getIsTank() == 1)
                    ok = 0;
            if (ok == 1)
                return true;
            for (int j = 0; j < 5; j++)
                if (board[2][j].getIsTank() == 1 && x == 2 && y==j)
                    return true;
        }
        return false;
    }

    public void useAttack(int attackerX, int attackerY, int attackedX, int attackedY) {
        Card attacker = board[attackerX][attackerY];
        Card attacked = board[attackedX][attackedY];
        attacker.setHasAttacked(1);
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        if (attacked.getHealth() <= 0) {
            int empty = 0;
            for (int j = attackedY + 1; j < 5; j++) {
                board[attackedX][j - 1] = board[attackedX][j];
                if (board[attackedX][j].getDescription().isEmpty()) {
                    empty++;
                    board[attackedX][j - 1] = new Minion();
                    break;
                }
            }
            if (empty == 0) {
                board[attackedX][4] = new Minion();
            }
        }
    }

    public boolean verifyIfAlly(int turn, int attackedX) {
        if (turn == 1) {
            if (attackedX == 2 || attackedX == 3)
                return true;
            return false;
        } else {
            if (attackedX == 1 || attackedX == 0)
                return true;
            return false;
        }
    }

    public boolean verifyHasTank(int turn) {
        if (turn == 1) {
            for (int j = 0; j < 5; j++) {
                if (board[1][j].getIsTank() == 1)
                    return true;
            }
            return false;
        } else {
            for (int j = 0; j < 5; j++) {
                if (board[2][j].getIsTank() == 1)
                    return true;
            }
            return false;
        }
    }

    public void eliminateCard(int x, int y) {
        int empty = 0;
        for (int j = y + 1; j < 5; j++) {
            board[x][j - 1] = board[x][j];
            if (board[x][j].getDescription().isEmpty()) {
                empty++;
                board[x][j - 1] = new Minion();
                break;
            }
        }
        if (empty == 0) {
            board[x][4] = new Minion();
        }
    }
}
