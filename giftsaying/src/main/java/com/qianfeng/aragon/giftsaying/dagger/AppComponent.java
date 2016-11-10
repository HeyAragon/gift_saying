package com.qianfeng.aragon.giftsaying.dagger;

import com.qianfeng.aragon.giftsaying.primary_fragment.HomeFragment;
import com.qianfeng.aragon.giftsaying.primary_fragment.home_fragments.FirstFragment;

import dagger.Component;

/**
 * Created by aragon on 2016/11/7.
 */
@Component (modules = {AppModule.class})
public interface AppComponent {
    void inject(HomeFragment homeFragment);

    void inject(FirstFragment firstFragment);
}
