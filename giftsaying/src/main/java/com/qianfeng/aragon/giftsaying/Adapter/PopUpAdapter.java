package com.qianfeng.aragon.giftsaying.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.aragon.giftsaying.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aragon on 2016/11/8.
 */
public class PopUpAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<String> titles = new ArrayList<>();
    private Context context;

    @BindView(R.id.popup_adapter_view_txt)
    TextView textView;

    public PopUpAdapter(List<String> titles, Context context) {
        this.titles = titles;
        this.context = context;
        this.notifyDataSetChanged();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.popup_adapter_view, parent, false);
            ButterKnife.bind(this, view);
            view.setTag(textView);

//            TextView textView = (TextView)view.findViewById(R.id.popup_adapter_view_txt);
        } else {
            textView = (TextView) view.getTag();
        }

        textView.setText(titles.get(position));
        return view;
    }
}
