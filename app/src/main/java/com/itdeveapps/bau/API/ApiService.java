package com.itdeveapps.bau.API;

import com.itdeveapps.bau.model.MainResult;


import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */

public interface ApiService {
    String SERVICE_ENDPOINT = "https://baunews2017.blogspot.com";
    @GET("/feeds/posts/default?alt=json")
    Observable<MainResult> getResult();

}
