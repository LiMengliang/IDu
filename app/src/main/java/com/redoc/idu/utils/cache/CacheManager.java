package com.redoc.idu.utils.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.redoc.idu.utils.image.BitmapUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * A local cache manager.
 * Created by limen on 2017/4/21.
 */

public class CacheManager {

    private File mCacheDirectory;
    private final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private final int IO_BUFFER_SIZE = 1024 * 1024;
    private int mApplicationVersion;

    /**
     * Construct a CacheManager.
     * @param context The application context.
     * @param subFolder The sub folder to keep the cache.
     */
    public CacheManager(Context context, String subFolder) {

        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            mApplicationVersion = packageInfo.versionCode;
            mCacheDirectory = getDiskCacheDir(context, subFolder);
            if(!mCacheDirectory.exists()) {
                mCacheDirectory.mkdir();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read cached bytes.
     * @param url The url of the cache.
     * @return Bytes cached in file.
     * @throws IOException IO exception.
     */
    public byte[] readBytes(String url) throws IOException {
        byte[] bytes = new byte[0];
        byte[] b = new byte[1024];
        DiskLruCache mDiskLruCache = DiskLruCache.open(mCacheDirectory, 1, mApplicationVersion, DISK_CACHE_SIZE);
        String key = CacheUtils.hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
        if(snapShot != null) {
            InputStream fileInputStream = snapShot.getInputStream(0);

            int bytesRead = fileInputStream.read(b);
            while(bytesRead != -1) {
                bytes = CacheUtils.append(bytes, b, bytesRead);
                bytesRead = fileInputStream.read(b);
            }
        }
        mDiskLruCache.close();
        return bytes;
    }

    /**
     * Read string from cached file.
     * @param url The url.
     * @return String value.
     */
    public String readString(String url) {
        String result = "";
        try {
            result = new String(readBytes(url), "GB2312");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Read bitmap from cache.
     * @param url The url.
     * @return Bitmap cached in file.
     */
    public Bitmap readBitmap(String url) {
        Bitmap result = null;
        try {
            DiskLruCache mDiskLruCache = DiskLruCache.open(mCacheDirectory, 1, 1, DISK_CACHE_SIZE);
            String key = CacheUtils.hashKeyFromUrl(url);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                result = BitmapFactory.decodeStream(is);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Write string to cache.
     * @param url The url.
     * @param content The content to be cached.
     */
    public void writeString(String url, String content) {
        try {
            DiskLruCache mDiskLruCache = DiskLruCache.open(mCacheDirectory, 1, 1, DISK_CACHE_SIZE);
            String key = CacheUtils.hashKeyFromUrl(url);
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if(editor != null) {
                BufferedOutputStream outputStream = new BufferedOutputStream(editor.newOutputStream(0), IO_BUFFER_SIZE);
                byte[] bytes = content.getBytes("GB2312");
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
                editor.commit();
            }
            mDiskLruCache.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write bitmap to cache.
     * @param url The url of the bitmap.
     * @param bitmap The bitmap.
     */
    public void writeBitmap(String url, Bitmap bitmap) {
        DiskLruCache diskLruCache = null;
        OutputStream outputStream = null;
        try {
            diskLruCache = DiskLruCache.open(mCacheDirectory, 1, 1, DISK_CACHE_SIZE);
            String key = CacheUtils.hashKeyFromUrl(url);
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if(editor != null) {
                outputStream = editor.newOutputStream(0);
                byte[] bytes = BitmapUtils.bitmap2Bytes(bitmap);
                for(int i = 0; i < bytes.length;) {
                    int length = Math.min(1024, bytes.length - i);
                    byte[] subBytes = Arrays.copyOfRange(bytes, i, i + length);
                    outputStream.write(subBytes, 0, length);
                    i += length;
                }
                outputStream.flush();
                outputStream.close();
                editor.commit();
                diskLruCache.close();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the cache of url.
     * @param url The url.
     */
    public void removeOneCache(String url) {

    }

    /**
     * Delete all the cache.
     */
    public void deleteAllCache() {

    }

    /**
     * Calculate size of the cache.
     * @return
     */
    public int cacheSize() {
        return 0;
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        if(uniqueName != null && uniqueName != "") {
            return new File(cachePath + File.separator + uniqueName);
        }
        return new File(cachePath);
    }
}
