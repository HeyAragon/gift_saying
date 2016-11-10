package com.qianfeng.aragon.giftsaying.model;

import com.qianfeng.aragon.giftsaying.bean.SelectedBean;
import com.qianfeng.aragon.giftsaying.bean.TabBean;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by aragon on 2016/11/7.
 */
public interface IApiService {
    @GET("v2/channels/preset?gender=2&generation=2")
    Observable<TabBean> queryTabBean();

    @GET("v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0")
    Observable<SelectedBean> querySelectedBean();
}