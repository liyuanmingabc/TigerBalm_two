package com.cnpay.tigerbalm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * 包            名:      com.cnpay.tigerbalm.utils
 * 类            名:      FileUtil
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 */
public class FileUtil {

    /**
     * 默认数据
     *
     * @param filePath
     * @return
     */
    public static String readJSONFile(String filePath) {
        String str = "";
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader read = new InputStreamReader(
                        fileInputStream, encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    str += lineTxt;
                }
                fileInputStream.close();
                read.close();
                bufferedReader.close();
            } else {
                TbLog.i("找不到指定的文件");
            }
        } catch (Exception e) {
            TbLog.i("读取文件内容出错");
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 使用文件通道的方式复制文件
     *
     * @param s 源文件
     * @param t 复制到的新文件
     */

    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fo != null) {
                    fo.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 保存最新的文件数据
     *
     * @param filePath
     * @param data
     */
    public static void saveFile(String filePath, String dir, String data) {
        try {
            File file = new File(filePath);
            File ff = new File(dir);
            if (!ff.exists()) {
                ff.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            TbLog.i("保存文件出错");
            e.printStackTrace();
        }
    }
}
