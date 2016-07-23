package com.ferraro.poker.helper.pokerhelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class Practice extends ActionBarActivity {
    Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        engine = Engine.getEngine();
        setContentView(R.layout.activity_practice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practice, menu);
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


    /**
     * Select five random cards to put into the players hand
     */
    public void practiceDeal(View view) {
        List<PlayerCard> playerCards = engine.getPlayerCards();
        List<Card> allCards = engine.getPlayingCards();
        int min = 0 ;
        for(PlayerCard playerCard: playerCards){
            Random r = new Random();
            Card randomCard = allCards.get(r.nextInt((allCards.size()-1 - min) + 1) + min);
            playerCard.setCard(randomCard);
            TextView playerCardView = (TextView) findViewById(playerCard.getId());
            playerCardView.setText(randomCard.getValue());
            decideBackGround(playerCard, "");
        }
    }

    public void practiceResult(View view) {

    }

    public void selectCardToKeep(View view) {
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

}
