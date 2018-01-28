package com.isscroberto.dailybibleandroid.data.source.retrofit;

import com.isscroberto.dailybibleandroid.data.models.RssResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BibleApi {

    @GET("theDailyBibleVerse?format=xml")
    Call<RssResponse> get();

    @GET("theDailyBibleVerse?format=xml")
    Call<ResponseBody> getRaw();

}
