package com.isscroberto.dailybibleandroid.data.source;

import com.isscroberto.dailybibleandroid.data.models.Bible;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by roberto.orozco on 23/11/2017.
 */

public class BibleLocalDataSource {

    private Realm mRealm;

    public BibleLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public RealmResults<Bible> get () {
        return mRealm.where(Bible.class).findAllSorted("Id", Sort.DESCENDING);
    }

    public Bible get(String id) {
        return mRealm.where(Bible.class).equalTo("Id", id).findFirst();
    }

    public Bible put(Bible bible) {
        mRealm.beginTransaction();
        Bible managedBible = mRealm.copyToRealm(bible);
        mRealm.commitTransaction();
        return managedBible;
    }

    public void delete (String id) {
        final Bible bible = mRealm.where(Bible.class).equalTo("Id", id).findFirst();
        if(bible != null) {
            mRealm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    bible.deleteFromRealm();
                }

            });
        }
    }

}
