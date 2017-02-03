package com.itdeveapps.bau.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */

public class Title  implements Serializable {
    @SerializedName("$t")
    String text;

    public String getText() {
        return text;
    }
}
