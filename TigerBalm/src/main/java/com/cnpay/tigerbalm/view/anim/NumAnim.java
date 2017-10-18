package com.cnpay.tigerbalm.view.anim;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

/**
 * textview 滚动加载数字
 * 包   名:     com.cnpay.tigerbalm.view.anim
 * 类   名:     NumAnim
 * 时   间:     2017/1/10 15:25
 * 作   者:     liyuanming
 */
public class NumAnim {

    /**
     * 每秒刷新多少次
     */
    private static final int COUNTPERS = 100;

    /**
     * @param textV 所需显示的View
     * @param num   显示数字
     */
    public static void startAnim(TextView textV, double num) {
        startAnim(textV, num, 500);
    }

    /**
     * @param textV 所需显示的View
     * @param num   显示数字
     * @param time  滚动动画持续时间
     */
    public static void startAnim(TextView textV, double num, long time) {
        if (num == 0) {
            textV.setText(numberFormat(num, 2));
            return;
        }

        Double[] nums = splitnum(num, (int) ((time / 1000f) * COUNTPERS));

        Counter counter = new Counter(textV, nums, time);

        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    /**
     * 滚动 double
     *
     * @param num   num
     * @param count count
     * @return Double[]
     */
    private static Double[] splitnum(double num, int count) {
        Random random = new Random();
        double numtemp = num;
        double sum = 0;
        LinkedList<Double> nums = new LinkedList<Double>();
        nums.add(0d);
        while (true) {
            double nextdouble = numberFormatdouble(
                    (random.nextDouble() * num * 2f) / (double) count,
                    2);
            System.out.println("next:" + nextdouble);
            if (numtemp - nextdouble >= 0) {
                sum = numberFormatdouble(sum + nextdouble, 2);
                nums.add(sum);
                numtemp -= nextdouble;
            } else {
                nums.add(num);
                return nums.toArray(new Double[0]);
            }
        }
    }

    /**
     * Counter
     */
    private static class Counter implements Runnable {

        /**
         * view
         */
        private final TextView view;
        /**
         * nums
         */
        private Double[] nums;
        /**
         * pertime
         */
        private long pertime;
        /**
         * i
         */
        private int i = 0;

        Counter(TextView view, Double[] nums, long time) {
            this.view = view;
            this.nums = nums;
            this.pertime = time / nums.length;
        }

        @Override
        public void run() {
            if (i > nums.length - 1) {
                view.removeCallbacks(Counter.this);
                return;
            }
            view.setText(numberFormat(nums[i++], 2));
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }

    /**
     * NumberFormat
     *
     * @param f f
     * @param m m
     * @return string
     */
    private static String numberFormat(double f, int m) {
        return String.format("%." + m + "f", f);
    }

    /**
     * NumberFormat
     *
     * @param f f
     * @param m m
     * @return double
     */
    private static double numberFormatdouble(double f, int m) {
        String strdouble = numberFormat(f, m);
        return Double.parseDouble(strdouble);
    }
}
