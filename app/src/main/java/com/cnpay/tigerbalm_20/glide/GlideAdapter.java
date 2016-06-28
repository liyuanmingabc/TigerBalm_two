package com.cnpay.tigerbalm_20.glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cnpay.tigerbalm_20.R;

import java.util.ArrayList;

/**
 * 包            名:      com.cnpay.tigerbalm_20.glide
 * 类            名:      GlideAdapter
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/28
 */
public class GlideAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> list;

    public GlideAdapter(Context mContext, ArrayList<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if (null == convertView) {
            view = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_glide, parent, false);
            view.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(list.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(view.image);
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
    }
}
