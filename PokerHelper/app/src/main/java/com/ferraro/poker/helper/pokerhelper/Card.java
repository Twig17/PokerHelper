package com.ferraro.poker.helper.pokerhelper;

/**
 * Created by Nick on 7/11/2016.
 */
public class Card {
    private String value;
    private String suite;
    private String color;
    private boolean selected;

    public Card(String value, String suite) {
        this.value = value;
        this.suite = suite;
        selected = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

        if (getValue() != null ? !getValue().equals(card.getValue()) : card.getValue() != null)
            return false;
        return !(getSuite() != null ? !getSuite().equals(card.getSuite()) : card.getSuite() != null);

    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + (getSuite() != null ? getSuite().hashCode() : 0);
        return result;
    }


}
