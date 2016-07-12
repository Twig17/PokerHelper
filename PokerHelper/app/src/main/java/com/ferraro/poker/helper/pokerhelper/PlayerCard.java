package com.ferraro.poker.helper.pokerhelper;

/**
 * Created by Nick on 7/11/2016.
 */
public class PlayerCard {
    private String id;
    private Card card;

    public PlayerCard(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
