package com.ferraro.poker.helper.pokerhelper;

/**
 * Created by Nick on 7/11/2016.
 */
public class PlayerCard {
    private int id;
    private int cardNumber;
    private Card card;
    private boolean selected;
    private boolean shouldKeep;
    private String defaultText;

    public PlayerCard(int id, int cardNumber, String defaultText) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.defaultText = defaultText;
        selected = false;
        shouldKeep = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isShouldKeep() {
        return shouldKeep;
    }

    public void setShouldKeep(boolean shouldKeep) {
        this.shouldKeep = shouldKeep;
    }
}
