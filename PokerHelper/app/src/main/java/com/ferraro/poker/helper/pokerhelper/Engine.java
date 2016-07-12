package com.ferraro.poker.helper.pokerhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 7/11/2016.
 */
public class Engine {
    private static Engine engine;
    private List<Card> playingCards;

    private Engine() {
        playingCards = new ArrayList<Card>();
        createCards();
    }

    public static Engine getEngine() {
        if (engine == null) {
            return new Engine();
        }
        return engine;
    }

    public List<Card> getPlayingCards() {
        return playingCards;
    }

    public void setPlayingCards(List<Card> playingCards) {
        this.playingCards = playingCards;
    }

    private void createCards() {
        //Create Hearts
        playingCards.add(new Card("2", "Heart"));
        playingCards.add(new Card("3", "Heart"));
        playingCards.add(new Card("4", "Heart"));
        playingCards.add(new Card("5", "Heart"));
        playingCards.add(new Card("6", "Heart"));
        playingCards.add(new Card("7", "Heart"));
        playingCards.add(new Card("8", "Heart"));
        playingCards.add(new Card("9", "Heart"));
        playingCards.add(new Card("10", "Heart"));
        playingCards.add(new Card("J", "Heart"));
        playingCards.add(new Card("Q", "Heart"));
        playingCards.add(new Card("K", "Heart"));
        playingCards.add(new Card("A", "Heart"));

        //Create Spades
        //Create Clubs
        //Create Diamonds
    }


}
