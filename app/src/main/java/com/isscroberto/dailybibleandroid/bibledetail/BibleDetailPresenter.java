package com.isscroberto.dailybibleandroid.bibledetail;

import com.isscroberto.dailybibleandroid.data.source.BibleLocalDataSource;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public class BibleDetailPresenter implements BibleDetailContract.Presenter {

    private final BibleLocalDataSource mBibleLocalDataSource;
    private final BibleDetailContract.View mView;

    public BibleDetailPresenter(BibleLocalDataSource bibleLocalDataSource, BibleDetailContract.View view) {
        mBibleLocalDataSource = bibleLocalDataSource;
        mView = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void deleteBible(String id) {
        mBibleLocalDataSource.delete(id);
    }
}
