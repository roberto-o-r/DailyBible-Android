package com.isscroberto.dailybibleandroid.bible;

import com.isscroberto.dailybibleandroid.data.models.Bible;
import com.isscroberto.dailybibleandroid.data.models.BingResponse;
import com.isscroberto.dailybibleandroid.data.models.Item;
import com.isscroberto.dailybibleandroid.data.models.RssResponse;
import com.isscroberto.dailybibleandroid.data.source.BibleLocalDataSource;
import com.isscroberto.dailybibleandroid.data.source.BibleRemoteDataSource;
import com.isscroberto.dailybibleandroid.data.source.ImageRemoteDataSource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roberto.orozco on 24/11/2017.
 */

public class BiblePresenter implements BibleContract.Presenter {

    private final BibleRemoteDataSource mBibleDataSource;
    private final BibleLocalDataSource mBibleLocalDataSource;
    private final ImageRemoteDataSource mImageDataSource;
    private final BibleContract.View mView;

    public BiblePresenter(BibleRemoteDataSource bibleDataSource, BibleLocalDataSource bibleLocalDataSource, ImageRemoteDataSource imageDataSource, BibleContract.View view) {
        mBibleDataSource = bibleDataSource;
        mBibleLocalDataSource = bibleLocalDataSource;
        mImageDataSource = imageDataSource;
        mView = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBible();
        loadImage();
    }

    @Override
    public void loadBible() {
        mView.setLoadingIndicator(true);
        mBibleDataSource.get(new Callback<RssResponse>() {
            @Override
            public void onResponse(Call<RssResponse> call, Response<RssResponse> response) {
                // Verify that response is not empty.
                if(response.body() != null) {
                    Item bible = response.body().getChannel().getItem();

                    // Quitar contenido sobrante.
                    if(bible.getEncoded().indexOf("<div id=\"commentary\"") >= 0) {
                        bible.setEncoded(bible.getEncoded().substring(0, bible.getEncoded().indexOf("<div id=\"commentary\"")));
                    }
                    if(bible.getEncoded().indexOf("<p>Today&#8217;s commentary") >= 0) {
                        bible.setEncoded(bible.getEncoded().substring(0, bible.getEncoded().indexOf("<p>Today&#8217;s commentary")));
                    }
                    bible.setDescription(bible.getEncoded());

                    // Create bible id based on the date.
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    df.setTimeZone(TimeZone.getTimeZone("gmt"));
                    String id = df.format(new Date());

                    // Create bible title.
                    df = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
                    bible.setTitle("Daily Bible for " + df.format(new Date()));

                    // Verify if bible is saved.
                    bible.setFav(false);
                    if (mBibleLocalDataSource.get(id) != null) {
                        bible.setFav(true);
                    }
                    mView.showBible(bible);
                } else {
                    mView.showError();
                }

                mView.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(Call<RssResponse> call, Throwable t) {
                mView.showError();
                mView.setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void loadImage() {
        mImageDataSource.get(new Callback<BingResponse>() {
            @Override
            public void onResponse(Call<BingResponse> call, Response<BingResponse> response) {
                // Verify response.
                if(response.body() != null) {
                    if (!response.body().getImages().isEmpty()) {
                        mView.showImage("http://www.bing.com/" + response.body().getImages().get(0).getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<BingResponse> call, Throwable t) {
                // Don't do nothing.
            }
        });
    }

    @Override
    public void saveBible(Bible bible) {
        mBibleLocalDataSource.put(bible);
    }

    @Override
    public void deleteBible(String id) {
        mBibleLocalDataSource.delete(id);
    }
    
}
