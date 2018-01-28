package com.isscroberto.dailybibleandroid.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roberto.orozco on 23/11/2017.
 */

public class Bible extends RealmObject {

    @PrimaryKey
    private String Id;
    private String Title;
    private String Description;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
