package com.qianfeng.aragon.giftsaying.primary_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qianfeng.aragon.giftsaying.Adapter.MyPagerAdapter;
import com.qianfeng.aragon.giftsaying.Adapter.PopUpAdapter;
import com.qianfeng.aragon.giftsaying.R;
import com.qianfeng.aragon.giftsaying.dagger.DaggerAppComponent;
import com.qianfeng.aragon.giftsaying.presenter.IPresenter;
import com.qianfeng.aragon.giftsaying.view.IHomeFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements IHomeFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<String> titles = new ArrayList<>();

    private String mParam1;
    private String mParam2;

    @BindView(R.id.home_fragment_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.home_fragment_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.home_fragment_ib)
    ImageButton imageButton;
    @BindView(R.id.home_fragment_change_tab_txt)
    TextView changeTab;

    @Inject
    IPresenter presenter;
    private String TAG = "androidhy";
    private MyPagerAdapter myPagerAdapter;
    private LayoutInflater inflater;
    private View popupView;
    private GridView mGridView;

    private PopUpAdapter mPopUpAdapter;
    private PopupWindow mPopupWindow;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        homeFragment.setArguments(args);
        return homeFragment;
    }
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        DaggerAppComponent.create().inject(this);
        ButterKnife.bind(this,view);
        initView();
        presenter.queryTabBean();
        presenter.setView(this);
        setPopUpWindow();
        return view;
    }


    private void initView() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
        mTabLayout.setSelectedTabIndicatorColor(Color.RED);
        mTabLayout.setupWithViewPager(mViewPager);

        popupView = inflater.inflate(R.layout.popup_view, null);
        mGridView = (GridView)popupView.findViewById(R.id.popup_grid_view);
    }


    @Override
    public void refreshTab(List<String> list) {
        titles.addAll(list);
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), titles);
        mViewPager.setAdapter(myPagerAdapter);
//        myPagerAdapter.notifyDataSetChanged();
        mPopUpAdapter = new PopUpAdapter(titles,getContext());
        mGridView.setAdapter(mPopUpAdapter);

        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
    }

    private void setPopUpWindow() {
        mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAsDropDown(v);
                mTabLayout.setVisibility(View.GONE);
                changeTab.setVisibility(View.VISIBLE);
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if( mPopupWindow!=null && mPopupWindow.isShowing()) {
                            mPopupWindow.dismiss();
                            mPopupWindow = null;
                            mTabLayout.setVisibility(View.VISIBLE);
                            changeTab.setVisibility(View.INVISIBLE);

                        }
                        break;
                }

                return true;
            }
        });
    }
}
