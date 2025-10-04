package org.poo.cards;

import org.poo.fileio.Coordinates;
import org.poo.gameClasses.StartGame;

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
    private int roundsFrozen = 0;

    public Card() {
        mana = 0;
        health = 0;
        attackDamage = 0;
        description = "";
        colors = new ArrayList<>();
        name = "";
    }

    public Card(final int attackDamage, final ArrayList<String> colors, final String description,
                final int health, final int mana, final String name) {
        this.attackDamage = attackDamage;
        this.colors = colors;
        this.description = description;
        this.health = health;
        this.mana = mana;
        this.name = name;
    }

    /**
     *
     * @return rounds frozen
     */
    public int getRoundsFrozen() {
        return roundsFrozen;
    }

    /**
     *
     * @param roundsFrozen setter
     */
    public void setRoundsFrozen(final int roundsFrozen) {
        this.roundsFrozen = roundsFrozen;
    }


    /**
     *
     * @return attack damage
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     *
     * @param attackDamage setter
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     *
     * @return colors getter
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     *
     * @param colors setter
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     *
     * @return description getter
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description setter
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @return health getter
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health setter
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     *
     * @return mana getter
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     * @param mana setter
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name setter
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @param row setter
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     *
     * @return whoPlacedMe
     */
    public int getWhoPlacedMe() {
        return whoPlacedMe;
    }

    /**
     *
     * @param whoPlacedMe setter
     */
    public void setWhoPlacedMe(final int whoPlacedMe) {
        this.whoPlacedMe = whoPlacedMe;
    }

    /**
     *
     * @return hasAttacked getter
     */
    public int getHasAttacked() {
        return hasAttacked;
    }

    /**
     *
     * @param hasAttacked setter
     */
    public void setHasAttacked(final int hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     *
     * @return isFrozen getter
     */
    public int getIsFrozen() {
        return isFrozen;
    }

    /**
     *
     * @param isFrozen setter
     */
    public void setIsFrozen(final int isFrozen) {
        this.isFrozen = isFrozen;
    }

    /**
     *
     * @return isTank
     */
    public int getIsTank() {
        return isTank;
    }

    /**
     *
     * @param isTank setter
     */
    public void setIsTank(final int isTank) {
        this.isTank = isTank;
    }

    /**
     *
     * @param startGame startGame
     * @param cardAttacker cardAttacker
     * @param cardAttacked cardAttacked
     */
    public void ability(final StartGame startGame, final Coordinates cardAttacker,
                           final Coordinates cardAttacked) {
    }

}
