package com.itdeveapps.bau.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */
public class Feed {
    @SerializedName("entry")
    @Expose
    private List<Entry> entry = new ArrayList<>();

    public List<Entry> getEntry() {
        return entry;
    }
}
