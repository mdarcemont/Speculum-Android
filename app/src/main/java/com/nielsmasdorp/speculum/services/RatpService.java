package com.nielsmasdorp.speculum.services;

import android.util.Log;

import com.nielsmasdorp.speculum.models.ratp.RatpLineStatus;
import com.nielsmasdorp.speculum.models.ratp.RatpResponse;
import com.nielsmasdorp.speculum.util.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Manuel Darcemont
 */
public class RatpService {

    private RatpApi ratpApi;

    public RatpService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.RATP_TRAFFIC_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ratpApi = retrofit.create(RatpApi.class);
    }

    public Observable<RatpLineStatus> getRatpLineStatus(RatpResponse response) {
        Log.d("My APp", "voila");
        return Observable.just(response.getRatpLineStatus());
    }

    public RatpApi getApi() {

        return ratpApi;
    }

    public interface RatpApi {

        @GET("{type}/{code}")
        Observable<RatpResponse> getRatpLineStatus(@Path("type") String type, @Path("code") String code);
    }
}
