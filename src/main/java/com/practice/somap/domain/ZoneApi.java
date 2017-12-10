package com.practice.somap.domain;



import android.util.Log;

import com.practice.somap.BuildConfig;

import java.net.URLEncoder;
import java.util.List;

import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by JisangYou on 2017-11-21.
 */

public class ZoneApi {

    public static List<Data> data;

    public static void getZones(Callback callback) throws Exception{
        getZones("",callback);
    }

    // Zone 데이터를 가져오는 함수
    public static void getZones(String area, Callback callback) throws Exception{
        // 레트로핏 정의
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.SERVER_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        // 2. 서비스 만들기 < 인터페이스로부터
        IZone service = retrofit.create(IZone.class);
        // 3. 옵저버블(Emitter) 생성
        Observable<Zone> observable = service.getData(area);

        // 4. 데이터 가져오기
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        zone -> {
                            if(zone.isSuccess()){
                                data = zone.getData();
                                callback.init();
                            }
                        }
                );
    }

    // 인터페이스 생성
    public interface IZone {
        @GET("zone/{param1}")
        Observable<Zone> getData(@Path("param1") String area);
    }
    public interface Callback {
        void init();
    }
}
