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
        playingCards.add(new Card(101, "2", AppConstants.HEARTS));
        playingCards.add(new Card(102, "3", AppConstants.HEARTS));
        playingCards.add(new Card(103, "4", AppConstants.HEARTS));
        playingCards.add(new Card(104, "5", AppConstants.HEARTS));
        playingCards.add(new Card(105, "6", AppConstants.HEARTS));
        playingCards.add(new Card(106, "7", AppConstants.HEARTS));
        playingCards.add(new Card(107, "8", AppConstants.HEARTS));
        playingCards.add(new Card(108, "9", AppConstants.HEARTS));
        playingCards.add(new Card(109, "10", AppConstants.HEARTS));
        playingCards.add(new Card(111, "J", AppConstants.HEARTS));
        playingCards.add(new Card(112, "Q", AppConstants.HEARTS));
        playingCards.add(new Card(113, "K", AppConstants.HEARTS));
        playingCards.add(new Card(114, "A", AppConstants.HEARTS));

        //Create Spades
        playingCards.add(new Card(115, "2", AppConstants.SPADES));
        playingCards.add(new Card(116, "3", AppConstants.SPADES));
        playingCards.add(new Card(117, "4", AppConstants.SPADES));
        playingCards.add(new Card(118, "5", AppConstants.SPADES));
        playingCards.add(new Card(119, "6", AppConstants.SPADES));
        playingCards.add(new Card(120, "7", AppConstants.SPADES));
        playingCards.add(new Card(121, "8", AppConstants.SPADES));
        playingCards.add(new Card(122, "9", AppConstants.SPADES));
        playingCards.add(new Card(123, "10", AppConstants.SPADES));
        playingCards.add(new Card(124, "J", AppConstants.SPADES));
        playingCards.add(new Card(125, "Q", AppConstants.SPADES));
        playingCards.add(new Card(126, "K", AppConstants.SPADES));
        playingCards.add(new Card(127, "A", AppConstants.SPADES));

        //Create Clubs
        playingCards.add(new Card(128, "2", AppConstants.CLUBS));
        playingCards.add(new Card(129, "3", AppConstants.CLUBS));
        playingCards.add(new Card(130, "4", AppConstants.CLUBS));
        playingCards.add(new Card(131, "5", AppConstants.CLUBS));
        playingCards.add(new Card(132, "6", AppConstants.CLUBS));
        playingCards.add(new Card(133, "7", AppConstants.CLUBS));
        playingCards.add(new Card(134, "8", AppConstants.CLUBS));
        playingCards.add(new Card(135, "9", AppConstants.CLUBS));
        playingCards.add(new Card(136, "10", AppConstants.CLUBS));
        playingCards.add(new Card(137, "J", AppConstants.CLUBS));
        playingCards.add(new Card(138, "Q", AppConstants.CLUBS));
        playingCards.add(new Card(139, "K", AppConstants.CLUBS));
        playingCards.add(new Card(140, "A", AppConstants.CLUBS));

        //Create Diamonds
        playingCards.add(new Card(141, "2", AppConstants.DIAMONDS));
        playingCards.add(new Card(142, "3", AppConstants.DIAMONDS));
        playingCards.add(new Card(143, "4", AppConstants.DIAMONDS));
        playingCards.add(new Card(144, "5", AppConstants.DIAMONDS));
        playingCards.add(new Card(145, "6", AppConstants.DIAMONDS));
        playingCards.add(new Card(146, "7", AppConstants.DIAMONDS));
        playingCards.add(new Card(147, "8", AppConstants.DIAMONDS));
        playingCards.add(new Card(148, "9", AppConstants.DIAMONDS));
        playingCards.add(new Card(149, "10", AppConstants.DIAMONDS));
        playingCards.add(new Card(150, "J", AppConstants.DIAMONDS));
        playingCards.add(new Card(151, "Q", AppConstants.DIAMONDS));
        playingCards.add(new Card(152, "K", AppConstants.DIAMONDS));
        playingCards.add(new Card(153, "A", AppConstants.DIAMONDS));
    }


}
