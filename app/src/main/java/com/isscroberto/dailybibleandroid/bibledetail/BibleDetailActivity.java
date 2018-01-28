package com.isscroberto.dailybibleandroid.bibledetail;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.isscroberto.dailybibleandroid.BuildConfig;
import com.isscroberto.dailybibleandroid.R;
import com.isscroberto.dailybibleandroid.data.source.BibleLocalDataSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BibleDetailActivity extends AppCompatActivity implements BibleDetailContract.View {

    //----- UI Bindings.
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.ad_view)
    AdView adView;

    private BibleDetailContract.Presenter mPresenter;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_detail);

        // Bind views with Butter Knife.
        ButterKnife.bind(this);

        // Setup toolbar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saved Bibles");

        // Verify if ads are enabled.
        Boolean adsEnabled = getSharedPreferences("com.isscroberto.dailybibleandroid", MODE_PRIVATE).getBoolean("AdsEnabled", true);
        if (adsEnabled) {
            // Load Ad Banner.
            AdRequest adRequest;
            if (BuildConfig.DEBUG) {
                adRequest = new AdRequest.Builder()
                        .addTestDevice(getString(R.string.test_device))
                        .build();
            } else {
                adRequest = new AdRequest.Builder().build();
            }
            adView.loadAd(adRequest);

            adView.setAdListener(new AdListener() {

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    adView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    adView.setVisibility(View.GONE);
                }
            });
        }

        // Get bible.
        mId = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        // Show bible.
        textTitle.setText(title);
        textContent.setText(description);

        // Create the presenter
        new BibleDetailPresenter(new BibleLocalDataSource(), this);
        mPresenter.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.bible, menu);

        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(BibleDetailActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteBible(mId);
                        finish();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void setPresenter(BibleDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
