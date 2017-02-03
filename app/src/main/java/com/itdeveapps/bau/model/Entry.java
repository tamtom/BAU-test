package com.itdeveapps.bau.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */

public class Entry  implements Serializable {
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("link")
    ArrayList<Link> links = new ArrayList<>();
    @SerializedName("media$thumbnail")
    Thumbnail thumbnail;



    public Content getContent() {
        return content;
    }

    public Title getTitle() {
        return title;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
