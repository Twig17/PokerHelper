package com.ferraro.poker.helper.pokerhelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 7/13/2016.
 */
public class AppConstants {

    final static String HEARTS = "Hearts";
    final static String DIAMONDS = "Diamonds";
    final static String CLUBS = "Clubs";
    final static String SPADES = "Spades";

    final static String DISCARD = "Discard";
    final static String SELECTED = "Selected";
    final static String KEEP = "Keep";
    final static String NORMAL = "Normal";

    final static Map<String, Integer> spadeBackgroundIds = new HashMap<String, Integer>()
    {{
        put(SELECTED, R.drawable.spade_selected);
        put(DISCARD, R.drawable.spade_discard);
        put(KEEP, R.drawable.spade_keep);
        put(NORMAL, R.drawable.spade_card);
    }};

    final static Map<String, Integer> clubBackgroundIds = new HashMap<String, Integer>()
    {{
        put(SELECTED, R.drawable.club_selected);
        put(DISCARD, R.drawable.club_discard);
        put(KEEP, R.drawable.club_keep);
        put(NORMAL, R.drawable.club_card);
    }};

    final static Map<String, Integer> diamondBackgroundIds = new HashMap<String, Integer>()
    {{
        put(SELECTED, R.drawable.diamond_selected);
        put(DISCARD, R.drawable.diamond_discard);
        put(KEEP, R.drawable.diamond_keep);
        put(NORMAL, R.drawable.diamond_card);
    }};

    final static Map<String, Integer> heartBackgroundIds = new HashMap<String, Integer>()
    {{
        put(SELECTED, R.drawable.heart_selected);
        put(DISCARD, R.drawable.heart_discard);
        put(KEEP, R.drawable.heart_keep);
        put(NORMAL, R.drawable.heart_card);
    }};

}
