package com.isscroberto.dailybibleandroid.data.source;

import com.isscroberto.dailybibleandroid.data.models.RssResponse;
import com.isscroberto.dailybibleandroid.data.source.retrofit.BibleApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public class BibleRemoteDataSource implements BaseDataSource<RssResponse> {

    @Override
    public void get(final Callback<RssResponse> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://feeds.feedburner.com/").addConverterFactory(SimpleXmlConverterFactory.create()).build();
        BibleApi api = retrofit.create(BibleApi.class);
        Call<RssResponse> apiCall = api.get();
        apiCall.enqueue(callback);
    }

    public void getRaw(final Callback<ResponseBody> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://feeds.feedburner.com/").addConverterFactory(SimpleXmlConverterFactory.create()).build();
        BibleApi api = retrofit.create(BibleApi.class);
        Call<ResponseBody> apiCall = api.getRaw();
        apiCall.enqueue(callback);
    }

}
