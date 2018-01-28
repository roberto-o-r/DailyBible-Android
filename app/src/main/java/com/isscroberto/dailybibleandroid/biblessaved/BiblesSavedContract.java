package com.isscroberto.dailybibleandroid.biblessaved;

import com.isscroberto.dailybibleandroid.BasePresenter;
import com.isscroberto.dailybibleandroid.BaseView;
import com.isscroberto.dailybibleandroid.data.models.Bible;

import io.realm.RealmResults;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public interface BiblesSavedContract {

    interface View extends BaseView<Presenter> {
        void showBibles(RealmResults<Bible> bibles);
    }

    interface Presenter extends BasePresenter {
        void loadBibles();
    }

}