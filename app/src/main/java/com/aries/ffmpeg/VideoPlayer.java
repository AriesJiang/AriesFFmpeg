package com.aries.ffmpeg;

/**
 * Created by JiangYiDong on 2018/2/6.
 */

public class VideoPlayer {

    static {
        System.loadLibrary("VideoPlayer");
    }

    public static native int play(Object surface);
}
