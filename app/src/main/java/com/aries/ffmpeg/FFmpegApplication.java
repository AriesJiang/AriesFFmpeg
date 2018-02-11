package com.aries.ffmpeg;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by JiangYiDong on 2018/2/11.
 */

public class FFmpegApplication extends Application {

//    public static String VIDEO_PATH = File.separator + "/Download/QUIK170930.mp4"; //one_Plus
    public static String VIDEO_PATH = File.separator + "VID_20180220_230848744_99.mp4";  //Sam sung
    private static FFmpegApplication sInstance;

    public static FFmpegApplication getAppContext() {
        return sInstance;
    }

    public String getVideoPath() {
        String path = Environment.getExternalStorageDirectory() + VIDEO_PATH;
        return path;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
