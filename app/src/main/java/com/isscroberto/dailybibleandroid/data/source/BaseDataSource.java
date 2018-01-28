package com.isscroberto.dailybibleandroid.data.source;

import retrofit2.Callback;

/**
 * Created by roberto.orozco on 23/11/2017.
 */

public interface BaseDataSource<T> {

    void get(Callback<T> callback);

}
