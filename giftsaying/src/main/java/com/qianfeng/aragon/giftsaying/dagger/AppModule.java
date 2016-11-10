package com.qianfeng.aragon.giftsaying.dagger;

import android.support.v4.app.FragmentManager;

import com.qianfeng.aragon.giftsaying.Adapter.MyPagerAdapter;
import com.qianfeng.aragon.giftsaying.model.IApiService;
import com.qianfeng.aragon.giftsaying.presenter.IPresenter;
import com.qianfeng.aragon.giftsaying.presenter.Presenter;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aragon on 2016/11/7.
 */
@Module
public class AppModule {

    @Provides
    public OkHttpClient provideOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        return okHttpClient;
    }
    @Provides
    public IApiService provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiService apiService = retrofit.create(IApiService.class);
        return apiService;
    }

    @Provides
    public IPresenter provideAppPresenter(IApiService apiService) {
        return new Presenter(apiService);
    }


}
