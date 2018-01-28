package com.isscroberto.dailybibleandroid.bible;

import com.isscroberto.dailybibleandroid.BasePresenter;
import com.isscroberto.dailybibleandroid.BaseView;
import com.isscroberto.dailybibleandroid.data.models.Bible;
import com.isscroberto.dailybibleandroid.data.models.Item;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public interface BibleContract {

    interface View extends BaseView<Presenter> {
        void showBible(Item bible);
        void showError();
        void showImage(String url);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter {
        void loadBible();
        void loadImage();
        void saveBible(Bible bible);
        void deleteBible(String id);
    }

}
