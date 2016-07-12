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

import java.util.ArrayList;
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

    public void selectYourCard(View view){
        List<Integer> myCardIds = new ArrayList<Integer>();
        myCardIds.add(R.id.PlayerCard1);
        myCardIds.add(R.id.PlayerCard2);
        myCardIds.add(R.id.PlayerCard3);
        myCardIds.add(R.id.PlayerCard4);
        myCardIds.add(R.id.PlayerCard5);
        for(int id: myCardIds) {
            TextView myCard = (TextView) findViewById(id);
            if(id == view.getId()) {
                myCard.setBackgroundResource(R.drawable.button_border);
            }
            else {
                myCard.setBackgroundResource(0);
            }
        }
    }

    private void addCardsToDisplay(List<Card> playingCards){
        LinearLayout heartsCardList = (LinearLayout) findViewById(R.id.heartsCardView);

        for(Card card: playingCards) {
            TextView heart2TextView = new TextView(this);
            heart2TextView.setLayoutParams(new ViewGroup.LayoutParams(
                    120,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            heart2TextView.setText(card.getValue());
            heart2TextView.setGravity(Gravity.CENTER);
            heart2TextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    selectYourCard2();
                }
            });
            heart2TextView.setBackgroundResource(R.drawable.playing_card_border);
            heartsCardList.addView(heart2TextView);
        }
    }

    public void selectYourCard2(){
            TextView myCard = (TextView) findViewById(R.id.PlayerCard5);
                myCard.setBackgroundResource(R.drawable.button_border);
    }

}
