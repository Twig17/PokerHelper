package com.ferraro.poker.helper.pokerhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 7/11/2016.
 */
public class Engine {
    private static Engine engine;
    private List<Card> playingCards;
    private List<PlayerCard> playerCards;

    private Engine() {
        playingCards = new ArrayList<Card>();
        playerCards = new ArrayList<PlayerCard>();
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

    public List<PlayerCard> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<PlayerCard> playerCards) {
        this.playerCards = playerCards;
    }

    private void createCards() {
        playerCards.add(new PlayerCard(R.id.PlayerCard1, 1, "Card 1"));
        playerCards.add(new PlayerCard(R.id.PlayerCard2, 2, "Card 2"));
        playerCards.add(new PlayerCard(R.id.PlayerCard3, 3, "Card 3"));
        playerCards.add(new PlayerCard(R.id.PlayerCard4, 4, "Card 4"));
        playerCards.add(new PlayerCard(R.id.PlayerCard5, 5, "Card 5"));

        //Create Hearts
        playingCards.add(new Card(101, "2", "Heart"));
        playingCards.add(new Card(102, "3", "Heart"));
        playingCards.add(new Card(103, "4", "Heart"));
        playingCards.add(new Card(104, "5", "Heart"));
        playingCards.add(new Card(105, "6", "Heart"));
        playingCards.add(new Card(106, "7", "Heart"));
        playingCards.add(new Card(107, "8", "Heart"));
        playingCards.add(new Card(108, "9", "Heart"));
        playingCards.add(new Card(109, "10", "Heart"));
        playingCards.add(new Card(111, "J", "Heart"));
        playingCards.add(new Card(112, "Q", "Heart"));
        playingCards.add(new Card(113, "K", "Heart"));
        playingCards.add(new Card(114, "A", "Heart"));

        //Create Spades
        //Create Clubs
        //Create Diamonds
    }


}
