package com.isscroberto.dailybibleandroid.biblessaved;

import com.isscroberto.dailybibleandroid.data.models.Bible;
import com.isscroberto.dailybibleandroid.data.source.BibleLocalDataSource;

import io.realm.RealmResults;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public class BiblesSavedPresenter implements BiblesSavedContract.Presenter {

    private final BibleLocalDataSource mBibleLocalDataSource;
    private final BiblesSavedContract.View mView;

    public BiblesSavedPresenter(BibleLocalDataSource bibleLocalDataSource, BiblesSavedContract.View view) {
        mBibleLocalDataSource = bibleLocalDataSource;
        mView = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBibles();
    }

    @Override
    public void loadBibles() {
        RealmResults<Bible> bibles = mBibleLocalDataSource.get();
        mView.showBibles(bibles);
    }
}
