package com.isscroberto.dailybibleandroid;

import android.app.Application;
import android.os.Build;

import com.github.stkent.amplify.*;
import com.github.stkent.amplify.BuildConfig;
import com.github.stkent.amplify.feedback.DefaultEmailFeedbackCollector;
import com.github.stkent.amplify.feedback.GooglePlayStoreFeedbackCollector;
import com.github.stkent.amplify.tracking.Amplify;

import io.realm.Realm;

public class DailyBibleAndroid extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm.
        Realm.init(this);

        // Feedback.
        Amplify.initSharedInstance(this)
                .setPositiveFeedbackCollectors(new GooglePlayStoreFeedbackCollector())
                .setCriticalFeedbackCollectors(new DefaultEmailFeedbackCollector(getString(R.string.my_email)))
                .applyAllDefaultRules();
        //.setAlwaysShow(BuildConfig.DEBUG);
    }

}
