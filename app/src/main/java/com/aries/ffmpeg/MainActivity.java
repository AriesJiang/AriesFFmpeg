package com.aries.ffmpeg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aries.ffmpeg.codec.ClipActivity;
import com.aries.ffmpeg.ffmpeg.ConfigureActivity;
import com.aries.ffmpeg.ffmpeg.FFmpeg;

/**
 * 对FFmpeg命令的JNI集成：http://blog.csdn.net/yhaolpz/article/details/77146156
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFFmpegClick(View view) {
        switch (view.getId()) {
            case R.id.ff_config:
                startActivity(new Intent(this, ConfigureActivity.class));
                break;
            case R.id.ff_play:
                startActivity(new Intent(this, PlayActivity.class));
                break;
            case R.id.ff_cut:
                startActivity(new Intent(this, ClipActivity.class));
                break;
            default:
                break;
        }
    }
}
