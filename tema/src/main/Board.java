package main;

import java.util.ArrayList;

public class Board {
    Card[][] board;

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
                if (board[row][j].getName().equals(""))
                    return false;
        }
        else {
            for (int j = 0; j < 5; j++)
                if(board[row + 2][j].getName().equals(""))
                    return false;
        }
        return true;
    }

    public void roundReset() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].setHasAttacked(0);
                board[i][j].setIsFrozen(0);
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
                if (board[1][j].getIsTank() == 1 && x == 1)
                    return true;
        } else {
            int ok = 1;
            for (int j = 0; j < 5; j++)
                if (board[2][j].getIsTank() == 1)
                    ok = 0;
            if (ok == 1)
                return true;
            for (int j = 0; j < 5; j++)
                if (board[2][j].getIsTank() == 1 && x == 2)
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
            for (int j = attackedY + 1; j < 5; j++)
                board[attackedX][j - 1] = board[attackedX][j];
        }
    }
}
