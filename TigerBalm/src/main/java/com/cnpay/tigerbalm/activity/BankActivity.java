package com.cnpay.tigerbalm.activity;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 手势滑动
 * 包            名:      com.cnpay.tigerbalm.activity
 * 类            名:      BankActivity
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 */
public class BankActivity extends TbActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;

    private View v;

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onStart() {
        super.onStart();
        if (mGestureDetector == null) {
            mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        }

        if (v != null) {
            v.setOnTouchListener(this);
        }
    }

    /**
     * 有Bug 暂时不用Bug ScrollView当做做页面上下滑动失效ACTION_UP
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    // 手指向右滑动时的最小速度
    private int verticalMinDistance = 200;
    // 手指向右滑动时的最小距离
    private int minVelocity = 150;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            // Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
            if (null != bankLeftActivity) {
                bankLeftActivity.bankLeft();
            }
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            // Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
            if (bankRightActivity == null) {
                anBack();
            } else {
                bankRightActivity.bankRight();
            }
        }
        return false;
    }

    /**
     * 默认返回-关闭activity
     */
    private void anBack() {
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private OnBankRightActivity bankRightActivity;
    private OnBankLeftActivity bankLeftActivity;

    /**
     * 滑动返回监听-向右手势
     */
    public interface OnBankRightActivity {
        //向右手势
        void bankRight();
    }

    public interface OnBankLeftActivity {
        //向左手势
        void bankLeft();
    }

    public void setBankActivity(OnBankRightActivity bankRightActivity) {
        this.bankRightActivity = bankRightActivity;
    }

    public void setBankLeftActivity(OnBankLeftActivity bankLeftActivity) {
        this.bankLeftActivity = bankLeftActivity;
    }
}
