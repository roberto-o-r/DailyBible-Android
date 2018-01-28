package com.isscroberto.dailybibleandroid.data.source.retrofit;

import com.isscroberto.dailybibleandroid.data.models.BingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by roberto.orozco on 23/11/2017.
 */

public interface ImageApi {

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US")
    Call<BingResponse> get();

}
