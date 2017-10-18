package com.cnpay.tigerbalm.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 */
public class BitmapUtil {
    /**
     * 设置指定文件路径
     *
     * @param parentPath parentPath
     * @param fileName   fileName
     * @return String
     */
    private static String getAccountFilePath(String parentPath, String fileName) {
        return parentPath + fileName + ".png";
    }

    /**
     * 设置随机文件路径
     *
     * @param path path
     * @return String
     */
    public static String getRandomFilePath(String path) {
        String fileName = UUID.randomUUID().toString();
        return getAccountFilePath(path, fileName);
    }

    /**
     * 判断文件是否存在
     *
     * @param str str
     * @return boolean
     */
    public static boolean fileIsExists(String str) {
        try {
            File f = new File(str);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据路径获取指定大小图片
     *
     * @param path   path
     * @param width  width
     * @param height height
     * @return Bitmap
     */
    public static Bitmap resizeImage(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 不加载bitmap到内存中
        BitmapFactory.decodeFile(path, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 1;
        if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0) {
            // Log.d(tag, "sampleSize = " + sampleSize);
            options.inSampleSize = (outWidth / width + outHeight / height) / 2;
        }
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 保存图片 返回设置的随机文件名
     *
     * @param savePath savePath
     * @param bitmap   bitmap
     * @return String
     */
    public static String setPicToSDCard(String savePath, Bitmap bitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return null;
        }
        String fileName = savePath + "/" + UUID.randomUUID() + ".png";
        compressBmpToFile(bitmap, fileName, 100);
        return fileName;
    }

    /**
     * 保存在SD卡中
     *
     * @param mBitmap     Bitmap
     * @param path        path
     * @param pictureName pictureName
     */
    public static void setPicToSDCard(Bitmap mBitmap, String path, String pictureName) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream fos = null;
        File file = new File(path);
        file.mkdirs(); // 创建文件夹
        String fileName = path + pictureName; //图片名字
        try {
            fos = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos); // 把数据写入文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    //关闭流
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩、保存图片，文件压缩，指定大小
     *
     * @param bmp    bmp
     * @param file   file
     * @param sizeKb sizeKb
     */
    private static void compressBmpToFile(Bitmap bmp, String file, int sizeKb) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80; // 个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > sizeKb) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件名图片
     *
     * @param path    path
     * @param picName picName
     */
    public static void deletePicFromSDCard(String path, String picName) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        try {
            File picFile = new File(path, picName);
            if (!picFile.exists() || picFile.isDirectory()) {
                return;
            }
            if (picFile.exists() && picFile.isFile()) {
                picFile.delete();
                Log.i("delete", "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据uri转换成path
     *
     * @param context context
     * @param uri     uri
     * @return String
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 根据图片路径获取图片旋转角度（一般三星手机获取出来的图片旋转角度会改变）
     *
     * @param path path
     * @return int
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 根据角度旋转图片
     *
     * @param angle  angle
     * @param bitmap bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 图像转数组
     *
     * @param bm Bitmap
     * @return byte
     */
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 数组转图像
     *
     * @param bts byte[]
     * @return Bitmap
     */
    public static Bitmap bytes2Bimap(byte[] bts) {
        if (bts.length != 0) {
            return BitmapFactory.decodeByteArray(bts, 0, bts.length);
        } else {
            return null;
        }
    }
}
