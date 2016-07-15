package com.ferraro.poker.helper.pokerhelper;

import android.support.v7.app.ActionBarActivity;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Nick on 7/15/2016.
 */
public class Rules  extends ActionBarActivity {
    Engine engine;
    List<PlayerCard> playerCardsList;
    Map<Integer, PlayerCard> sortedMap = new TreeMap<Integer, PlayerCard>();

    public Rules() {
        engine = Engine.getEngine();

    }


    public void execute(List<PlayerCard> playerCardsList) {
        this.playerCardsList = playerCardsList;
        sortCards();
        //36 rules to check
        if(isRoyalFlush() ) { //Rule 1
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(true);
            }
            return;
        }
        if(isStraightFlush() ) { //Rule 2
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(true);
            }
            return;
        }

    }

    private void sortCards() {
        for(PlayerCard playerCard: playerCardsList) {
            playerCard.setShouldKeep(false);
            sortedMap.put(playerCard.getCard().getIntValue(), playerCard);
        }

    }

    private boolean isRoyalFlush() {
        String suit ="";
        if(sortedMap.size() != 5) {
            return false;
        }
        if(sortedMap.get(10) != null &&  sortedMap.get(11) != null &&
                sortedMap.get(12) != null && sortedMap.get(13) != null &&
                sortedMap.get(14) != null) {
            for(PlayerCard playerCard: playerCardsList) {
                if("".equals(suit)) {
                    suit = playerCard.getCard().getSuite();
                }
                else if(!suit.equals( playerCard.getCard().getSuite())) {
                    return false;
                }
             }
            return true;
        }
        return false;
    }


    private boolean isStraightFlush() {
        int previousValue = 0;
        String suit ="";
        if(sortedMap.size() != 5) {
            return false;
        }
        for(PlayerCard playerCard: playerCardsList) {
            if("".equals(suit)) {
                suit = playerCard.getCard().getSuite();
            }
            else if(!suit.equals( playerCard.getCard().getSuite())) {
                return false;
            }
        }
        for (Map.Entry<Integer, PlayerCard> entry : sortedMap.entrySet()) {
            if(previousValue == 0) {
                previousValue = entry.getKey();
                continue;
            } else if(entry.getKey() == previousValue + 1) {
                previousValue = entry.getKey();
                continue;
            }
            return false;
        }

        return true;
    }


}
