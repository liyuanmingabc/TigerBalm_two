package com.cnpay.tigerbalm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cnpay.tigerbalm.R;

import java.util.Arrays;

/**
 * 圆形和 方形圆角图片--
 * 包   名:     com.cnpay.tigerbalm.view
 * 类   名:     ShapeImageView
 * 版权所有:     版权所有(C)2010-2016
 * 版   本:          V1.0
 * 时   间:     2016/7/1 11:01
 * 作   者:     liyuanming
 */
public class ShapeImageView extends ImageView {

    /*圆形
    <com.cnpay.tigerbalm.view.ShapeImageView
    android:layout_width="150dp"
    android:layout_height="150dp"
    android:src="@drawable/yu"
    app:shape_mode="circle"
    app:frame_width="3dp"
    app:frame_color="@android:color/holo_red_light"/>

    圆角
    <com.cnpay.tigerbalm.view.ShapeImageView
    android:layout_width="150dp"
    android:layout_height="150dp"
    android:src="@drawable/yu"
    app:shape_mode="shape"
    app:frame_width="3dp"
    app:frame_color="@android:color/holo_red_light"
    app:round_radius="10dp"/>*/


    private static final int SHAPE = 1;//圆角

    private static final int CIRCLE = 2;//圆

    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG |
            Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
            Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG;


    private int mShapeMode = 0;//图片模式

    private int mFrameColor = 0x26000000;//边框颜色

    private float mRadius = 0;//半径

    private float mFrameWidth = 0;//边框宽度


    private Paint mPaint;//主画笔

    private Paint mFramePaint;//边框画笔

    private Shape mShape;

    private Shape mFrameShape;

    private Bitmap mFrameBitmap;


    public ShapeImageView(Context context) {
        this(context, null);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        //硬件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_HARDWARE, null);
        }

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShapeImageView);

            mShapeMode = a.getInt(R.styleable.ShapeImageView_shape_mode, 0);
            mRadius = a.getDimension(R.styleable.ShapeImageView_round_radius, 0);
            mFrameWidth = a.getDimension(R.styleable.ShapeImageView_frame_width, 0);
            mFrameColor = a.getColor(R.styleable.ShapeImageView_frame_color, mFrameColor);

            a.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setColor(mFrameColor);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (changed) {
            if (mShapeMode == CIRCLE) {
                mRadius = (float)Math.min(getWidth(), getHeight()) / 2;
            }
        }

        if (mShape == null) {
            float[] radius = new float[8];
            Arrays.fill(radius, mRadius);
            mShape = new RoundRectShape(radius, null, null);
            mFrameShape = new RoundRectShape(radius, null, null);
        }

        mShape.resize(getWidth(), getHeight());

        if (mFrameWidth > 0) {
            mFrameBitmap = setupFrameBitmap(getWidth(), getHeight());
            mFrameShape.resize(getWidth() - mFrameWidth * 2,
                    getHeight() - mFrameWidth * 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mFrameWidth > 0 && mFrameShape != null && mFrameBitmap != null) {
            int i = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, LAYER_FLAGS);
            canvas.drawBitmap(mFrameBitmap, 0, 0, mFramePaint);
            canvas.translate(mFrameWidth, mFrameWidth);
            mFramePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mFrameShape.draw(canvas, mFramePaint);
            mFramePaint.setXfermode(null);
            canvas.restoreToCount(i);
        }

        switch (mShapeMode) {
            case SHAPE:
            case CIRCLE:
                if (mShape != null) {
                    mShape.draw(canvas, mPaint);
                }
                break;
        }
    }

    private Bitmap setupFrameBitmap(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(mFrameColor);
        c.drawRect(new RectF(0, 0, w, h), p);
        return bm;
    }
}
