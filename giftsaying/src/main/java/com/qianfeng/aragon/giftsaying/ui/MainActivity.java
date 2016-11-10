package com.qianfeng.aragon.giftsaying.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.qianfeng.aragon.giftsaying.R;
import com.qianfeng.aragon.giftsaying.primary_fragment.ClassifyFragment;
import com.qianfeng.aragon.giftsaying.primary_fragment.HomeFragment;
import com.qianfeng.aragon.giftsaying.primary_fragment.HotFragment;
import com.qianfeng.aragon.giftsaying.primary_fragment.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private HotFragment mHotFragment;
    private HomeFragment mHomeFragment;
    private ClassifyFragment mClassifyFragment;
    private UserFragment mUserFragment;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initView();

    }

    private void initView() {
        initFragment();
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        check(mHomeFragment);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_home_radio_btn:
                        check(mHomeFragment);
                        break;
                    case R.id.main_hot_radio_btn:
                        check(mHotFragment);
                        break;
                    case R.id.main_classify_radio_btn:
                        check(mClassifyFragment);
                        break;
                    case R.id.main_user_radio_btn:
                        check(mUserFragment);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        mHotFragment = HotFragment.newInstance();
        mHomeFragment = HomeFragment.newInstance();
        mClassifyFragment = ClassifyFragment.newInstance();
        mUserFragment = UserFragment.newInstance();
    }


    public void check(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.main_fragment_container, fragment);
//            transaction.show(mCurrentFragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }

}
