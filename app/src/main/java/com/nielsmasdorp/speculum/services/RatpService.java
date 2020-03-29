package com.nielsmasdorp.speculum.services;

import android.util.Log;

import com.nielsmasdorp.speculum.models.ratp.RatpLine;
import com.nielsmasdorp.speculum.models.ratp.RatpLineStatus;
import com.nielsmasdorp.speculum.models.ratp.RatpLineStatuses;
import com.nielsmasdorp.speculum.models.ratp.RatpResponse;
import com.nielsmasdorp.speculum.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Func2;

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

    public Observable<RatpLineStatuses> getRatpLineStatuses() {
        List<RatpLine> lines = new ArrayList<>();
        lines.add(new RatpLine("rers", "b"));
        lines.add(new RatpLine("metros", "2"));
        lines.add(new RatpLine("metros", "3"));


        return Observable.from(lines)
                .flatMap(line ->this.getApi().getRatpLineStatus(line.getType(), line.getCode()))
                .map(RatpResponse::getRatpLineStatus)
                .toList()
                .map(RatpLineStatuses::new);
    }

    public RatpApi getApi() {

        return ratpApi;
    }

    public interface RatpApi {

        @GET("{type}/{code}")
        Observable<RatpResponse> getRatpLineStatus(@Path("type") String type, @Path("code") String code);
    }
}
