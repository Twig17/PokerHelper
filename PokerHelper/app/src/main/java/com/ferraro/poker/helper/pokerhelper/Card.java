package com.ferraro.poker.helper.pokerhelper;

/**
 * Created by Nick on 7/11/2016.
 */
public class Card {
    private int id;
    private String value;
    private String suite;
    private String color;

    public Card(int id) {
        this.id = id;
    }

    public Card(int id, String value, String suite) {
        this.id = id;
        this.value = value;
        this.suite = suite;
        if("Hearts".equals(suite) || "Diamonds".equals(suite)){
            color = "Red";
        } else if("Spades".equals(suite) || "Clubs".equals(suite)){
            color = "Black";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return getId() == card.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
