package com.cnpay.tigerbalm.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnpay.tigerbalm.R;


/**
 * 默认 头部 布局
 */
public class TbBaseTopViewHolder {

    /**
     * 左
     */
    public ImageView ivLeft;
    /**
     * 左
     */
    public TextView tvLeft;
    /**
     * 返回
     */
    public LinearLayout llBack;
    /**
     * 标题
     */
    public TextView tvTitle;
    /**
     * 右
     */
    public ImageView ivRight;
    /**
     * 右
     */
    public TextView tvRight;
    /**
     * 右
     */
    public LinearLayout llRight;
    /**
     * 总
     */
    public LinearLayout llTitleTobar;

    public TbBaseTopViewHolder(View view) {
//        ButterKnife.bind(this, view);
        ivLeft = (ImageView) view.findViewById(R.id.iv_left);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        llBack = (LinearLayout) view.findViewById(R.id.ll_back);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);

        ivRight = (ImageView) view.findViewById(R.id.iv_right);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        llRight = (LinearLayout) view.findViewById(R.id.ll_right);


        llTitleTobar = (LinearLayout) view.findViewById(R.id.ll_title_Tobar);
    }
}
