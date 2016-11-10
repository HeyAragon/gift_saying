package com.qianfeng.aragon.giftsaying.presenter;

import com.qianfeng.aragon.giftsaying.view.IFirstFragment;
import com.qianfeng.aragon.giftsaying.view.IHomeFragment;

/**
 * Created by aragon on 2016/11/7.
 */
public interface IPresenter {
    /**
     * 请求TabLayout标题数据
     */
    void queryTabBean();

    /**
     * 请求精选listView数据
     */
    void querySelectedBean();


    void setView(IHomeFragment homeFragment);

    void setView(IFirstFragment firstFragment);
}
