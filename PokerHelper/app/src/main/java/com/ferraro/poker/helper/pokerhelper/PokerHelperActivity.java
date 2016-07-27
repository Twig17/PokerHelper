package com.ferraro.poker.helper.pokerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class PokerHelperActivity extends ActionBarActivity {
    Engine engine;
    boolean buttonLocked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setTitle("PokerHelper");
        setContentView(R.layout.activity_poker_helper);
        engine = Engine.getEngine();
        addCardsToDisplay(engine.getPlayingCards());
        selectFirstCardByDefault();
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
        if (id == R.id.practice_settings) {
            startPractice();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * have the first card default to selected so can just start picking cards for hand
     */
    public void selectFirstCardByDefault() {
        PlayerCard firstCard = engine.getPlayerCards().get(0);
        TextView playerCardView = (TextView) findViewById(firstCard.getId());
        firstCard.setSelected(true);
        decideBackGround(firstCard, AppConstants.SELECTED);
    }

    /**
     * clear all selected cards so can start over
     */
    public void newDeal(View view) {
        List<PlayerCard> playerCards = engine.getPlayerCards();
        enableFixCardsButton(false);
        for(PlayerCard playerCard: playerCards){
            playerCard.setSelected(false);
            playerCard.setCard(null);
            TextView playerCardView = (TextView) findViewById(playerCard.getId());
            playerCardView.setText(playerCard.getDefaultText());
            playerCardView.setBackgroundResource(0);
        }
        PlayerCard firstCard = playerCards.get(0);
        TextView playerCardView = (TextView) findViewById(firstCard.getId());
        selectYourCardSpot(playerCardView);
    }

    public void startPractice() {
        Intent intent = new Intent(PokerHelperActivity.this, Practice.class);
        startActivityForResult(intent, 1);
    }


    /**
     * One of the players Card slots has been selected
     * set all others back without a border
     * @param view
     */
    public void selectYourCardSpot(View view){
        if(!buttonLocked) {
            return;
        }
        List<PlayerCard> myCardIds = engine.getPlayerCards();
        for(PlayerCard playerCard: myCardIds) {
            TextView myCard = (TextView) findViewById(playerCard.getId());
            //check if this card is the one that was selected
            if(playerCard.getId() == view.getId()) {
                //if card was already selected unselect
                if(playerCard.isSelected()) {
                    decideBackGround(playerCard, "");
                    playerCard.setSelected(false);
                } else {
                    //card was picked make it the currectly selected one
                    decideBackGround(playerCard, AppConstants.SELECTED);
                    playerCard.setSelected(true);
                }
            }
            else {
                //make sure only one card is selected at a time
                decideBackGround(playerCard, "");
                playerCard.setSelected(false);
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
            public void onClick(View v) {
                selectYourCard(v);
            }
        });
        if(AppConstants.HEARTS.equals(card.getSuite())) {
            textView.setBackgroundResource(R.drawable.heart_card);
        } else if(AppConstants.DIAMONDS.equals(card.getSuite())) {
            textView.setBackgroundResource(R.drawable.diamond_card);
        } else if(AppConstants.SPADES.equals(card.getSuite())) {
            textView.setBackgroundResource(R.drawable.spade_card);
        } else if(AppConstants.CLUBS.equals(card.getSuite())) {
            textView.setBackgroundResource(R.drawable.club_card);
        }

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
            //put the selected card into the player hand
            if(playerCard.isSelected()) {
                playerCardSlot = playerCard;
                TextView myCard = (TextView) findViewById(playerCard.getId());
                playerCard.setCard(selectedCard);
                myCard.setText(selectedCard.getValue());
                playerCard.setSelected(false);
                decideBackGround(playerCard, "");
                cardFound = true;
                checkIfHandIsFull();
            }
        }
        //a card was selected and updated with a cards info
        if(cardFound) {
            if(playerCardSlot.getCardNumber() < 5) {
                for(PlayerCard playerCard: playerCardsList){
                    if(playerCard.getCardNumber() == playerCardSlot.getCardNumber() +1 && playerCard.getCard() == null) {
                        TextView myCard = (TextView) findViewById(playerCard.getId());
                        decideBackGround(playerCard, AppConstants.SELECTED);
                        playerCard.setSelected(true);
                    }
                }
            }
        }
    }

    public void fixASelectedCard(View view) {
        enableFixCardsButton(false);
        for(PlayerCard playerCard: engine.getPlayerCards()){
                playerCard.setSelected(false);
                decideBackGround(playerCard, "");
        }
    }


    private void checkIfHandIsFull() {
        List<PlayerCard> playerCardsList = engine.getPlayerCards();
        for(PlayerCard playerCard: playerCardsList){
            if(playerCard.getCard() == null ) {
                return;
            }
        }
        calculateWhatToDo(playerCardsList);

    }

    private void calculateWhatToDo(List<PlayerCard> playerCardsList) {
        JacksOrBetterRules jacksOrBetterRules = new JacksOrBetterRules();
        jacksOrBetterRules.execute(playerCardsList);
        updateCards(playerCardsList);
        enableFixCardsButton(true);
    }

    private void updateCards(List<PlayerCard> playerCardsList) {
        String whatToDo = "";
        for(PlayerCard playerCard: playerCardsList) {
            if(playerCard.isShouldKeep()) {
                whatToDo = AppConstants.KEEP;
            } else {
                whatToDo = AppConstants.DISCARD;
            }

            decideBackGround(playerCard, whatToDo );
        }
    }

    private void decideBackGround(PlayerCard playerCard, String type) {
        TextView playerCardView = (TextView) findViewById(playerCard.getId());
        Card card = playerCard.getCard();
        if(card == null) {
            if(AppConstants.SELECTED.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.selected_border);
            } else {
                playerCardView.setBackgroundResource(0);
            }
            return;
        }

        if(AppConstants.HEARTS.equals(card.getSuite())) {
            if(AppConstants.SELECTED.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.heart_selected);
            }
            else if(AppConstants.DISCARD.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.heart_discard);
            }
            else if(AppConstants.KEEP.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.heart_keep);
            }
            else {
                playerCardView.setBackgroundResource(R.drawable.heart_card);
            }
        }

        if(AppConstants.DIAMONDS.equals(card.getSuite())) {
            if(AppConstants.SELECTED.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.diamond_selected);
            }
            else if(AppConstants.DISCARD.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.diamond_discard);
            }
            else if(AppConstants.KEEP.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.diamond_keep);
            }
            else {
                playerCardView.setBackgroundResource(R.drawable.diamond_card);
            }
        }

        if(AppConstants.CLUBS.equals(card.getSuite())) {
            if(AppConstants.SELECTED.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.club_selected);
            }
            else if(AppConstants.DISCARD.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.club_discard);
            }
            else if(AppConstants.KEEP.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.club_keep);
            }
            else {
                playerCardView.setBackgroundResource(R.drawable.club_card);
            }
        }

        if(AppConstants.SPADES.equals(card.getSuite())) {
            if(AppConstants.SELECTED.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.spade_selected);
            }
            else if(AppConstants.DISCARD.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.spade_discard);
            }
            else if(AppConstants.KEEP.equals(type)) {
                playerCardView.setBackgroundResource(R.drawable.spade_keep);
            }
            else {
                playerCardView.setBackgroundResource(R.drawable.spade_card);
            }
        }

    }

    private void enableFixCardsButton(boolean action) {
        Button fixCardButton = (Button) findViewById(R.id.findResultButton);
        if(action) {
            buttonLocked = false;
            fixCardButton.setEnabled(true);
        } else {
            buttonLocked = true;
            fixCardButton.setEnabled(false);
        }
    }

}
