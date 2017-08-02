package com.ferraro.poker.helper.pokerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nick on 2/24/2017.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

        Intent setupIntent = new Intent(this, PokerHelperActivity.class);
        startActivity(setupIntent);
        finish();

    }
}
