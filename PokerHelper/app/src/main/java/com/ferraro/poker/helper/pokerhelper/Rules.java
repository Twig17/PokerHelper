package com.ferraro.poker.helper.pokerhelper;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.HashMap;
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
    Map<String, List<PlayerCard>> suitToCardMap = new HashMap<String, List<PlayerCard>>();
    Map<Integer, List<PlayerCard>> cardValueToCardMap = new HashMap<Integer, List<PlayerCard>>();

    public Rules() {
        engine = Engine.getEngine();
        suitToCardMap = new HashMap<String, List<PlayerCard>>();
        cardValueToCardMap = new HashMap<Integer, List<PlayerCard>>();

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
        if(isFourOfAKind() ) { //Rule 3
            return;
        }
        if(isFourToRoyalFlush() ) { //Rule 4
            return;
        }
        if(isFullHouse() ) { //Rule 5
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(true);
            }
            return;
        }
        if(isFlush() ) { //Rule 6
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(true);
            }
            return;
        }
        if(isThreeOfAKind() ) { //Rule 7
            return;
        }
        if(isStraight() ) { //Rule 8
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(true);
            }
            return;
        }
        //Rule 9 -- needs to be added
        if(isTwoPair() ) { //Rule 10
            return;
        }
        if(isHighPair() ) { //Rule 11
            return;
        }
        if(isThreeToRoyalFlush() ) { //Rule 12
            return;
        }
        if(isFourToFlush() ) { //Rule 13
            return;
        }
        if(isUnsuitedTJQK() ) { //Rule 14
            return;
        }
        if(isLowPair() ) { //Rule 15
            return;
        }
        //Rule 16 -- needs to be done
        //Rule 17 -- needs to be done
        if(isSuitedQJ() ) { //Rule 18
            return;
        }
        ///Rule 19 -- needs to be done
        if(isSuitedKQorKj() ) { //Rule 20
            return;
        }
        if(isSuitedAKorAQorAJ() ) { //Rule 21
            return;
        }
        //Rule 22 -- needs to be done
        //Rule 23 -- needs to be done
        if(isUnSuitedJQK() ) { //Rule 24
            return;
        }
        if(isUnSuitedJQ() ) { //Rule 25
            return;
        }
        if(isSuitedTJ() ) { //Rule 26
            return;
        }
        if(isUnSuitedKPlus() ) { //Rule 27
            return;
        }
        if(isSuitedTQ() ) { //Rule 28
            return;
        }
        if(isUnSuitedAPlus() ) { //Rule 29
            return;
        }
        if(isHasJ() ) { //Rule 30
            return;
        }
        if(isSuitedTK() ) { //Rule 31
            return;
        }
        if(isHasQ() ) { //Rule 32
            return;
        }
        if(isHasK() ) { //Rule 33
            return;
        }
        if(isHasA() ) { //Rule 34
            return;
        }

        //Rule 35 -- todo


    }

    private void sortCards() {
        for(PlayerCard playerCard: playerCardsList) {
            playerCard.setShouldKeep(false);
            sortedMap.put(playerCard.getCard().getIntValue(), playerCard);

            if(!suitToCardMap.containsKey(playerCard.getCard().getSuite())) {
                suitToCardMap.put(playerCard.getCard().getSuite(), new ArrayList<PlayerCard>());
            }
            suitToCardMap.get(playerCard.getCard().getSuite()).add(playerCard);

            if(!cardValueToCardMap.containsKey(playerCard.getCard().getIntValue())) {
                cardValueToCardMap.put(playerCard.getCard().getIntValue(), new ArrayList<PlayerCard>());
            }
            cardValueToCardMap.get(playerCard.getCard().getIntValue()).add(playerCard);
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

    private boolean isFourOfAKind() {
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(4 <= cardValueToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = cardValueToCardMap.get(keySuit);
                result = true;
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            playerCard.setShouldKeep(true);
        }
        return result;

    }

    private boolean isFourToRoyalFlush() {
        String suit ="";
        ArrayList<Integer> royalFlush = new ArrayList<>();
        royalFlush.add(10);
        royalFlush.add(11);
        royalFlush.add(12);
        royalFlush.add(13);
        royalFlush.add(14);
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(4 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            if(royalFlush.contains(playerCard.getCard().getIntValue())) {
                royalFlush.remove(royalFlush.indexOf(playerCard.getCard().getIntValue()));
                playerCard.setShouldKeep(true);
            }
        }
        if(royalFlush.size() < 2) {
            result = true;
        } else {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        return result;
    }

    private boolean isFullHouse() {
        boolean hasThreeSame = false;
        boolean hasTwoSame = false;
        boolean result = false;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(3 == cardValueToCardMap.get(keySuit).size() ) {
                hasThreeSame = true;
            }
            if(2 == cardValueToCardMap.get(keySuit).size() ) {
                hasTwoSame = true;
            }
        }
        if(hasThreeSame && hasTwoSame) {
            result = true;
        }
        return result;

    }

    private boolean isFlush() {
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(5 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                result = true;
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            playerCard.setShouldKeep(true);
        }
        return result;

    }

    private boolean isThreeOfAKind() {
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(3 <= cardValueToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = cardValueToCardMap.get(keySuit);
                result = true;
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            playerCard.setShouldKeep(true);
        }
        return result;

    }

    private boolean isStraight() {
        int previousValue = 0;
        String suit ="";
        if(sortedMap.size() != 5) {
            return false;
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

    private boolean isTwoPair() {
        int hasTwoPair = 0;
        boolean result = false;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(2 == cardValueToCardMap.get(keySuit).size() ) {
                for(PlayerCard playerCard:  cardValueToCardMap.get(keySuit)) {
                    playerCard.setShouldKeep(true);
                }
                hasTwoPair++;
            }
        }
        if(hasTwoPair ==2) {
            result = true;
        }
        else {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }

        }
        return result;

    }

    private boolean isHighPair() {
        boolean result = false;
        int highCards = 0;
        List<PlayerCard> cardsWithSameValue = null;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(2 <= cardValueToCardMap.get(keySuit).size() ) {
                cardsWithSameValue = cardValueToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameValue) {
                    if(playerCard.getCard().getIntValue() > 9) {
                        highCards++;
                    }
                }

            }
        }
        if(highCards == 2){
            for(PlayerCard playerCard: cardsWithSameValue) {
                playerCard.setShouldKeep(true);
            }
            result = true;
        }
        return result;
    }

    private boolean isThreeToRoyalFlush() {
        String suit ="";
        ArrayList<Integer> royalFlush = new ArrayList<>();
        royalFlush.add(10);
        royalFlush.add(11);
        royalFlush.add(12);
        royalFlush.add(13);
        royalFlush.add(14);
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(3 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            if(royalFlush.contains(playerCard.getCard().getIntValue())) {
                royalFlush.remove(royalFlush.indexOf(playerCard.getCard().getIntValue()));
                playerCard.setShouldKeep(true);
            }
        }
        if(royalFlush.size() < 3) {
            result = true;
        }else {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        return result;
    }

    private boolean isFourToFlush() {
        boolean result = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(4 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                result = true;
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        for(PlayerCard playerCard : cardsWithSameSuit) {
            playerCard.setShouldKeep(true);
        }
        return result;

    }

    private boolean isUnsuitedTJQK() {
        boolean result = false;
        boolean hasT = false;
        boolean hasJ = false;
        boolean hasQ = false;
        boolean hasK = false;

        for(PlayerCard playerCard: playerCardsList) {
            if(playerCard.getCard().getIntValue() == 10) {
                hasT = true;
                playerCard.setShouldKeep(true);
            }
            if(playerCard.getCard().getIntValue() == 11) {
                hasJ = true;
                playerCard.setShouldKeep(true);
            }
            if(playerCard.getCard().getIntValue() == 12) {
                hasQ = true;
                playerCard.setShouldKeep(true);
            }
            if(playerCard.getCard().getIntValue() == 13) {
                hasK = true;
                playerCard.setShouldKeep(true);
            }
        }
        if(hasT && hasJ && hasQ && hasK) {
            result = true;
        }
        if(!result) {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        return result;
    }

    private boolean isLowPair() {
        boolean result = false;
        int lowCards = 0;
        List<PlayerCard> cardsWithSameValue = null;
        for(int keySuit :  cardValueToCardMap.keySet() ) {
            if(2 <= cardValueToCardMap.get(keySuit).size() ) {
                cardsWithSameValue = cardValueToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameValue) {
                    if(playerCard.getCard().getIntValue() < 10) {
                        lowCards++;
                    }
                }

            }
        }
        if(lowCards == 2){
            for(PlayerCard playerCard: cardsWithSameValue) {
                playerCard.setShouldKeep(true);
            }
            result = true;
        }
        return result;
    }

    private boolean isSuitedQJ() {
        boolean result = false;
        boolean hasJ = false;
        boolean hasQ = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 11) {
                        hasJ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 12) {
                        hasQ = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if(hasJ == false || hasQ == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isSuitedKQorKj() {
        boolean result = false;
        boolean hasK = false;
        boolean hasJ = false;
        boolean hasQ = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 11) {
                        hasJ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 12) {
                        hasQ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 13) {
                        hasK = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if( (hasK && hasQ) || (hasK && hasJ) ){
            result = true;
        }
        else {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        return result;
    }

    private boolean isSuitedAKorAQorAJ() {
        boolean result = false;
        boolean hasA = false;
        boolean hasK = false;
        boolean hasJ = false;
        boolean hasQ = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 11) {
                        hasJ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 12) {
                        hasQ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 13) {
                        hasK = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 14) {
                        hasA = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if( (hasA && hasK) || (hasA && hasQ) || (hasA && hasJ) ){
            result = true;
        }
        else {
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        return result;
    }

    private boolean isUnSuitedJQK() {
        boolean result = false;
        boolean hasK = false;
        boolean hasJ = false;
        boolean hasQ = false;
        for(PlayerCard playerCard: playerCardsList) {
            if (playerCard.getCard().getIntValue() == 11) {
                hasJ = true;
                playerCard.setShouldKeep(true);
            }
            if (playerCard.getCard().getIntValue() == 12) {
                hasQ = true;
                playerCard.setShouldKeep(true);
            }
            if (playerCard.getCard().getIntValue() == 13) {
                hasK = true;
                playerCard.setShouldKeep(true);
            }
        }
        if(hasK == false || hasJ == false || hasQ == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isUnSuitedJQ() {
        boolean result = false;
        boolean hasJ = false;
        boolean hasQ = false;
        for(PlayerCard playerCard: playerCardsList) {
            if (playerCard.getCard().getIntValue() == 11) {
                hasJ = true;
                playerCard.setShouldKeep(true);
            }
            if (playerCard.getCard().getIntValue() == 12) {
                hasQ = true;
                playerCard.setShouldKeep(true);
            }
        }
        if( hasJ == false || hasQ == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }
    private boolean isSuitedTJ() {
        boolean result = false;
        boolean hasJ = false;
        boolean hasT = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 11) {
                        hasJ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 10) {
                        hasT = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if(hasJ == false || hasT == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isUnSuitedKPlus() {
        boolean result = false;
        boolean hasOther = false;
        boolean hasK = false;
        for(PlayerCard playerCard: playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 10) {
                hasOther = true;
                playerCard.setShouldKeep(true);
            }
            if (playerCard.getCard().getIntValue() == 13) {
                hasK = true;
                playerCard.setShouldKeep(true);
            }
        }
        if( hasOther == false || hasK == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isSuitedTQ() {
        boolean result = false;
        boolean hasJ = false;
        boolean hasQ = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 11) {
                        hasJ = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 12) {
                        hasQ = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if(hasJ == false || hasQ == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isUnSuitedAPlus() {
        boolean result = false;
        boolean hasOther = false;
        boolean hasA = false;
        for(PlayerCard playerCard: playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 10) {
                hasOther = true;
                playerCard.setShouldKeep(true);
            }
            if (playerCard.getCard().getIntValue() == 14) {
                hasA = true;
                playerCard.setShouldKeep(true);
            }
        }
        if( hasOther == false || hasA == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isSuitedTK() {
        boolean result = false;
        boolean hasT = false;
        boolean hasK = false;
        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(2 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(playerCard.getCard().getIntValue() == 10) {
                        hasT = true;
                        playerCard.setShouldKeep(true);
                    }
                    if(playerCard.getCard().getIntValue() == 14) {
                        hasK = true;
                        playerCard.setShouldKeep(true);
                    }
                }
            }
        }
        if(hasT == false || hasK == false){
            for(PlayerCard playerCard: playerCardsList) {
                playerCard.setShouldKeep(false);
            }
        }
        else{
            result = true;
        }
        return result;
    }

    private boolean isHasJ() {
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 11) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }

    private boolean isHasQ() {
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 12) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }


    private boolean isHasK() {
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 13) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }


    private boolean isHasA() {
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 14) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }


}
