package com.qianfeng.aragon.giftsaying.primary_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.aragon.giftsaying.R;

/**
 * Created by aragon on 2016/11/7.
 */
public class ClassifyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ClassifyFragment() {

    }

    public static ClassifyFragment newInstance(String param1, String param2) {
        ClassifyFragment classifyFragment = new ClassifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1,param1);
        bundle.putString(ARG_PARAM2, param2);
        classifyFragment.setArguments(bundle);
        return classifyFragment;
    }

    public static ClassifyFragment newInstance() {
        ClassifyFragment classifyFragment = new ClassifyFragment();
        return classifyFragment;
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
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        return view;
    }

}
