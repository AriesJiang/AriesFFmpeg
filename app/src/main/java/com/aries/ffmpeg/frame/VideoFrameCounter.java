package com.aries.ffmpeg.frame;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;

import java.nio.ByteBuffer;

/**
 * Created by 逆流的鱼yuiop on 16/12/18.
 * blog : http://blog.csdn.net/hejjunlin
 * 参考：http://blog.csdn.net/hejjunlin/article/details/53729575
 */
public class VideoFrameCounter {
    private final static String TAG = "VideoClip";
    private MediaExtractor mMediaExtractor;
    private MediaFormat mMediaFormat;
    private String mime = null;

    public boolean clipVideo(String url, long clipPoint) {
        int keyFrameCounter = 0;
        int videoMaxInputSize = 0;
        int audioMaxInputSize = 0;
        int sourceVTrack = 0;
        int sourceATrack = 0;
        long videoDuration, audioDuration;
        long start = System.currentTimeMillis();
        Log.d(TAG, ">>clip start　url : " + url);
        //创建分离器
        mMediaExtractor = new MediaExtractor();
        try {
            //设置文件路径
            mMediaExtractor.setDataSource(url);
        } catch (Exception e) {
            Log.e(TAG, "error path" + e.getMessage());
        }
        //获取每个轨道的信息
        for (int i = 0; i < mMediaExtractor.getTrackCount(); i++) {
            try {
                mMediaFormat = mMediaExtractor.getTrackFormat(i);
                mime = mMediaFormat.getString(MediaFormat.KEY_MIME);
                if (mime.startsWith("video/")) {
                    sourceVTrack = i;
                    int width = mMediaFormat.getInteger(MediaFormat.KEY_WIDTH);
                    int height = mMediaFormat.getInteger(MediaFormat.KEY_HEIGHT);
                    videoMaxInputSize = mMediaFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);
                    videoDuration = mMediaFormat.getLong(MediaFormat.KEY_DURATION);
                    //检测剪辑点和剪辑时长是否正确
                    if (clipPoint >= videoDuration) {
                        Log.e(TAG, "clip point is error!");
                        return false;
                    }
                    Log.d(TAG, "width and height is " + width + " " + height
                            + ";maxInputSize is " + videoMaxInputSize
                            + ";duration is " + videoDuration
                    );
                } else if (mime.startsWith("audio/")) {
                    sourceATrack = i;
                    int sampleRate = mMediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE);
                    int channelCount = mMediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
                    audioMaxInputSize = mMediaFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);
                    audioDuration = mMediaFormat.getLong(MediaFormat.KEY_DURATION);
                    Log.d(TAG, "sampleRate is " + sampleRate
                            + ";channelCount is " + channelCount
                            + ";audioMaxInputSize is " + audioMaxInputSize
                            + ";audioDuration is " + audioDuration
                    );
                }
                Log.d(TAG, "file mime is " + mime);
            } catch (Exception e) {
                Log.e(TAG, " read error " + e.getMessage());
            }
        }
        //分配缓冲
        ByteBuffer inputBuffer = ByteBuffer.allocate(videoMaxInputSize);
        //根据官方文档的解释MediaMuxer的start一定要在addTrack之后
        //视频处理部分
        mMediaExtractor.selectTrack(sourceVTrack);
        //选择起点
        mMediaExtractor.seekTo(clipPoint, MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
        while (true) {
            int sampleSize = mMediaExtractor.readSampleData(inputBuffer, 0);
            if (sampleSize < 0) {
                //这里一定要释放选择的轨道，不然另一个轨道就无法选中了
                mMediaExtractor.unselectTrack(sourceVTrack);
                break;
            }
            int trackIndex = mMediaExtractor.getSampleTrackIndex();
            //获取时间戳
            long presentationTimeUs = mMediaExtractor.getSampleTime();
            //获取帧类型，只能识别是否为I帧
            int sampleFlag = mMediaExtractor.getSampleFlags();
            Log.d(TAG, "trackIndex is " + trackIndex
                    + ";presentationTimeUs is " + presentationTimeUs
                    + ";sampleFlag is " + sampleFlag
                    + ";sampleSize is " + sampleSize);
            if (sampleFlag == MediaCodec.BUFFER_FLAG_KEY_FRAME) {
                Log.e(TAG, "MediaCodec.BUFFER_FLAG_KEY_FRAME----" + ";presentationTimeUs is " + presentationTimeUs);
                keyFrameCounter++;
            }
            mMediaExtractor.advance();
        }
        //全部写完后释放MediaMuxer和MediaExtractor
        mMediaExtractor.release();
        mMediaExtractor = null;
        Log.d(TAG, ">>clip end spend time=" + (System.currentTimeMillis() - start) + "　keyFrameCounter=" + keyFrameCounter);
        return true;
    }
}
