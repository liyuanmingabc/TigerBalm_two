package com.cnpay.tigerbalm_20.glide;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.cnpay.tigerbalm.view.list.AutoLoadRecyclerView;
import com.cnpay.tigerbalm.view.list.listener.LoadMoreListener;
import com.cnpay.tigerbalm.view.list.listener.LoadResultCallBack;
import com.cnpay.tigerbalm_20.R;

import java.util.ArrayList;

/**
 * 包            名:      com.cnpay.tigerbalm_20.glide
 * 类            名:      GlideActivity
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/27
 */
public class GlideActivity extends AppCompatActivity implements LoadResultCallBack {
   /* private ImageView image1;
    private ImageView image2;*/

   /* private ListView listView;

    private GlideAdapter glideAdapter;*/

    private AutoLoadRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gilde);
//        listView = (ListView) findViewById(R.id.listView);
        recyclerView = (AutoLoadRecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


        /*image1 = (ImageView) findViewById(R.id.image_1);
        image2 = (ImageView) findViewById(R.id.image_2);
        Glide.with(this)
                .load("http://img02.tooopen.com/images/20160408/tooopen_sy_158653365484.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image1);
        Glide.with(this)
                .load("http://img02.tooopen.com/images/20160408/tooopen_sy_158653365484.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image2);*/
       /* glideAdapter = new GlideAdapter(this, getData());
        listView.setAdapter(glideAdapter);*/


        /**下拉*/
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                logAdapter.loadFirst();
            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(GlideActivity.this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnPauseListenerParams(false, true);
        /**加载更多*/
        recyclerView.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                logAdapter.loadNextPage();
            }
        });
        logAdapter = new RecyclerAdapter(recyclerView, this);
        recyclerView.setAdapter(logAdapter);
        logAdapter.loadFirst();
    }


    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("http://7xlwmc.com1.z0.glb.clouddn.com/" +
                    String.format("SAMPLE_IMG_%03d.jpg", i + 1));
        }
        return list;
    }

    @Override
    public void onSuccess(int result, Object object) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError(int code, String msg) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
