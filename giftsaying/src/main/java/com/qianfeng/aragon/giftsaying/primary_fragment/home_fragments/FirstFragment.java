package com.qianfeng.aragon.giftsaying.primary_fragment.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianfeng.aragon.giftsaying.Adapter.MyBaseAdapter;
import com.qianfeng.aragon.giftsaying.R;
import com.qianfeng.aragon.giftsaying.bean.SelectedBean;
import com.qianfeng.aragon.giftsaying.dagger.DaggerAppComponent;
import com.qianfeng.aragon.giftsaying.presenter.IPresenter;
import com.qianfeng.aragon.giftsaying.view.IFirstFragment;
import com.qianfeng.aragon.giftsaying.view.IHomeFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aragon on 2016/11/7.
 */
public class FirstFragment extends Fragment implements IFirstFragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "androidhy";
    private List<SelectedBean.DataBean.ItemsBean> itemsBeen = new ArrayList<>();


    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;

    @Inject
    IPresenter presenter;
    @BindView(R.id.first_fragment_list_view)
    ListView mListView;
    private MyBaseAdapter myBaseAdapter;

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    public static FirstFragment newInstance() {
        FirstFragment firstFragment = new FirstFragment();
        return firstFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        ButterKnife.bind(this,view);
        DaggerAppComponent.create().inject(this);
        DaggerAppComponent.create().inject(this);
        presenter.querySelectedBean();
        presenter.setView(this);
        myBaseAdapter = new MyBaseAdapter(getContext(), itemsBeen);
        mListView.setAdapter(myBaseAdapter);
    }


    @Override
    public void refreshSelectedList(List<SelectedBean.DataBean.ItemsBean> itemsBeen) {
        Log.i(TAG, "refreshSelectedList: "+itemsBeen.size());
        this.itemsBeen.addAll(itemsBeen);
        myBaseAdapter.notifyDataSetChanged();
    }
}
