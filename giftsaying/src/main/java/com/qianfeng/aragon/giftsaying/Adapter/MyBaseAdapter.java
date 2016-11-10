package com.qianfeng.aragon.giftsaying.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.aragon.giftsaying.R;
import com.qianfeng.aragon.giftsaying.bean.SelectedBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aragon on 2016/11/10.
 */
public class MyBaseAdapter extends BaseAdapter {

    private static final String TAG = "androidhy";
    private final LayoutInflater inflater;
    private Context context;
    private List<SelectedBean.DataBean.ItemsBean> itemsBeen = new ArrayList<>();
    private static final int TYPE_AD = 0;
    private static final int TYPE_OTHERS = 1;


    public MyBaseAdapter(Context context, List<SelectedBean.DataBean.ItemsBean> itemsBeen) {
        this.context = context;
        this.itemsBeen = itemsBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override

    public int getCount() {
        return itemsBeen == null ? 0 : itemsBeen.size();
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
    public int getItemViewType(int position) {
        if (itemsBeen.get(position).getType().equals("ad")) {
            return TYPE_AD;
        } else {
            return TYPE_OTHERS;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;
        int itemViewType = getItemViewType(position);
        if (view == null) {
            switch (itemViewType) {
                case TYPE_AD:
                    view = inflater.inflate(R.layout.ad_view, parent, false);
                    viewHolder2 = new ViewHolder2(view);
                    break;
                case TYPE_OTHERS:
                    view = inflater.inflate(R.layout.list_view_adapter_view, parent, false);
                    viewHolder = new ViewHolder(view);
                    break;
            }

        } else {
            switch (itemViewType) {
                case TYPE_AD:
                    viewHolder2 = (ViewHolder2) view.getTag();
                    break;
                case TYPE_OTHERS:
                    viewHolder = (ViewHolder) view.getTag();
                    break;
            }

        }
        switch (itemViewType) {
            case TYPE_AD:
                Picasso.with(context).load(itemsBeen.get(position).getImage_url()).into(viewHolder2.imageView);
                break;
            case TYPE_OTHERS:
                viewHolder.editorIntroduction.setText(itemsBeen.get(position).getAuthor().getIntroduction());
                viewHolder.editorName.setText(itemsBeen.get(position).getAuthor().getNickname());
                viewHolder.articleTitle.setText(itemsBeen.get(position).getTitle());
                viewHolder.articleIntroduction.setText(itemsBeen.get(position).getIntroduction());
                if (itemsBeen.get(position).getColumn()==null) {
                    viewHolder.fromWhere.setText("");
                } else {
                    viewHolder.fromWhere.setText(itemsBeen.get(position).getColumn().getTitle());
                }
                viewHolder.collectCount.setText(itemsBeen.get(position).getLikes_count()+"");

                Picasso.with(context).load(itemsBeen.get(position).getAuthor().getAvatar_url()).into(viewHolder.editorIcon);
                Picasso.with(context).load(itemsBeen.get(position).getCover_image_url()).into(viewHolder.bitImage);
                break;
        }
        return view;
    }

    class ViewHolder {
        @BindView(R.id.list_view_editor_icon)
        ImageView editorIcon;
        @BindView(R.id.list_view_editor_name)
        TextView editorName;
        @BindView(R.id.list_view_editor_introduction)
        TextView editorIntroduction;
        @BindView(R.id.list_view_big_image)
        ImageView bitImage;
        @BindView(R.id.list_view_article_title)
        TextView articleTitle;
        @BindView(R.id.list_view_article_introduction)
        TextView articleIntroduction;
        @BindView(R.id.list_view_from_where)
        TextView fromWhere;
        @BindView(R.id.list_view_collect_count)
        TextView collectCount;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 {
        ImageView imageView;

        public ViewHolder2(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.ad_image_view);
        }
    }
}
