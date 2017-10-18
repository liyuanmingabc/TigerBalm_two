//package com.cnpay.tigerbalm_20;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.cnpay.tigerbalm.activity.TbBaseActivityWithTop;
//import com.cnpay.tigerbalm.view.list.ProgressView;
//import com.cnpay.tigerbalm.view.list.TbRecyclerView;
//import com.cnpay.tigerbalm.view.list.listener.LoadMoreListener;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//
///**
// * 包   名:     com.cnpay.tigerbalm_20
// * 类   名:     TbBaseTopActivity
// * 时   间:     2016/12/9 11:32
// * 作   者:     liyuanming
// */
//public class TbBaseTopActivity extends TbBaseActivityWithTop {
//
//    @Bind(R.id.hao_recycleview)
//    TbRecyclerView haoRecycleview;
//    @Bind(R.id.swiperefresh)
//    SwipeRefreshLayout swiperefresh;
//
//
//    private MyAdapter mAdapter;
//    private ArrayList<String> listData = new ArrayList<>();
//    private int limit = 10;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_tb_rv;
//    }
//
//
//    @Override
//    public void initWidget(Bundle savedInstanceState) {
//        imageBackBar(0, "测试");
//
//        Context context = this;
//        TypedArray array = context.getTheme().obtainStyledAttributes(new int[]{
//                android.R.attr.colorBackground,
//        });
//        int backgroundColor = array.getColor(0, 0xFF00FF);
//        array.recycle();
//
//        swiperefresh.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
//        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        initData();
//                        //注意此处
//                        haoRecycleview.loadComplete();
//                        swiperefresh.setRefreshing(false);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }, 3000);
//
//            }
//        });
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        haoRecycleview.setLayoutManager(layoutManager);
//
//        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(this);
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff69b3e0);
//        haoRecycleview.setFootLoadingView(progressView);
//
//
//        /**
//         *
//         */
//        TextView textView = new TextView(this);
//        textView.setText("已经到底啦~");
//        textView.setTextColor(R.color.black_de);
//        haoRecycleview.setFootEndView(textView);
//
//
//        haoRecycleview.setFooterBackgroundResource(R.color.white);
//
//        haoRecycleview.setLoadMoreListener(new LoadMoreListener() {
//            @Override
//            public void loadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//
//                        if (listData.size() >= 3 * limit) {
//                            haoRecycleview.loadMoreEnd();
//                            return;
//                        }
//
//                        for (int i = 0; i < limit; i++) {
//                            listData.add(i + "");
//                        }
//                        mAdapter.notifyDataSetChanged();
//                        haoRecycleview.loadComplete();
//
//                    }
//                }, 2000);
//            }
//        });
//
//        initData();
//        mAdapter = new MyAdapter(this, listData);
//        haoRecycleview.setAdapter(mAdapter);
//
//
//        haoRecycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(TbBaseTopActivity.this, "click-----position" + i, Toast.LENGTH_SHORT).show();
//                Log.i(TbBaseTopActivity.class.getSimpleName(), "onItemClick: " + listData.size());
//                Log.i(TbBaseTopActivity.class.getSimpleName(), "s: " + i);
//            }
//        });
//
//
//        haoRecycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(TbBaseTopActivity.this, "long click------position" + i, Toast.LENGTH_SHORT).show();
//                Log.i(TbBaseTopActivity.class.getSimpleName(), "onItemLongClick: " + listData.size());
//                return false;
//            }
//        });
//
//    }
//
//    @Override
//    public void onWidgetClick(View v) {
//
//    }
//
//
//    private void initData() {
//        listData.clear();
//        for (int i = 0; i < limit; i++) {
//            listData.add(i + "");
//        }
//    }
//
//}
