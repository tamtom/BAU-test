package com.itdeveapps.bau.API;

/**
 * Created by Omar AlTamimi on 1/28/2017.
 */
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Omar AlTamimi on 9/1/2016.
 */
public class ApiClient {
    private static Retrofit retrofit = null;
    public  static  ApiService getApi(){
        return  ApiClient.getClient().create(ApiService.class);
    }
    private static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ApiService.SERVICE_ENDPOINT)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
