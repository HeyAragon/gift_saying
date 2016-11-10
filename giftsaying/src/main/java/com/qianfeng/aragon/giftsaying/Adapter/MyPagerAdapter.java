package com.qianfeng.aragon.giftsaying.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qianfeng.aragon.giftsaying.primary_fragment.home_fragments.FirstFragment;
import com.qianfeng.aragon.giftsaying.primary_fragment.home_fragments.SecondFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aragon on 2016/11/8.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> titles = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
        loadFragment();
//        this.notifyDataSetChanged();
    }

    private void loadFragment() {
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
