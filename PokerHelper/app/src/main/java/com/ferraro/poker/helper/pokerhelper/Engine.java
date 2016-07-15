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
        playingCards.add(new Card(101, "2",  2,  AppConstants.HEARTS));
        playingCards.add(new Card(102, "3",  3,  AppConstants.HEARTS));
        playingCards.add(new Card(103, "4",  4,  AppConstants.HEARTS));
        playingCards.add(new Card(104, "5",  5,  AppConstants.HEARTS));
        playingCards.add(new Card(105, "6",  6,  AppConstants.HEARTS));
        playingCards.add(new Card(106, "7",  7,  AppConstants.HEARTS));
        playingCards.add(new Card(107, "8",  8,  AppConstants.HEARTS));
        playingCards.add(new Card(108, "9",  9,  AppConstants.HEARTS));
        playingCards.add(new Card(109, "10", 10, AppConstants.HEARTS));
        playingCards.add(new Card(111, "J",  11, AppConstants.HEARTS));
        playingCards.add(new Card(112, "Q",  12, AppConstants.HEARTS));
        playingCards.add(new Card(113, "K",  13, AppConstants.HEARTS));
        playingCards.add(new Card(114, "A",  14, AppConstants.HEARTS));

        //Create Spades
        playingCards.add(new Card(115, "2",  2,  AppConstants.SPADES));
        playingCards.add(new Card(116, "3",  3,  AppConstants.SPADES));
        playingCards.add(new Card(117, "4",  4,  AppConstants.SPADES));
        playingCards.add(new Card(118, "5",  5,  AppConstants.SPADES));
        playingCards.add(new Card(119, "6",  6,  AppConstants.SPADES));
        playingCards.add(new Card(120, "7",  7,  AppConstants.SPADES));
        playingCards.add(new Card(121, "8",  8,  AppConstants.SPADES));
        playingCards.add(new Card(122, "9",  9,  AppConstants.SPADES));
        playingCards.add(new Card(123, "10", 10, AppConstants.SPADES));
        playingCards.add(new Card(124, "J",  11, AppConstants.SPADES));
        playingCards.add(new Card(125, "Q",  12, AppConstants.SPADES));
        playingCards.add(new Card(126, "K",  13, AppConstants.SPADES));
        playingCards.add(new Card(127, "A",  14, AppConstants.SPADES));

        //Create Clubs
        playingCards.add(new Card(128, "2",  2,  AppConstants.CLUBS));
        playingCards.add(new Card(129, "3",  3,  AppConstants.CLUBS));
        playingCards.add(new Card(130, "4",  4,  AppConstants.CLUBS));
        playingCards.add(new Card(131, "5",  5,  AppConstants.CLUBS));
        playingCards.add(new Card(132, "6",  6,  AppConstants.CLUBS));
        playingCards.add(new Card(133, "7",  7,  AppConstants.CLUBS));
        playingCards.add(new Card(134, "8",  8,  AppConstants.CLUBS));
        playingCards.add(new Card(135, "9",  9,  AppConstants.CLUBS));
        playingCards.add(new Card(136, "10", 10, AppConstants.CLUBS));
        playingCards.add(new Card(137, "J",  11, AppConstants.CLUBS));
        playingCards.add(new Card(138, "Q",  12, AppConstants.CLUBS));
        playingCards.add(new Card(139, "K",  13, AppConstants.CLUBS));
        playingCards.add(new Card(140, "A",  14, AppConstants.CLUBS));

        //Create Diamonds
        playingCards.add(new Card(141, "2",  2,  AppConstants.DIAMONDS));
        playingCards.add(new Card(142, "3",  3,  AppConstants.DIAMONDS));
        playingCards.add(new Card(143, "4",  4,  AppConstants.DIAMONDS));
        playingCards.add(new Card(144, "5",  5,  AppConstants.DIAMONDS));
        playingCards.add(new Card(145, "6",  6,  AppConstants.DIAMONDS));
        playingCards.add(new Card(146, "7",  7,  AppConstants.DIAMONDS));
        playingCards.add(new Card(147, "8",  8,  AppConstants.DIAMONDS));
        playingCards.add(new Card(148, "9",  9,  AppConstants.DIAMONDS));
        playingCards.add(new Card(149, "10", 10, AppConstants.DIAMONDS));
        playingCards.add(new Card(150, "J",  11, AppConstants.DIAMONDS));
        playingCards.add(new Card(151, "Q",  12, AppConstants.DIAMONDS));
        playingCards.add(new Card(152, "K",  13, AppConstants.DIAMONDS));
        playingCards.add(new Card(153, "A",  14, AppConstants.DIAMONDS));
    }


}
