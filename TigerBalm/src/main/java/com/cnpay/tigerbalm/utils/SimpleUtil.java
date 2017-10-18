package com.cnpay.tigerbalm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;


/**
 * SimpleUtil
 */
public class SimpleUtil {

    /**
     * 点击时间记录
     */
    private static long lastClickTime;

    /**
     * BUTTON 防爆点击 1s
     *
     * @param ms ms
     * @return boolean
     */
    public static synchronized boolean isFastClick(long ms) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < ms) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * 返回mac值
     *
     * @return String
     */
    public static String getMacAddress() {
        String result = "";
        String mac = "";
        result = callCmd("busybox ifconfig", "HWaddr");
        if (result == null) {
            return "网络出错，请检查网络";
        }
        if (result.length() > 0 && result.contains("HWaddr")) {
            mac = result.substring(result.indexOf("HWaddr") + 6, result.length() - 1);
            if (mac.length() > 1) {
                result = mac.toUpperCase();
            }
        }
        return result.trim();
    }

    /**
     * @param cmd    cmd
     * @param filter filter
     * @return String
     */
    private static String callCmd(String cmd, String filter) {
        String result = "";
        String line;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            //执行命令cmd，只取结果中含有filter的这一行
            while ((line = br.readLine()) != null && !line.contains(filter)) {
                //result += line;
                Log.i("test", "line: " + line);
            }
            result = line;
            Log.i("test", "result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取版本信息
     *
     * @param activity activity
     * @return 当前应用的版本号 PackageInfo
     */
    public static PackageInfo getVersion(Context activity) {
        try {
            PackageManager manager = activity.getPackageManager();
            return manager.getPackageInfo(activity.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 暂停时间
     *
     * @param time 时间
     */
    public static void toSeelp(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            TbLog.d("seelp-92");
        }
    }

    /**
     * 设置ListView的高度
     *
     * @param listView listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
        listView.setLayoutParams(params);
    }

    /**
     * 设置ListView的高度+ 手动增加高度
     *
     * @param listView listView
     * @param size     额外增加高度
     */
    public static void setListViewHeightBasedOnChildren(GridView listView, int size) {
        try {
            // 获取listview的adapter
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
            // listAdapter.getCount()小于等于8时计算两次高度相加
            for (int i = 0; i < listAdapter.getCount(); i = i + size) {
                // 获取listview的每一个item
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                // 获取item的高度和
                totalHeight += listItem.getMeasuredHeight();
            }

            // 获取listview的布局参数
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            // 设置高度
            params.height = totalHeight;
            // 设置margin
            ((ViewGroup.MarginLayoutParams) params).setMargins(0, 0, 0, 0);
            // 设置参数
            listView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址 4.0默认IPV6
     *
     * @return String
     */
    public static String getPsdnIpv6() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址 IPV4
     *
     * @return String
     */
    public static String getPsdnIpv4() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取sd卡路径 双sd卡时，获得的是外置sd卡
     *
     * @return String
     */
    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime(); // 返回与当前 Java 应用程序相关的运行时对象
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        try {
            Process p = run.exec(cmd); // 启动另一个进程来执行命令
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                TbLog.i(lineStr);
                if (lineStr.contains("sdcard") && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray.length >= 5) {
                        return strArray[1].replace("/.android_secure", "");
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                    TbLog.e("命令执行失败!");
                }
            }
        } catch (Exception e) {
            TbLog.e(e.toString());
            // return Environment.getExternalStorageDirectory().getPath();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (inBr != null) {
                    inBr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取扩展存储路径，TF卡、U盘
     *
     * @return String
     */
    public static String getExternalStorageDirectory() {
        String dir = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure")) {
                    continue;
                }
                if (line.contains("asec")) {
                    continue;
                }

                if (line.contains("fat")) {
                    String[] columns = line.split(" ");
                    if (columns.length > 1) {
                        dir = dir.concat(columns[1] + "\n");
                    }
                } else if (line.contains("fuse")) {
                    String[] columns = line.split(" ");
                    if (columns.length > 1) {
                        dir = dir.concat(columns[1] + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dir;
    }


    /**
     * 获取扩展存储路径，TF卡、U盘
     *
     * @return ArrayList<String>
     */
    public static ArrayList<String> getStorageDirectory() {
        ArrayList<String> dir = new ArrayList<>();
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure"))
                    continue;
                if (line.contains("asec"))
                    continue;

                if (line.contains("fat")) {
                    String[] columns = line.split(" ");
                    if (columns.length > 1) {
//                        dir = dir.concat(columns[1] + "\n");
                        dir.add(columns[1]);
                    }
                } else if (line.contains("fuse")) {
                    String[] columns = line.split(" ");
                    if (columns.length > 1) {
//                        dir = dir.concat(columns[1] + "\n");
                        dir.add(columns[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dir;
    }


    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp;
            for (String aFile : file) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + aFile);
                } else {
                    temp = new File(oldPath + File.separator + aFile);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()));
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) { //如果是子文件夹
                    copyFolder(oldPath + "/" + aFile, newPath + "/" + aFile);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }
    }


    /**
     * 显示或隐藏 (Invisible) View
     *
     * @param isHide false 显示 ，true 隐藏
     * @param view   view
     */
    public static void hideInvisible(boolean isHide, View view) {
        if (null != view) {
            if (isHide) {
                view.setVisibility(View.INVISIBLE);
                view.setOnClickListener(null);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 显示或隐藏 (Gone) View
     *
     * @param isHide false 显示 ，true 隐藏
     * @param view   view
     */
    public static void hideGone(boolean isHide, View view) {
        if (null != view) {
            if (isHide) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
