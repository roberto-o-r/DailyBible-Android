package com.isscroberto.dailybibleandroid.bibledetail;

import com.isscroberto.dailybibleandroid.BasePresenter;
import com.isscroberto.dailybibleandroid.BaseView;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public interface BibleDetailContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void deleteBible(String id);
    }
}
