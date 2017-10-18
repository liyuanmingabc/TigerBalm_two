package com.cnpay.tigerbalm.net;

import android.content.Context;
import android.text.TextUtils;

import com.cnpay.tigerbalm.utils.TbLog;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 包   名:     com.mh.patentnanny.net
 * 类   名:     OkHttpNetUtil
 * 版   本:          V1.0
 * 时   间:     2016/10/16 17:53
 * 作   者:     on
 * https://github.com/liyuanmingabc/okhttputils
 */
public class OkHttpNetUtil {

    private static OkHttpNetUtil okHttpNetUtil;

    private Context mContext;

//    private OkHttpUtils okHttpUtils;

    /**
     * 双重锁定，使用同一个 DatePicker 实例
     */
    public static OkHttpNetUtil getInstance(Context mContext) {
        if (okHttpNetUtil == null) {
            synchronized (OkHttpNetUtil.class) {
                if (okHttpNetUtil == null) {
                    okHttpNetUtil = new OkHttpNetUtil(mContext);
                }
            }
        }
        return okHttpNetUtil;
    }

    private OkHttpNetUtil(Context mContext) {
        this.mContext = mContext;
//        if (null == okHttpUtils) {
//            okHttpUtils = OkHttpUtils.getInstance();
//        }
    }

  /*  *//*
     * 同步请求-
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     *//*
    public SyncMsg postSync(String url, Map<String, String> params) throws Exception {
        if (null == params) {
            params = new HashMap<>();
        }
        SyncMsg syncMsg;
        String token = PnApplication.getInstance().getToken();
        params.put("token", token == null ? "" : token);
        TbLog.i("--" + url + "----请求参数---" + params.toString());
        PostFormBuilder post = OkHttpUtils.post().url(Config.URL_COMM + url).params(params);
        RequestCall build = post.build();
        Response response = build.execute();
        if (response.isSuccessful()) {
            JSONObject json = new JSONObject(response.body().string());
            TbLog.i("--" + url + "----同步返回的数据----" + json.toString());
            if (Config.TRUE.equals(json.getString(Config.SUCCESS))) {
//                listener.onSuccess(id, json);
                syncMsg = new SyncMsg(true, json);
            } else {
                String error = json.getString(Config.ERROR);

                TbLog.e("--" + url + "---" + error);
                syncMsg = new SyncMsg(false, conversionException(new Exception(error)));
//                syncMsg = new SyncMsg(false,new ArrayList<>());
                jumpSignIn(error);
            }
        } else {
            syncMsg = new SyncMsg(false, conversionException(new Exception(response.message())));
            TbLog.e("--" + url + "---" + response.message());
        }
        return syncMsg;
    }

    *//*
     * post
     *
     * @param url      地址
     * @param params   参数
     * @param listener 监听
     *//*
    public void post(final String url, Map<String, String> params, final HttpListener listener) {
        if (null == params) {
            params = new HashMap<>();
        }
        String token = PnApplication.getInstance().getToken();
        params.put("token", token == null ? "" : token);
//        params.put("token", "8jIsFa9MPFyvmhjZRR3a9pUX5zP96O2oJrXCleHCiqHk5s");
        TbLog.i("--" + url + "---请求参数---" + params.toString());
        PostFormBuilder post = OkHttpUtils.post().url(Config.URL_COMM + url).params(params);
        RequestCall build = post.build();
        build.execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                listener.onStart(id);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                TbLog.e("---" + url + "--请求失败------ " + e.getMessage());
                listener.onFailed(id, conversionException(e));
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    TbLog.i("--[ " + url + " ] 返回的网络数据----" + response);
                    JSONObject json = new JSONObject(response);
                    if (Config.TRUE.equals(json.getString(Config.SUCCESS))) {
                        listener.onSuccess(id, json);
                    } else {
                        String error = json.getString(Config.ERROR);
                        listener.onFailed(id, conversionException(new Exception(error)));
                        jumpSignIn(error);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailed(id, e.getMessage());
                }
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                listener.onFinish(id);
            }
        });
    }


    *//*
     * post json
     *
     * @param url      地址
     * @param params   参数
     * @param listener 监听
     *//*
    public void postJson(final String url, Map<String, String> params, final HttpListener listener) {
        if (null == params) {
            params = new HashMap<>();
        }
        String token = PnApplication.getInstance().getToken();
        TbLog.i("---请求参数---" + params.toString());
        params.put("token", token == null ? "" : token);
        //使用Request的post方法来提交请求体RequestBody
        PostStringBuilder post = OkHttpUtils.postString().url(Config.URL_COMM + url)
                .content(GsonUtils.createGson().toJson(params)).mediaType(MediaType.parse("application/json; charset=utf-8"));
        RequestCall build = post.build();
        build.execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                listener.onStart(id);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                TbLog.e("---" + url + "--请求失败------ " + e.getMessage());
                listener.onFailed(id, conversionException(e));
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    TbLog.i("--返回的网络数据----" + response);
                    JSONObject json = new JSONObject(response);
                    if (Config.TRUE.equals(json.getString(Config.SUCCESS))) {
                        listener.onSuccess(id, json);
                    } else {
                        String error = json.getString(Config.ERROR);
                        listener.onFailed(id, conversionException(new Exception(error)));
                        jumpSignIn(error);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailed(id, e.getMessage());
                }
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                listener.onFinish(id);
            }
        });
    }


    *//*
     * token 失效 跳转至登录页面
     *
     * @param error 某条固定的错误
     *//*
    private void jumpSignIn(String error) {
        if (error.contains("为了您的账号安全，请重新登录")) {
            //为了您的账号安全，请重新登录
            AppManager.finishAllActivity();
            //跳转到登录
            TbLog.e("token失效--跳转至登录-清楚所有activity");
            mContext.startActivity(new Intent(mContext, SignInActivity.class));

        }
    }*/

    /**
     * 转换错误信息提示
     *
     * @param e 错误信息
     * @return 错误信息转换后
     */
    private String conversionException(Exception e) {
        String msg = "请求失败";
        if (null != e) {
            TbLog.e("--请求失败------ " + e.getMessage());
            if (e instanceof ConnectException) {
                msg = HttpPrompt.CONNECTEXCEPTION;
            } else if (e instanceof ConnectTimeoutException) {
                msg = HttpPrompt.CONNECTEXCEPTION;
            } else if (e instanceof UnknownHostException) {
                msg = HttpPrompt.UNKNOWNHOSTEXCEPTION;
            } else if (e instanceof SocketException) {
                msg = HttpPrompt.SOCKETEXCEPTION;
            } else if (e instanceof SocketTimeoutException) {
                msg = HttpPrompt.SOCKETTIMEOUTEXCEPTION;
            } else if (e instanceof NullPointerException) {
                msg = HttpPrompt.NULLPOINTEREXCEPTION;
            } else {
                if (TextUtils.isEmpty(e.getMessage())) {
                    msg = HttpPrompt.NULLMESSAGEEXCEPTION;
                } else {
                    msg = e.getMessage();
                }
            }
            if (msg.equals("The target server failed to respond")) {
                msg = HttpPrompt.SOCKETEXCEPTION;
            } else if (msg.contains("Request time out")) {
                msg = HttpPrompt.SOCKETEXCEPTION;
            } else if (msg.startsWith("request failed , reponse's code is : 500")) {
                msg = HttpPrompt.NULLPOINTEREXCEPTION;
            } else if (msg.contains("request failed , reponse's code is : 404")) {
                msg = HttpPrompt.NOT_FOUND_EXCEPTION;
            }
        }
        TbLog.e("-----------" + msg);
        return msg;
    }
}
