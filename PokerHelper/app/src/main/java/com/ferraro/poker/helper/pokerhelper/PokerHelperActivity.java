package com.ferraro.poker.helper.pokerhelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class PokerHelperActivity extends ActionBarActivity {
    Engine engine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poker_helper);
        engine = Engine.getEngine();
        addCardsToDisplay(engine.getPlayingCards());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poker_helper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newDeal(View view) {
        List<PlayerCard> playerCards = engine.getPlayerCards();
        for(PlayerCard playerCard: playerCards){
            playerCard.setSelected(false);
            playerCard.setCard(null);
            TextView playerCardView = (TextView) findViewById(playerCard.getId());
            playerCardView.setText(playerCard.getDefaultText());
            playerCardView.setBackgroundResource(0);

        }
    }


    /**
     * One of the players Card slots has been selected
     * set all others back without a border
     * @param view
     */
    public void selectYourCardSpot(View view){
        List<PlayerCard> myCardIds = engine.getPlayerCards();
        for(PlayerCard card: myCardIds) {
            TextView myCard = (TextView) findViewById(card.getId());
            if(card.getId() == view.getId()) {
                if(card.isSelected()) {
                    myCard.setBackgroundResource(0);
                    card.setSelected(false);
                } else {
                    myCard.setBackgroundResource(R.drawable.button_border);
                    card.setSelected(true);
                }
            }
            else {
                myCard.setBackgroundResource(0);
                card.setSelected(false);
            }
        }
    }

    /**
     * Have cards be displayed in GUI
     */
    private void addCardsToDisplay(List<Card> playingCards){
        LinearLayout heartsCardList = (LinearLayout) findViewById(R.id.heartsCardView);
        LinearLayout diamondsCardList = (LinearLayout) findViewById(R.id.diamondsCardView);
        LinearLayout clubsCardList = (LinearLayout) findViewById(R.id.clubsCardView);
        LinearLayout spadesCardList = (LinearLayout) findViewById(R.id.spadesCardView);

        for(Card card: playingCards) {
            if(AppConstants.HEARTS.equals(card.getSuite())) {
                addCardToDisplayView(card, new TextView(this), heartsCardList);
            }
            if(AppConstants.CLUBS.equals(card.getSuite())) {
                addCardToDisplayView(card, new TextView(this), clubsCardList);
            }
            if(AppConstants.DIAMONDS.equals(card.getSuite())) {
                addCardToDisplayView(card, new TextView(this), diamondsCardList);
            }
            if(AppConstants.SPADES.equals(card.getSuite())) {
                addCardToDisplayView(card, new TextView(this), spadesCardList);
            }
        }
    }

    private void addCardToDisplayView(Card card, TextView textView, LinearLayout layout){
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                120,
                ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setText(card.getValue());
        textView.setId(card.getId());
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                selectYourCard(v);
            }
        });
        textView.setBackgroundResource(R.drawable.playing_card_border);
        layout.addView(textView);
    }

    /**
     * Pick one of the 52 cards to be in your hand
     */
    public void selectYourCard(View view) {
        boolean cardFound = false;
        Card selectedCard = new Card(view.getId());
        PlayerCard playerCardSlot = null;
        List<PlayerCard> playerCardsList = engine.getPlayerCards();
        selectedCard =  engine.getPlayingCards().get(engine.getPlayingCards().indexOf(selectedCard));
        for(PlayerCard playerCard: playerCardsList){
            if(playerCard.isSelected()) {
                playerCardSlot = playerCard;
                TextView myCard = (TextView) findViewById(playerCard.getId());
                playerCard.setCard(selectedCard);
                myCard.setText(selectedCard.getValue());
                playerCard.setSelected(false);
                myCard.setBackgroundResource(0);
                cardFound = true;
                checkIfHandIsFull();
            }
        }
        if(cardFound) {
            if(playerCardSlot.getCardNumber() < 5) {
                for(PlayerCard playerCard: playerCardsList){
                    if(playerCard.getCardNumber() == playerCardSlot.getCardNumber() +1 && playerCard.getCard() == null) {
                        TextView myCard = (TextView) findViewById(playerCard.getId());
                        myCard.setBackgroundResource(R.drawable.button_border);
                        playerCard.setSelected(true);
                    }
                }
            }
        }
    }

    public void checkIfHandIsFull(View view) {
        checkIfHandIsFull();
    }

    private void checkIfHandIsFull() {
        List<PlayerCard> playerCardsList = engine.getPlayerCards();
        for(PlayerCard playerCard: playerCardsList){
            if(playerCard.getCard() == null ) {
                return;
            }
        }
        calculateWhatToDo();

    }

    private void calculateWhatToDo() {
        TextView clearButton = (TextView) findViewById(R.id.PlayerCard4);
        clearButton.setBackgroundResource(R.drawable.discard_card_border);
    }



}
