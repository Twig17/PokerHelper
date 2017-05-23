package com.ferraro.poker.helper.pokerhelper;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Nick on 7/15/2016.
 */
public class JacksOrBetterRules extends ActionBarActivity {
    Engine engine;
    List<PlayerCard> playerCardsList;
    Map<Integer, PlayerCard> sortedMap = new TreeMap<Integer, PlayerCard>();
    Map<String, List<PlayerCard>> suitToCardMap = new HashMap<String, List<PlayerCard>>();
    Map<Integer, List<PlayerCard>> cardValueToCardMap = new HashMap<Integer, List<PlayerCard>>();

    public JacksOrBetterRules() {
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
        if(isFourToStraightFlush()) { //Rule 9
            return;
        }
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
        if(isFourToOutsideStrightMinimalHighCards()) { //Rule 16
            return;
        }
        if(isThreeToStraightFlush()) { //Rule 17
            return;
        }
        if(isSuitedQJ() ) { //Rule 18
            return;
        }
        if(isFourToStraightWithFourHighCards()) { //Rule 19
            return;
        }
        if(isSuitedKQorKj() ) { //Rule 20
            return;
        }
        if(isSuitedAKorAQorAJ() ) { //Rule 21
            return;
        }
        if(isFourToStraightWithThreeHighCards() ) { //Rule 22
            return;
        }
        if(isThreeToStraight() ) { //Rule 23
            return;
        }
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

    private void cleanCards() {
        for(PlayerCard card : playerCardsList) {
            card.setShouldKeep(false);
        }
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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

    private boolean isFourToStraightWithFourHighCards() {
        cleanCards();
        List<Integer> straight8 = Arrays.asList(new Integer[]{9,10,11,12,13});
        List<Integer> straight9 = Arrays.asList(new Integer[]{10,11,12,13,14});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);

        int matchesIArray = 0;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: playerCardsList) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                    matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 4) {
                return true;
            }
            matchesIArray = 0;
        }
        return false;
    }

    private boolean isSuitedKQorKj() {
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
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
        cleanCards();
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 11) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }

    private boolean isHasQ() {
        cleanCards();
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 12) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }


    private boolean isHasK() {
        cleanCards();
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 13) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }


    private boolean isHasA() {
        cleanCards();
        for (PlayerCard playerCard : playerCardsList) {
            if (playerCard.getCard().getIntValue() >= 14) {
                playerCard.setShouldKeep(true);
                return true;
            }
        }
        return false;
    }

    private boolean isFourToStraightFlush() {
        cleanCards();
        List<Integer> straight1 = Arrays.asList(new Integer[]{2,3,4,5,6});
        List<Integer> straight2 = Arrays.asList(new Integer[]{3,4,5,6,7});
        List<Integer> straight3 = Arrays.asList(new Integer[]{4,5,6,7,8});
        List<Integer> straight4 = Arrays.asList(new Integer[]{5,6,7,8,9});
        List<Integer> straight5 = Arrays.asList(new Integer[]{6,7,8,9,10});
        List<Integer> straight6 = Arrays.asList(new Integer[]{7,8,9,10,11});
        List<Integer> straight7 = Arrays.asList(new Integer[]{8,9,10,11,12});
        List<Integer> straight8 = Arrays.asList(new Integer[]{9,10,11,12,13});
        List<Integer> straight9 = Arrays.asList(new Integer[]{10, 11, 12, 13, 14});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight1);
        possibleStraights.add(straight2);
        possibleStraights.add(straight3);
        possibleStraights.add(straight4);
        possibleStraights.add(straight5);
        possibleStraights.add(straight6);
        possibleStraights.add(straight7);
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);

        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(4 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        int matchesIArray = 0;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                   matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 4) {
                return true;
            }
            matchesIArray = 0;
        }
        return false;
    }

    private boolean isFourToOutsideStrightMinimalHighCards() {
        cleanCards();
        List<Integer> straight1 = Arrays.asList(new Integer[]{3,4,5,6});
        List<Integer> straight2 = Arrays.asList(new Integer[]{2,3,4,5});
        List<Integer> straight3 = Arrays.asList(new Integer[]{4,5,6,7});
        List<Integer> straight4 = Arrays.asList(new Integer[]{3,4,5,6});
        List<Integer> straight5 = Arrays.asList(new Integer[]{5,6,7,8});
        List<Integer> straight6 = Arrays.asList(new Integer[]{4,5,6,7});
        List<Integer> straight7 = Arrays.asList(new Integer[]{6,7,8,9});
        List<Integer> straight8 = Arrays.asList(new Integer[]{5,6,7,8});
        List<Integer> straight9 = Arrays.asList(new Integer[]{7,8,9,10});
        List<Integer> straight10 = Arrays.asList(new Integer[]{6,7,8,9});
        List<Integer> straight11 = Arrays.asList(new Integer[]{8,9,10,11});
        List<Integer> straight12 = Arrays.asList(new Integer[]{7,8,9,10});
        List<Integer> straight13 = Arrays.asList(new Integer[]{8,9,10,11});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight1);
        possibleStraights.add(straight2);
        possibleStraights.add(straight3);
        possibleStraights.add(straight4);
        possibleStraights.add(straight5);
        possibleStraights.add(straight6);
        possibleStraights.add(straight7);
        possibleStraights.add(straight8);
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);
        possibleStraights.add(straight10);
        possibleStraights.add(straight11);
        possibleStraights.add(straight12);
        possibleStraights.add(straight13);

        int matchesIArray = 0;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: playerCardsList) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                    matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 4) {
                return true;
            }
            matchesIArray = 0;
        }
        return false;
    }

    private boolean isThreeToStraightFlush() {
        cleanCards();
        List<Integer> straight1 = Arrays.asList(new Integer[]{2,3,4,5,6});
        List<Integer> straight2 = Arrays.asList(new Integer[]{3,4,5,6,7});
        List<Integer> straight3 = Arrays.asList(new Integer[]{4,5,6,7,8});
        List<Integer> straight4 = Arrays.asList(new Integer[]{5,6,7,8,9});
        List<Integer> straight5 = Arrays.asList(new Integer[]{6,7,8,9,10});
        List<Integer> straight6 = Arrays.asList(new Integer[]{7,8,9,10,11});
        List<Integer> straight7 = Arrays.asList(new Integer[]{8,9,10,11,12});
        List<Integer> straight8 = Arrays.asList(new Integer[]{9,10,11,12,13});
        List<Integer> straight9 = Arrays.asList(new Integer[]{10, 11, 12, 13, 14});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight1);
        possibleStraights.add(straight2);
        possibleStraights.add(straight3);
        possibleStraights.add(straight4);
        possibleStraights.add(straight5);
        possibleStraights.add(straight6);
        possibleStraights.add(straight7);
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);

        List<PlayerCard> cardsWithSameSuit = null;
        for(String keySuit :  suitToCardMap.keySet() ) {
            if(3 <= suitToCardMap.get(keySuit).size() ) {
                cardsWithSameSuit = suitToCardMap.get(keySuit);
            }
        }
        if(cardsWithSameSuit == null) {
            return false;
        }
        int matchesIArray = 0;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: cardsWithSameSuit) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                    matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 3) {
                return true;
            }
            matchesIArray = 0;
        }
        return false;
    }

    private boolean isFourToStraightWithThreeHighCards() {
        cleanCards();
        List<Integer> straight7 = Arrays.asList(new Integer[]{8,9,10,11,12});
        List<Integer> straight8 = Arrays.asList(new Integer[]{9,10,11,12,13});
        List<Integer> straight9 = Arrays.asList(new Integer[]{10, 11, 12, 13, 14});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight7);
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);

        int matchesIArray = 0;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: playerCardsList) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                    matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 4) {
                return true;
            }
            matchesIArray = 0;
        }
        return false;
       }

    private boolean isThreeToStraight() {
        cleanCards();
        List<Integer> straight1 = Arrays.asList(new Integer[]{2,3,4,5,6});
        List<Integer> straight2 = Arrays.asList(new Integer[]{3,4,5,6,7});
        List<Integer> straight3 = Arrays.asList(new Integer[]{4,5,6,7,8});
        List<Integer> straight4 = Arrays.asList(new Integer[]{5,6,7,8,9});
        List<Integer> straight5 = Arrays.asList(new Integer[]{6,7,8,9,10});
        List<Integer> straight6 = Arrays.asList(new Integer[]{7,8,9,10,11});
        List<Integer> straight7 = Arrays.asList(new Integer[]{8,9,10,11,12});
        List<Integer> straight8 = Arrays.asList(new Integer[]{9,10,11,12,13});
        List<Integer> straight9 = Arrays.asList(new Integer[]{10, 11, 12, 13, 14});
        ArrayList<List<Integer>> possibleStraights = new ArrayList<List<Integer>>();
        possibleStraights.add(straight1);
        possibleStraights.add(straight2);
        possibleStraights.add(straight3);
        possibleStraights.add(straight4);
        possibleStraights.add(straight5);
        possibleStraights.add(straight6);
        possibleStraights.add(straight7);
        possibleStraights.add(straight8);
        possibleStraights.add(straight9);

        int matchesIArray = 0;
        boolean result = false;
        for(List<Integer> intArray: possibleStraights) {
            matchesIArray = 0;
            for(int i = 0; i < intArray.size(); i++) {
                boolean shouldRemove = false;
                for(PlayerCard playerCard: playerCardsList) {
                    if(intArray.get(i) == playerCard.getCard().getIntValue())
                    {
                        playerCard.setShouldKeep(true);
                        shouldRemove = true;
                    }
                }//end of playercards
                if(shouldRemove) {
                    matchesIArray++ ;
                    shouldRemove = false;
                }
            }//end of int array
            if(matchesIArray == 3) {
                return true;
            }
            matchesIArray = 0;
            cleanCards();
        }
        return result;
    }
}
