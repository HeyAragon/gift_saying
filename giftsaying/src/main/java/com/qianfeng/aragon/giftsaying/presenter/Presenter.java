package com.qianfeng.aragon.giftsaying.presenter;

import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.qianfeng.aragon.giftsaying.bean.SelectedBean;
import com.qianfeng.aragon.giftsaying.bean.TabBean;
import com.qianfeng.aragon.giftsaying.model.IApiService;
import com.qianfeng.aragon.giftsaying.view.IFirstFragment;
import com.qianfeng.aragon.giftsaying.view.IHomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by aragon on 2016/11/7.
 */
public class Presenter implements IPresenter{
    private IApiService apiService;
    private IHomeFragment homeFragment;
    private IFirstFragment firstFragment;
    private String TAG = "androidhy";

    public Presenter(IApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setView(IHomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void setView(IFirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    /**
     * 请求TabLayout标题数据
     */
    public void queryTabBean() {
        final Map<String, String> map = new ArrayMap<>();
        final List<String> tabList = new ArrayList<>();
        apiService.queryTabBean().flatMap(new Func1<TabBean, Observable<TabBean.DataBean.ChannelsBean>>() {
            @Override
            public Observable<TabBean.DataBean.ChannelsBean> call(TabBean tabBean) {
                List<TabBean.DataBean.ChannelsBean> channels = tabBean.getData().getChannels();
                return Observable.from(channels);
            }
        }).map(new Func1<TabBean.DataBean.ChannelsBean, List<String>>() {
            @Override
            public List<String> call(TabBean.DataBean.ChannelsBean channelsBean) {
                if (channelsBean != null) {
                    
                    String name = channelsBean.getName();
                    tabList.add(name);
                    Log.i(TAG, "call: "+tabList.size());
//                    for (int i = 0; i < tabList.size(); i++) {
//                        Log.i(TAG, "call: "+tabList.get(i));
//                    }
                }
                return tabList;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        homeFragment.refreshTab(tabList);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {

                    }
                });
    }

    @Override
    public void querySelectedBean() {
        apiService.querySelectedBean().map(new Func1<SelectedBean, List<SelectedBean.DataBean.ItemsBean>>() {
            @Override
            public List<SelectedBean.DataBean.ItemsBean> call(SelectedBean selectedBean) {
                List<SelectedBean.DataBean.ItemsBean> items = selectedBean.getData().getItems();
                Log.i(TAG, "call: ------->"+items.size());
                return items;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SelectedBean.DataBean.ItemsBean>>() {
                    @Override
                    public void call(List<SelectedBean.DataBean.ItemsBean> itemsBeen) {
                        Log.i(TAG, "call: "+itemsBeen.size());
                        firstFragment.refreshSelectedList(itemsBeen);

                    }
                });
    }

//    @Override
//    public void querySelectedBean() {
////        List<>
//        apiService.querySelectedBean().flatMap(new Func1<SelectedBean, Observable<SelectedBean.DataBean.ItemsBean>>() {
//            @Override
//            public Observable<SelectedBean.DataBean.ItemsBean> call(SelectedBean selectedBean) {
//                List<SelectedBean.DataBean.ItemsBean> items = selectedBean.getData().getItems();
////                items.get
//                return Observable.from(items);
//            }
//        }).subscribeOn(Schedulers.newThread())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Observer<SelectedBean.DataBean.ItemsBean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(SelectedBean.DataBean.ItemsBean itemsBean) {
//
//            }
//        });
//    }


}
