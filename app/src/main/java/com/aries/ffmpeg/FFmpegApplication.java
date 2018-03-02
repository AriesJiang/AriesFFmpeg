package com.aries.ffmpeg;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by JiangYiDong on 2018/2/11.
 */

public class FFmpegApplication extends Application {

//    public static String VIDEO_PATH = File.separator + "Download/QUIK170930.mp4"; //one_Plus
//    public static String VIDEO_PATH = File.separator + "Download/concat/S8houzhi1920x1080hengping.mp4";  //Sam sung
    public static String VIDEO_PATH = File.separator + "Download/concat/S8houzhi1920x1080suping.mp4";  //Sam sung
//    public static String VIDEO_PATH = File.separator + "Download/concat/VID_20171209_144634.mp4";  //Sam sung
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

    static {
        System.loadLibrary("VideoPlayer");
    }
}
