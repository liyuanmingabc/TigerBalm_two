package com.cnpay.tigerbalm.activity;

import android.view.View;
import android.view.ViewGroup;

import com.cnpay.tigerbalm.R;
import com.cnpay.tigerbalm.utils.SimpleUtil;
import com.cnpay.tigerbalm.utils.StrUtil;
import com.cnpay.tigerbalm.utils.TbLog;

import static com.cnpay.tigerbalm.utils.SimpleUtil.hideInvisible;

/**
 * 包   名:     com.cnpay.tigerbalm.activity
 * 类   名:     TbBaseActivityWithTop
 * 时   间:     2016/12/2 14:47
 * 作   者:     liyuanming
 */
public abstract class TbBaseActivityWithTop extends TbBaseActivity implements TbIBaseViewListener {
    protected ViewGroup mTopContentView;//头部主布局
    protected TbBaseTopViewHolder mTopViewHolder;//头部布局参数

    protected View.OnClickListener mITopLeftListener;//头部左操作监听
    protected View.OnClickListener mITopRight1Listener;//头部右1操作监听


    /**
     * 初始化头部布局
     */
    private void initTopBar() {

        int topBarId = getTopBarId();
        TbLog.e("=============topBarId = " + topBarId);

        mTopContentView = (ViewGroup) mInflater.inflate(topBarId, null);

        mTopViewHolder = new TbBaseTopViewHolder(mTopContentView);
        //获取头部相关监听
        if (mTopViewHolder.llBack != null) {
            mTopViewHolder.llBack.setOnClickListener(mTopBarClickListener);
        }
        if (mTopViewHolder.llRight != null) {
            mTopViewHolder.llRight.setOnClickListener(mTopBarClickListener);
            mTopViewHolder.llRight.setVisibility(View.INVISIBLE);
        }
        setContentView(mTopContentView);
    }

    /**
     * 获取头部布局
     * 默认 头部标题
     *
     * @return 选择的头部布局id
     */
    protected int getTopBarId() {
        return R.layout.activity_titlebar_main;
    }

    /**
     * 初始化主布局
     */
    protected void initContentView() {
        initTopBar();
        if (getLayoutId() != 0) {
            TbLog.e("============getLayoutId() = " + getLayoutId());
            mInflater.inflate(getLayoutId(), mTopContentView);
        }
    }

    /**
     * 只显示 标题
     *
     * @param title 标题
     */
    public void noBackBar(String title) {
        if (null != mTopViewHolder) {
            SimpleUtil.hideGone(false, mTopViewHolder.llTitleTobar);
            hideInvisible(true, mTopViewHolder.llBack);
            if (null != mTopViewHolder.tvTitle) {
                mTopViewHolder.tvTitle.setText(title);
            }
            hideInvisible(true, mTopViewHolder.llRight);
        }
    }

    /**
     * 显示 返回 图片
     *
     * @param icon  图片
     * @param title 标题
     */
    public void imageBackBar(int icon, String title) {
        if (null != mTopViewHolder) {
            SimpleUtil.hideGone(false, mTopViewHolder.llTitleTobar);
            SimpleUtil.hideInvisible(false, mTopViewHolder.llBack);
            if (null != mTopViewHolder.ivLeft && icon != 0)
                mTopViewHolder.ivLeft.setImageResource(icon);
            if (null != mTopViewHolder.tvTitle) {
                mTopViewHolder.tvTitle.setText(title);
            }
            hideInvisible(true, mTopViewHolder.llRight);
        }
    }

    /**
     * 使用默认 返回 图片
     *
     * @param title 标题
     */
    public void backBar(String title) {
        imageBackBar(0, title);
    }


    /**
     * 显示
     *
     * @param text  左侧文字
     * @param title 标题文字
     */
    public void textBackBar(String text, String title) {
        if (null != mTopViewHolder) {
            SimpleUtil.hideGone(false, mTopViewHolder.llTitleTobar);
            hideInvisible(false, mTopViewHolder.llBack);
            hideInvisible(false, mTopViewHolder.ivLeft);
            if (null != mTopViewHolder.tvLeft) {
                hideInvisible(false, mTopViewHolder.tvLeft);
                mTopViewHolder.tvLeft.setText(StrUtil.parseEmpty(text));
            }
            if (null != mTopViewHolder.tvTitle) {
                mTopViewHolder.tvTitle.setText(title);
            }
            hideInvisible(true, mTopViewHolder.llRight);
        }
    }

    /**
     * 隐藏bar
     */
    public void hideBar() {
        if (null != mTopViewHolder) {
            SimpleUtil.hideGone(true, mTopViewHolder.llTitleTobar);
        }
    }


    /**
     * 设置返回图片
     *
     * @param icon 图片
     */
    public void setBackImage(int icon) {
        if (null != mTopViewHolder) {
            SimpleUtil.hideGone(false, mTopViewHolder.llTitleTobar);
            if (null != mTopViewHolder.ivLeft && icon != 0)
                mTopViewHolder.ivLeft.setImageResource(icon);
        }
    }


    /**
     * topbar左布局相关监听
     * back
     */
    protected void setTopLeftListener(View.OnClickListener listener) {
        this.mITopLeftListener = listener;
    }

    /**
     * topbar右1相关监听
     */
    protected void setTopRightListener(View.OnClickListener listener) {
        this.mITopRight1Listener = listener;
    }

    /**
     * 头部点击操作监听
     */
    private View.OnClickListener mTopBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //防止控件多次点击
//            if (SimpleUtil.isFastClick(200)) {
//                return;
//            }
            int i = v.getId();
            if (i == R.id.ll_back) {
                if (mITopLeftListener != null) {
                    mITopLeftListener.onClick(v);
                }
            } else if (i == R.id.ll_right) {
                if (mITopRight1Listener != null) {
                    mITopRight1Listener.onClick(v);
                }

            }
        }
    };
}
