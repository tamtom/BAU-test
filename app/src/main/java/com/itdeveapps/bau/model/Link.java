package com.itdeveapps.bau.model;

import java.io.Serializable;

/**
 * Created by Omar AlTamimi on 2/2/2017.
 */

public class Link implements Serializable {
    String rel;
    String href;

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }
    public static final String   LINK = "alternate";
}
