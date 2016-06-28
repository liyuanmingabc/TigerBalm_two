package com.cnpay.tigerbalm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * 包            名:      com.cnpay.tigerbalm.utils
 * 类            名:      WifiUtil
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 */
public class WifiUtil {

    /**
     *
     * 描述：打开wifi.
     * @param context
     * @param enabled
     */
    public static void setWifiEnabled(Context context,boolean enabled){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(enabled){
            wifiManager.setWifiEnabled(true);
        }else{
            wifiManager.setWifiEnabled(false);
        }
    }

    /**
     *
     * 描述：是否有网络连接.
     * @param context
     */
    public static boolean isConnectivity(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || telephonyManager
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断当前网络是否是wifi网络.
     *
     * @param context the context
     * @return boolean
     */
    public static boolean isWifiConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     *
     * 描述：得到所有的WiFi列表.
     * @param context
     */
    public static List<ScanResult> getScanResults(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> list = null;
        //开始扫描WiFi
        boolean f = wifiManager.startScan();
        if(!f){
            getScanResults(context);
        }else{
            // 得到扫描结果
            list = wifiManager.getScanResults();
        }

        return list;
    }

    /**
     *
     * 描述：根据SSID过滤扫描结果.
     * @param context
     * @param bssid
     * @return ScanResult
     */
    public static ScanResult getScanResultsByBSSID(Context context,String bssid) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        ScanResult scanResult = null;
        //开始扫描WiFi
        boolean f = wifiManager.startScan();
        if(!f){
            getScanResultsByBSSID(context,bssid);
        }
        // 得到扫描结果
        List<ScanResult> list = wifiManager.getScanResults();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                // 得到扫描结果
                scanResult = list.get(i);
                if (scanResult.BSSID.equals(bssid)) {
                    break;
                }
            }
        }
        return scanResult;
    }

    /**
     *
     * 描述：获取连接的WIFI信息.
     * @param context
     * @return WifiInfo
     */
    public static WifiInfo getConnectionInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo;
    }

}
