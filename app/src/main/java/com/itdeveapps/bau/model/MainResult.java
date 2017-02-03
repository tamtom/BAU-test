package com.itdeveapps.bau.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */

public class MainResult {
    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }
}
