//package com.cnpay.tigerbalm_20;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.cnpay.tigerbalm.view.list.ProgressView;
//import com.cnpay.tigerbalm.view.list.TbRecyclerView;
//import com.cnpay.tigerbalm.view.list.listener.LoadMoreListener;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//
///**
// * 包   名:     com.cnpay.tigerbalm_20
// * 类   名:     TbRvActivity
// * 时   间:     2016/12/5 15:04
// * 作   者:     liyuanming
// */
//public class TbRvActivity extends AppCompatActivity {
//
//    @Bind(R.id.hao_recycleview)
//    TbRecyclerView haoRecycleview;
//    @Bind(R.id.swiperefresh)
//    SwipeRefreshLayout swiperefresh;
//    private MyAdapter mAdapter;
//    private ArrayList<String> listData = new ArrayList<>();
//    private int limit = 10;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.activity_tb_rv);
//        ButterKnife.bind(this);
//
//
////        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
////                R.color.textBlueDark);
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
//        mAdapter = new MyAdapter(this,listData);
//        haoRecycleview.setAdapter(mAdapter);
//
//
//        haoRecycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(TbRvActivity.this, "click-----position" + i, Toast.LENGTH_SHORT).show();
//                Log.i(TbRvActivity.class.getSimpleName(), "onItemClick: " + listData.size());
//                Log.i(TbRvActivity.class.getSimpleName(), "s: " + i);
//            }
//        });
//
//
//        haoRecycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(TbRvActivity.this, "long click------position" + i, Toast.LENGTH_SHORT).show();
//                Log.i(TbRvActivity.class.getSimpleName(), "onItemLongClick: " + listData.size());
//                return false;
//            }
//        });
//    }
//
//    private void initData() {
//        listData.clear();
//        for (int i = 0; i < limit; i++) {
//            listData.add(i + "");
//        }
//    }
//}
