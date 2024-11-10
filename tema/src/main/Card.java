package main;

import java.util.ArrayList;

public class Card {
    private int mana;
    private int health;
    private int attackDamage;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private int row;
    private int whoPlacedMe = 0;
    private int hasAttacked = 0;
    private int isFrozen = 0;
    private int isTank = 0;

    public Card() {
        mana = 0;
        health = 0;
        attackDamage = 0;
        description = "";
        colors = new ArrayList<>();
        name = "";
    }

    public Card(int attackDamage, ArrayList<String> colors, String description, int health, int mana, String name) {
        this.attackDamage = attackDamage;
        this.colors = colors;
        this.description = description;
        this.health = health;
        this.mana = mana;
        this.name = name;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getWhoPlacedMe() {
        return whoPlacedMe;
    }

    public void setWhoPlacedMe(int whoPlacedMe) {
        this.whoPlacedMe = whoPlacedMe;
    }

    public int getHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(int hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

    public int getIsTank() {
        return isTank;
    }

    public void setIsTank(int isTank) {
        this.isTank = isTank;
    }

    @Override
    public String toString() {
        return "{" +
                "mana = " + mana +
                "attackDamage = " + attackDamage +
                "health = " + health +
                "description = '" + description + '\'' +
                "colors : " + colors + '\'' +
                "name:" + name + '\'';
        }
}
