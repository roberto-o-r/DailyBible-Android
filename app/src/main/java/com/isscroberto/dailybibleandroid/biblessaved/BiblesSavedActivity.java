package com.isscroberto.dailybibleandroid.biblessaved;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isscroberto.dailybibleandroid.R;
import com.isscroberto.dailybibleandroid.bibledetail.BibleDetailActivity;
import com.isscroberto.dailybibleandroid.data.models.Bible;
import com.isscroberto.dailybibleandroid.data.source.BibleLocalDataSource;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class BiblesSavedActivity extends AppCompatActivity implements BiblesSavedContract.View {

    //----- Bindings.
    @BindView(R.id.list_bibles)
    RecyclerView listBibles;

    private BiblesSavedContract.Presenter mPresenter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RealmResults<Bible> mBibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibles_saved);

        // Bind views with Butter Knife.
        ButterKnife.bind(this);

        // Setup toolbar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bibles Saved");

        // Setup recycler view.
        listBibles.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        listBibles.setLayoutManager(mLayoutManager);

        // Create the presenter
        new BiblesSavedPresenter(new BibleLocalDataSource(), this);
        mPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

        return true;
    }

    @Override
    public void setPresenter(BiblesSavedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showBibles(RealmResults<Bible> bibles) {
        // Setup recycler view adapter.
        mBibles = bibles;
        mAdapter = new BibleAdapter(this, mBibles);
        listBibles.setAdapter(mAdapter);
    }

    private static class BibleAdapter extends RealmRecyclerViewAdapter<Bible, BibleAdapter.ViewHolder> {

        private Context mContext;

        public BibleAdapter(Context context, RealmResults<Bible> bibles) {
            super(bibles, true);
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bible, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Bible bible = getItem(position);
            holder.textTitle.setText(bible.getTitle());
            holder.textPreview.setText(getExcerpt(bible.getDescription()));

        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textTitle;
            public TextView textPreview;

            public ViewHolder(View v) {
                super(v);
                textTitle = (TextView) v.findViewById(R.id.text_title);
                textPreview = (TextView) v.findViewById(R.id.text_preview);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), BibleDetailActivity.class);
                        intent.putExtra("id", getItem(getAdapterPosition()).getId());
                        intent.putExtra("title", getItem(getAdapterPosition()).getTitle());
                        intent.putExtra("description", getItem(getAdapterPosition()).getDescription());
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        private String getExcerpt(String input) {
            String excerpt = input;
            if(excerpt.lastIndexOf(" ") > -1 && excerpt.length() > 99) {
                excerpt = excerpt.substring(0, 100);
                excerpt = excerpt.substring(0, excerpt.lastIndexOf(" "));
            }
            excerpt = excerpt + "...";
            return excerpt;
        }
    }
}
