package com.cnpay.tigerbalm_20.glide;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cnpay.tigerbalm.utils.TbLog;
import com.cnpay.tigerbalm.view.list.listener.LoadFinishCallBack;
import com.cnpay.tigerbalm.view.list.listener.LoadResultCallBack;
import com.cnpay.tigerbalm_20.R;

import java.util.ArrayList;

/**
 * 包            名:      com.cnpay.tigerbalm_20.glide
 * 类            名:      RecyclerAdapter
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/28
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder> {

    private LoadFinishCallBack mLoadFinisCallBack;
    private LoadResultCallBack mLoadResultCallBack;
    private int page = 0;
    private int size = 20;
    private ArrayList<String> infos;

    private Context context;

    public RecyclerAdapter(LoadFinishCallBack mLoadFinisCallBack, LoadResultCallBack mLoadResultCallBack) {
        this.mLoadFinisCallBack = mLoadFinisCallBack;
        this.mLoadResultCallBack = mLoadResultCallBack;
        infos = new ArrayList<>();
    }

    @Override
    public RecyclerAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_glide, parent, false);
        return new Myholder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.Myholder holder, int position) {
        String path = infos.get(position);
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    public void loadFirst() {
        infos.clear();
        page = 0;
        loadData();
    }


    public void loadNextPage() {
        page++;
        loadData();
    }

    private void loadData() {

        new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                mLoadFinisCallBack.loadFinish();
                mLoadResultCallBack.onSuccess(100, null);
                notifyDataSetChanged();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                if (infos.size() >= 100) {
                    TbLog.e("没有更多数据");
                    return false;
                }
                int ss = size * (page + 1);
                int index = page * size;
                for (int i = index; i < ss; i++) {
                    infos.add("http://7xlwmc.com1.z0.glb.clouddn.com/" +
                            String.format("SAMPLE_IMG_%03d.jpg", i + 1));
                }
                TbLog.e("-----------" + infos.size());
                return true;
            }
        }.execute();
    }


    public class Myholder extends RecyclerView.ViewHolder {
        private ImageView image;

        public Myholder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
