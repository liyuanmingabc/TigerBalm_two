/*
package com.cnpay.tigerbalm_20;

import android.app.Activity;

import com.cnpay.tigerbalm.utils.GsonUtils;
import com.cnpay.tigerbalm.utils.StrUtil;
import com.cnpay.tigerbalm.utils.ToastUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;

//android选择器,地址时间...
//    compile 'cn.qqtheme.framework:AndroidPicker:latest.integration'

*/


/**
 * author zenghonghua
 * 包名: com.cnpay.tigerbalm.activity
 * 修改记录:
 * 公司: 深圳华夏通宝信息技术有限公司
 *//*

public class PickerDemo {
    private static PickerDemo picker;
    private Activity mContext;

    public static PickerDemo getInstance(Activity context) {
        if (null == picker) {
            picker = new PickerDemo(context);
        }
        return picker;
    }

    public PickerDemo(Activity context) {
        this.mContext = context;
    }


    private ArrayList<AddressPicker.Province> getData(String json) {
        Type type = new TypeToken<List<AddressPicker.Province>>() {
        }.getType();
        ArrayList<AddressPicker.Province> list = GsonUtils.createGson().fromJson(json, type);
        return list;
    }

    */
/**
     * 获取地址数据
     *//*

    public ArrayList<AddressPicker.Province> getDataList() {
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = readText(mContext, "city.json");
//        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));

        data.addAll(getData(json));
        return data;
    }
/**
	 * read file content
	 *
	 * @param context   the context
	 * @param assetPath the asset path
	 * @return String string
	 */
	/*public static String readText(Context context, String assetPath) {
		LogUtils.debug("read assets file as text: " + assetPath);
		try {
			return ConvertUtils.toString(context.getAssets().open(assetPath));
		} catch (Exception e) {
			LogUtils.error(e);
			return "";
		}
	}*/

/**
     * 地址
     *//*

    public void showAddress() {
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = StrUtil.readText(mContext, "city.json");
        data.addAll(getData(json));
//        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        AddressPicker picker = new AddressPicker(mContext, data);
        //picker.setSelectedItem("贵州", "贵阳", "花溪");     //可以设置默认选中的内容
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(String province, String city, String county) {
                ToastUtil.showToast(mContext, province + city + county);
            }
        });
        picker.show();
    }

    */
/**
     * 地址
     *//*

    public void showCityAddress(AddressPicker.OnAddressPickListener listener) {
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = StrUtil.readText(mContext, "city.json");
//        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        data.addAll(getData(json));
        AddressPicker picker = new AddressPicker(mContext, data);
        picker.setOnAddressPickListener(listener);
        picker.show();
    }

    //日期
    public void showDate() {
        DatePicker picker = new DatePicker(mContext, DatePicker.YEAR_MONTH_DAY);
        picker.setRange(2000, 2015);//年份范围
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                ToastUtil.showToast(mContext, year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    //时间
    public void showTime() {
        TimePicker picker = new TimePicker(mContext, TimePicker.HOUR_OF_DAY);
        picker.setTopLineVisible(false);
        picker.setSelectedItem(10, 10);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                ToastUtil.showToast(mContext, hour + ":" + minute);
            }
        });
        picker.show();
    }
}
*/
