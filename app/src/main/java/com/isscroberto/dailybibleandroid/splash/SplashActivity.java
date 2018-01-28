package com.isscroberto.dailybibleandroid.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.isscroberto.dailybibleandroid.R;
import com.isscroberto.dailybibleandroid.bible.BibleActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // AdMob.
        MobileAds.initialize(this, getString(R.string.ad_mob_app_id));

        Intent intent = new Intent(this, BibleActivity.class);
        startActivity(intent);
        finish();
    }
}
